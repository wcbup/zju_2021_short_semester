package com.example.tictok.Broad;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictok.R;

public class BroadActivity extends AppCompatActivity {
    private static final String TAG = "BroadActivity";
    private RecyclerView mRecycler;
    private CusLayoutManager mLayoutManager;
    private int position; //表示此时应该播放的视频的编号
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        Intent intent = getIntent();
        intent.getIntExtra("position",position);

        //进行界面 包括recycler的初始化
        initView();
        //进行监听的初始化
        initListener(position);
    }

    //初始化监听
    private void initListener(int position) {

        mLayoutManager.setOnPageSlideListener(new OnPageSlideListener() {

            //当pagerelease 的时候发生的事情
            @Override
            public void onPageRelease(boolean isNext, int position) {
                int index;
                if (isNext) {
                    index = 0;
                } else {
                    index = 1;
                }
                releaseVideo(index);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onPageSelected(int position, boolean isNext) {
                playVideo();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //初始化View
    private void initView() {
        mRecycler = findViewById(R.id.mRecycler);
        mLayoutManager = new CusLayoutManager(this, OrientationHelper.VERTICAL, false);
        VideoAdapter mAdapter = new VideoAdapter(this);
        mRecycler.setLayoutManager(mLayoutManager);
        mRecycler.setAdapter(mAdapter);
        back =findViewById(R.id.broad_back);
    }

    //播放
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void playVideo() {
        //初始化页面上的必要元素
        View itemView = mRecycler.getChildAt(0);
        final CusVideoView mVideoView = itemView.findViewById(R.id.mVideoView);
        final ImageView mPlay = itemView.findViewById(R.id.mPlay);
        final ImageView mThumb = itemView.findViewById(R.id.mThumb);
        final MediaPlayer[] mMediaPlayer = new MediaPlayer[1];
        mVideoView.start();

        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                //将页面设置循环播放
                mMediaPlayer[0] = mp;
                mp.setLooping(true);
                //设置循环滚动的时间
                mThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });

        //暂停控制
        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoView.isPlaying()) {
                    mPlay.animate().alpha(1f).start();
                    mVideoView.pause();
                } else {
                    mPlay.animate().alpha(0f).start();
                    mVideoView.start();
                }
            }
        });
    }

    //释放
    private void releaseVideo(int index) {
        View itemView = mRecycler.getChildAt(index);
        final CusVideoView mVideoView = itemView.findViewById(R.id.mVideoView);
        final ImageView mThumb = itemView.findViewById(R.id.mThumb);
        final ImageView mPlay = itemView.findViewById(R.id.mPlay);
        mVideoView.stopPlayback();
        mThumb.animate().alpha(1).start();
        mPlay.animate().alpha(0f).start();
    }


}