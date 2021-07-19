package com.example.chapter6homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    private static final String TAG = "InMainActivity";

    private List<ToDoItem> toDoItemList = new ArrayList<>();

    RecyclerView recyclerView;
    ToDoItemAdapter adapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what == 114){
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create the database
        dbHelper = new MyDatabaseHelper(this,
                "List.db",null,1);
        dbHelper.getWritableDatabase();

        FloatingActionButton add_fab = findViewById(R.id.add_fab);
        add_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditTextActivity.class);
                startActivityForResult(intent, 123);
            }
        });

        initToDoList();

        updateRecycler();

    }

    private void initToDoList(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("List",
                null,
                null,
                null,
                null,
                null,
                null);
        if(cursor.moveToFirst()){
            do{
                Boolean isFinished =
                        cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.IS_FINISHED)) != 0;
                String whatToDo =
                        cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.WHAT_TO_DO));
                String createTime =
                        cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.CREATE_TIME));
                int priority =
                        cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.PRIORITY));
                toDoItemList.add(new ToDoItem(isFinished, whatToDo,createTime,priority));
            }while (cursor.moveToNext());
            cursor.close();
        }

//        for(int i = 0; i < 20; i++){
//            toDoItemList.add(new ToDoItem(false,
//                    Integer.toString(i),
//                    (new Date()).toString()));
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 123:
                if(resultCode == RESULT_OK){
                    String returnData = data.getStringExtra("data_return");
                    int priority = data.getIntExtra("priority",1);
                    Log.d(TAG, "priority "+priority);
                    Log.d(TAG, "add "+returnData);
                    toDoItemList.add(new ToDoItem(returnData, priority));
                    updateRecycler();
                }
                break;
            default:
                break;
        }
    }

    private void updateRecycler(){
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ToDoItemAdapter(toDoItemList);
        recyclerView.setAdapter(adapter);
        adapter.setUpdateListener(new UpdateListener() {
            @Override
            public void update() {
                adapter.notifyDataSetChanged();
            }
        });

//        adapter.setDeleteListener(new DeleteListener() {
//            @Override
//            public void click(int position) {
//                toDoItemList.remove(position);
//                adapter.notifyDataSetChanged();
//            }
//        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: "+Integer.toString(toDoItemList.size()));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //delete all the data in the table
        db.execSQL("delete from List");
        ContentValues values = new ContentValues();
        //insert all the data in the toDoList into the table
        for(ToDoItem toDoItem:toDoItemList){
            values.clear();
            values.put(MyDatabaseHelper.IS_FINISHED,
                    (toDoItem.isFinished?1:0));
            values.put(MyDatabaseHelper.WHAT_TO_DO,toDoItem.whatToDo);
            values.put(MyDatabaseHelper.CREATE_TIME,toDoItem.createTime);
            values.put(MyDatabaseHelper.PRIORITY, toDoItem.priority);
            db.insert("List",null,values);
        }
    }
}