package com.example.chapter2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Scroll extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private int[] imgId= new int[10];

    private void init(){
        imgId[0] = R.drawable.e1;
        imgId[1] = R.drawable.e2;
        imgId[2] = R.drawable.e3;
        imgId[3] = R.drawable.e4;
        imgId[4] = R.drawable.e5;
        imgId[5] = R.drawable.e6;
        imgId[6] = R.drawable.e7;
        imgId[7] = R.drawable.e8;
        imgId[8] = R.drawable.e9;
        imgId[9] = R.drawable.e10;

        for(int i = 0; i < 50; i++){
            Item item = new Item("今天天气真热啊", imgId[i%10]);
            itemList.add(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        init();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}