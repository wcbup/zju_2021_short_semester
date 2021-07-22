package com.example.tictok.Broad;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class CusVideoView extends VideoView {

    public CusVideoView(Context context) {
        super(context);
    }

    public CusVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(getWidth(),widthMeasureSpec);
        int height = getDefaultSize(getHeight(),heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
