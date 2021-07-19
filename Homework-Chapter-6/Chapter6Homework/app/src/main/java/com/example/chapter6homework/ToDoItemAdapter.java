package com.example.chapter6homework;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class ToDoItemAdapter extends RecyclerView.Adapter<ToDoItemAdapter.ViewHolder>{

    private static final String TAG = "InToDoItemAdapter";

    public List<ToDoItem> mToDoItemList;
    View mParent;

//    DeleteListener deleteListener;
    UpdateListener listener;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBoxItem;
        TextView textTodo;
        TextView textTime;
        ImageButton imageButtonDelete;

        public ViewHolder(View view){
            super(view);
            checkBoxItem = view.findViewById(R.id.checkbox_item);
            textTodo = view.findViewById(R.id.text_todo);
            textTime = view.findViewById(R.id.text_time);
            imageButtonDelete = view.findViewById(R.id.image_btn_delete);
        }
    }

    public ToDoItemAdapter(List<ToDoItem> toDoItemList){
        mToDoItemList = toDoItemList;
        Collections.sort(mToDoItemList, new MyComparator());
    }

//    public void setDeleteListener(DeleteListener deleteListener) {
//        this.deleteListener = deleteListener;
//    }

    public void setUpdateListener(UpdateListener listener){
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        mParent = parent;
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ToDoItemAdapter.ViewHolder holder, int position) {
        ToDoItem toDoItem = mToDoItemList.get(position);
        holder.textTodo.setText(toDoItem.whatToDo);
        holder.textTime.setText(toDoItem.createTime.toString());
        holder.checkBoxItem.setChecked(toDoItem.isFinished);
        if(toDoItem.isFinished){
            holder.textTodo.setTextColor(Color.GRAY);
        }else {
            holder.textTodo.setTextColor(Color.BLACK);
        }
        switch (toDoItem.priority){
            case 3:
                holder.textTime.setBackgroundColor(0xFFF498AD);
                break;
            case 2:
                holder.textTime.setBackgroundColor(0xFF03DAC5);
                break;
            default:
                holder.textTime.setBackgroundColor(Color.TRANSPARENT);
                break;
        }

        holder.checkBoxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                if(mToDoItemList.get(position).isFinished == isChecked){
                    return;
                }
                ToDoItem tmp = mToDoItemList.get(position);
                tmp.isFinished = isChecked;
                mToDoItemList.set(position, tmp);
//                mToDoItemList.get(position).isFinished = isChecked;
                if(toDoItem.isFinished){
                    holder.textTodo.setTextColor(Color.GRAY);
                }else {
                    holder.textTodo.setTextColor(Color.BLACK);
                }
//                for(ToDoItem toDoItem:mToDoItemList){
//                    Log.d(TAG, "isFinished "+toDoItem.isFinished);
//                    Log.d(TAG, "whatToDo "+toDoItem.whatToDo);
//                    Log.d(TAG, "createTime "+toDoItem.createTime);
//                }
                Collections.sort(mToDoItemList, new MyComparator());
                notifyDataSetChanged();
            }
        });




        holder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                deleteListener.click(position);
                mToDoItemList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mToDoItemList.size();
    }

//    private void mySort(){
//        for(int i=0;i<mToDoItemList.size();i++){
//            for(int j=mToDoItemList.size()-1;j>i;j--){
//                if(MyComparator.compare(mToDoItemList.get(i),mToDoItemList.get(j))>0){
//                    ToDoItem tmp;
//                    tmp = mToDoItemList.get(i);
//                    mToDoItemList.set(i,mToDoItemList.get(j));
//                    mToDoItemList.set(j,tmp);
//                }
//            }
//        }
////        listener.update();
//    }
}
interface UpdateListener {

    void update();
}