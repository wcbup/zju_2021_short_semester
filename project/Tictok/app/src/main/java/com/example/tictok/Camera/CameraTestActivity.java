package com.example.tictok.Camera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

import com.example.tictok.R;

public class CameraTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test);

        VideoView videoView = findViewById(R.id.video_test);
        videoView.setVideoPath(CameraActivity.mp4Path);
        videoView.start();
    }
}