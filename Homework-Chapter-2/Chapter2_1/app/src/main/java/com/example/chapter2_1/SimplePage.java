package com.example.chapter2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SimplePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_page);

        Button btn_eat = findViewById(R.id.btn_eat);

        btn_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SimplePage.this,
                        "干饭！",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}