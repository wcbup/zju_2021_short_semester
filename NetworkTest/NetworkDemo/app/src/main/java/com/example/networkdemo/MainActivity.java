package com.example.networkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Permissions;
import java.sql.SQLTransactionRollbackException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };

    private static final String BASE_URL =
            "https://api-android-camp.bytedance.com/zju/invoke/";
    private static final String TOKEN =
            "WkpVLWJ5dGVkYW5jZS1hbmRyb2lk";

    MessageListResponse result = null;

    private static final String TAG = "InMainActivity";

    private Retrofit retrofit = null;
    private IApi api;
    private Uri coverImageUri;
    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_send_pull = findViewById(R.id.btn_pull);
        btn_send_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPullRequest();
            }
        });

        Button btn_send_post = findViewById(R.id.btn_push);
        btn_send_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions(PERMISSIONS_STORAGE,1);
                sendPushRequest();
            }
        });
    }

    private void sendPullRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BASE_URL+"video");
                    HttpURLConnection connection =(HttpURLConnection)url.openConnection();
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
                                new TypeToken<MessageListResponse>(){}.getType()
                        );

                        reader.close();
                        in.close();

                        Log.d(TAG, "return: "+result.success);
                        pullTest();

                    }else {
                        Log.e(TAG, "run: connection fails");
                    }
                    connection.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e(TAG, "run: "+e.toString());
                }
            }
        }).start();
    }

    void sendPushRequest(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(IApi.class);

        File imageFile = new File("/storage/emulated/0/Download/img1.jpeg");
        RequestBody imageRequest =
                RequestBody.create(MediaType.parse("multipart/form-data"),imageFile);
        MultipartBody.Part imagePart =
                MultipartBody.Part.createFormData(
                        "cover_image",
                        imageFile.getName(),
                        imageRequest
                );

        File videoFile = new File("/storage/emulated/0/Download/video.mp4");
        RequestBody videoRequest =
                RequestBody.create(MediaType.parse("multipart/form-data"),videoFile);
        MultipartBody.Part videoPart =
                MultipartBody.Part.createFormData(
                        "video",
                        videoFile.getName(),
                        videoRequest
                );

        Call<UploadResponse> call =
                api.submitMessage("3190101918",
                        "wcbup",
                        "",
                        imagePart,
                        videoPart,
                        TOKEN);

        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if(!response.isSuccessful()){
                    Log.e(TAG, "update fails");
                }else {
                    Log.d(TAG, "update success");
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.toString() );
            }
        });
    }

    private void pullTest(){
        String pullTag = "InPull";
        for(MyMessage myMessage :result.feeds){
            Log.d(pullTag, "id: "+myMessage.Id);
            Log.d(pullTag, "student_id: "+myMessage.studentId);
            Log.d(pullTag, "userName: "+myMessage.userName);
            Log.d(pullTag, "extraValue: "+myMessage.extraValue);
            Log.d(pullTag, "videoUrl: "+myMessage.videoUrl);
            Log.d(pullTag, "imageUrl: "+myMessage.imageUrl);
            Log.d(pullTag, "imageW: "+myMessage.imageWidth);
            Log.d(pullTag, "imageH: "+myMessage.imageHeight);
            Log.d(pullTag, "createAt: "+myMessage.createAt);
            Log.d(pullTag, "updateAt: "+myMessage.updateAt);
        }
    }
}