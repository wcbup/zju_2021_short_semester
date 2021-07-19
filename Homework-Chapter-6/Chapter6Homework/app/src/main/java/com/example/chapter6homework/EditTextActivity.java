package com.example.chapter6homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditTextActivity extends AppCompatActivity {

    private static final String TAG = "InEditTextActivity";

    private static final String SP_KEY = "noteCache";
    private static final String CACHE_KEY = "cache";


    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Button btnConfirm = findViewById(R.id.btn_confirm);
        editText = findViewById(R.id.edit_text);


        SharedPreferences preferences = getSharedPreferences(SP_KEY,
                MODE_PRIVATE);
        String cacheString;
        cacheString = preferences.getString(CACHE_KEY,"");
        Log.d(TAG, "The preferences get: "+cacheString);
        editText.setText(cacheString);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Log.d(TAG, editText.getText().toString());
                intent.putExtra("data_return", editText.getText().toString());
                intent.putExtra("priority",getPriority());
                setResult(RESULT_OK, intent);
                cleanCache();
                finish();
            }
        });
    }

    private int getPriority(){
        RadioGroup radioGroup = findViewById(R.id.radio);
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.btn_high:
                Log.d(TAG, "getPriority: "+"high");
                return 3;
            case R.id.btn_medium:
                Log.d(TAG, "getPriority: "+"medium");
                return 2;
            default:
                Log.d(TAG, "getPriority: "+"low");
                return 1;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = getSharedPreferences(SP_KEY,
                MODE_PRIVATE).edit();
        editor.putString(CACHE_KEY,editText.getText().toString());
        editor.apply();
    }

    private void cleanCache(){
        Log.d(TAG, "cleanCache: ");
        SharedPreferences.Editor editor = getSharedPreferences(SP_KEY,
                MODE_PRIVATE).edit();
        editor.putString(CACHE_KEY,"");
        editor.apply();
        editText.setText("");
    }
}