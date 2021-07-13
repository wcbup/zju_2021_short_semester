package com.example.chapter2_1;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Item> itemList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_index;
        TextView item_text;
        ImageView item_img;

        public ViewHolder(View view){
            super(view);
            item_index = view.findViewById(R.id.item_index);
            item_text = view.findViewById(R.id.item_text);
            item_img = view.findViewById(R.id.item_img);
        }
    }

    public MyAdapter(List<Item> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.item_index.setText(Integer.toString(position));
        holder.item_text.setText(item.getText());
        holder.item_img.setImageResource(item.getImgId());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
