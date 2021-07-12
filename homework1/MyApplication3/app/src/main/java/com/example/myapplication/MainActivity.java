package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean textFlag=false;
    boolean imagFlag=false;
    boolean isCheckOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1=findViewById(R.id.btn1);
        TextView tv1=findViewById(R.id.tv1);
        ImageView iv1=findViewById(R.id.iv1);
        EditText et1=findViewById(R.id.ed1);
        Switch sw1=findViewById(R.id.sw1);
        CheckBox cb1=findViewById(R.id.cb1);

        final String TAG="MyActivity";

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheckOn=isChecked;
                Log.v(TAG,"CheckBox is "+(isChecked?"On":"Off"));
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG,"btn1 pressed");
                String text;
                if(textFlag){
                    text = getResources().getString(R.string.text1);
                    textFlag=false;
                }
                else{
                    text = getResources().getString(R.string.text2);
                    textFlag=true;
                }
                tv1.setText(text);
                Log.v(TAG,"text changed");
            }
        });

        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Bitmap bm2=BitmapFactory.decodeResource(getResources(),R.drawable.img2);
                Bitmap bm1=BitmapFactory.decodeResource(getResources(),R.drawable.img1);
                if(isChecked){
                    Log.v(TAG,"switch On");
                    iv1.setImageBitmap(bm1);
                    Log.v(TAG,"image change to img1");
                    imagFlag=false;
                }
                else{
                    Log.v(TAG,"switch Off");
                    iv1.setImageBitmap(bm2);
                    Log.v(TAG,"image change to img2");
                    imagFlag=true;
                }
            }
        });

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isCheckOn){
                    tv1.setText(s.toString().trim());
                }
                else {
                }
            }
        });
    }
}