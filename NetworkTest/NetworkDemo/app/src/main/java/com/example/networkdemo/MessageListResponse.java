package com.example.networkdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageListResponse {
    @SerializedName("feeds")
    public List<MyMessage> feeds;
    @SerializedName("success")
    public boolean success;
}
