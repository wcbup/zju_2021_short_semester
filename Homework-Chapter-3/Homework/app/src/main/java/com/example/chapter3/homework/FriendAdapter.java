package com.example.chapter3.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FriendAdapter extends ArrayAdapter<Friend> {
    private int resourceId;

    public FriendAdapter(Context context, int viewResourceId,
                         List<Friend> objects){
        super(context, viewResourceId, objects);
        resourceId = viewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,
                       ViewGroup parent) {
        Friend friend = getItem(position);
        View view = LayoutInflater.from(getContext())
                .inflate(resourceId, parent, false);
        ImageView friendImage = view.findViewById(R.id.friend_image);
        TextView friendName = view.findViewById(R.id.friend_name);
        friendImage.setImageResource(friend.getImageId());
        friendName.setText(friend.getName());
        return view;
    }
}
