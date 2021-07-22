package com.example.tictok.Stream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tictok.Broad.BroadActivity;
import com.example.tictok.Camera.CameraActivity;
import com.example.tictok.R;

public class StreamActivity extends AppCompatActivity {

    private RecyclerView stream;
    private ImageButton camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);

        //初始化recycler和其监听
        stream = findViewById(R.id.stream);
        stream.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        StreamAdapter adapter = new StreamAdapter(StreamActivity.this);
        adapter.setBroadClickListener(new BroadClickListener() {
            @Override
            public void jump(int position) {
                Intent intent = new Intent(StreamActivity.this, BroadActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        stream.setAdapter(adapter);



        //设置camera图表及其监听
        camera = findViewById(R.id.stream_camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StreamActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });



    }
}