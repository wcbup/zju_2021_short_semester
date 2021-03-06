package com.bytedance.practice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bytedance.practice5.model.MessageListResponse;
import com.bytedance.practice5.socket.SocketActivity;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.bytedance.practice5.model.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private FeedAdapter adapter = new FeedAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Constants.STUDENT_ID);
            }
        });

        findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(null);
            }
        });
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                startActivity(intent);
            }
        });



    }

    //TODO 2
    // ???HttpUrlConnection????????????????????????????????????Gson?????????????????????UI?????????adapter.setData()?????????
    // ?????????????????????UI???????????????????????????????????????
    private void getData(String studentId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String urlStr =
                        " https://api-android-camp.bytedance.com/zju/invoke/messages";
                MessageListResponse result = null;
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(6000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("token", "WkpVLWJ5dGVkYW5jZS1hbmRyb2lk");
                    if(connection.getResponseCode() == 200){
                        InputStream in = connection.getInputStream();
                        BufferedReader reader =
                                new BufferedReader(
                                        new InputStreamReader(
                                                in,
                                                StandardCharsets.UTF_8
                                        )
                                );

                        result = new Gson().fromJson(
                                reader,
                                new TypeToken<MessageListResponse>(){
                                }.getType());

                        reader.close();
                        in.close();

                        MessageListResponse finalResult = result;
                        //??????ui
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.setData(finalResult.feeds);
                            }
                        });


                    }
                    else{
                        Log.e(TAG, "run: connection fails");
                    }
                    connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e(TAG, "run: "+e.toString() );
                }
            }
        }).start();

    }


}