package com.example.chapter7homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        ImageView imageView = findViewById(R.id.image_view);


        Button btn_load_pic = findViewById(R.id.btn_load_pic);
        btn_load_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pic_url = "https://cdnfile.aixifan.com/static/umeditor/emotion/images/ac/1.gif";
                DrawableCrossFadeFactory drawableCrossFadeFactory
                        = new DrawableCrossFadeFactory.Builder(500)
                        .setCrossFadeEnabled(true).build();
                Glide.with(PictureActivity.this)
                        .load(pic_url)
                        .placeholder(R.drawable.loading_pic)
                        .apply(new RequestOptions().centerInside())
                        .error(R.drawable.load_fail)
                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                        .into(imageView);
            }
        });

        Button btn_load_fail = findViewById(R.id.btn_load_fail);
        btn_load_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pic_url = "https://cdnfile.aixifan.com/static/umeditor/emotion/ims/ac/1.gif";
                DrawableCrossFadeFactory drawableCrossFadeFactory
                        = new DrawableCrossFadeFactory.Builder(500)
                        .setCrossFadeEnabled(true).build();
                Glide.with(PictureActivity.this)
                        .load(pic_url)
                        .placeholder(R.drawable.loading_pic)
                        .apply(new RequestOptions().centerInside())
                        .error(R.drawable.load_fail)
                        .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                        .into(imageView);
            }
        });
    }
}