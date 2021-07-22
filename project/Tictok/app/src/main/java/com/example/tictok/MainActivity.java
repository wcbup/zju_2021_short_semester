package com.example.tictok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tictok.Broad.BroadActivity;
import com.example.tictok.Camera.CameraActivity;
import com.example.tictok.Camera.CameraTestActivity;
import com.example.tictok.Stream.StreamActivity;
import com.example.tictok.Upload.UploadActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button broad = findViewById(R.id.broad);
        Button camera = findViewById(R.id.camera);
        Button stream = findViewById(R.id.stream);
        Button upload = findViewById(R.id.upload);

        requestPermission();

        broad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BroadActivity.class);
                startActivity(intent);
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
        stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StreamActivity.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        Button btn_go_test = findViewById(R.id.btn_go_test);
        btn_go_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CameraTestActivity.class);
                startActivity(intent);
            }
        });

    }

    private void requestPermission(){
//        boolean hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
//        boolean hasAudioPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
//
            List<String> permission = new ArrayList<String>();
//            if (!hasCameraPermission) {
//                permission.add(Manifest.permission.CAMERA);
//            }
//            if (!hasAudioPermission) {
//                permission.add(Manifest.permission.RECORD_AUDIO);
//            }
        permission.add(Manifest.permission.CAMERA);
        permission.add(Manifest.permission.CAMERA);
//        ActivityCompat.requestPermissions(this, permission.toArray(new String[permission.size()]), 1001);
        requestPermissions(permission.toArray(new String[permission.size()]),114);
    }
}