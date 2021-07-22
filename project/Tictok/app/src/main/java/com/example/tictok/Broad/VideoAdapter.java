package com.example.tictok.Broad;


import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictok.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    private int[] videos = {R.raw.v1,R.raw.v3};
    private int[] imgs = {R.drawable.douyin, R.drawable.douyin};
    private List<String> mTitles = new ArrayList<>();
    private List<String> mMarqueeList = new ArrayList<>();
    private Context mContext;

    public VideoAdapter(Context context) {
        this.mContext = context;
        mTitles.add("Android仿抖音主界面UI效果,\n一起来学习Android开发啊啊啊啊啊\n#Android高级UIAndroid开发");
        mTitles.add("@乔布奇\nAndroid RecyclerView自定义\nLayoutManager的使用方式，仿抖音效果哦");
        mMarqueeList.add("哈哈创作的原声-乔布奇asdfasldfjlsadjflasdjlf");
        mMarqueeList.add("嘿嘿创作的原声-Jarchielsadjflkasjdflasjdflasjdflkj");
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        View itemv= layoutInflater.inflate(R.layout.items_onbroad,viewGroup,false);
        return new ViewHolder(itemv);
    }



    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int pos) {

        holder.Bind(pos);


        //第一种方式：获取视频第一帧作为封面图片
//    MediaMetadataRetriever media = new MediaMetadataRetriever();
//    media.setDataSource(mContext,Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
//    holder.mThumb.setImageBitmap(media.getFrameAtTime());
//        //第二种方式：使用固定图片作为封面图片
//        holder.mThumb.setImageResource(imgs[pos % 2]);
//        holder.mVideoView.setVideoURI(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
//        holder.mTitle.setText(mTitles.get(pos % 2));
//        holder.mMarquee.setText(mMarqueeList.get(pos % 2));
//        holder.mMarquee.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRootView;
        ImageView mThumb;
        ImageView mPlay;
        TextView mTitle;
        TextView mMarquee;
        CusVideoView mVideoView;
        TextView user;

        public ViewHolder(View itemView) {
            super(itemView);
            mRootView = itemView.findViewById(R.id.mRootView);
            mThumb = itemView.findViewById(R.id.mThumb);
            mPlay = itemView.findViewById(R.id.mPlay);
            mVideoView = itemView.findViewById(R.id.mVideoView);
            mTitle = itemView.findViewById(R.id.mTitle);
            mMarquee = itemView.findViewById(R.id.mMarquee);
            user = itemView.findViewById(R.id.User_Name);
        }

        public void Bind(int pos){
            MediaMetadataRetriever media = new MediaMetadataRetriever();
            media.setDataSource(mContext,Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
            mThumb.setImageBitmap(media.getFrameAtTime());
            //mThumb.setImageResource(imgs[pos % 2]);
            mVideoView.setVideoURI(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + videos[pos % 2]));
            mTitle.setText(mTitles.get(pos % 2));
            mMarquee.setText(mMarqueeList.get(pos % 2));
            mMarquee.setSelected(true);
        }
    }

}
