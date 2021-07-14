package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        List<Friend> friendList = new ArrayList<>();
        for(int i=0;i<2;i++){
            friendList.add(new Friend("Frank",R.drawable.img1));
            friendList.add(new Friend("Frank",R.drawable.img2));
            friendList.add(new Friend("Frank",R.drawable.img3));
            friendList.add(new Friend("Frank",R.drawable.img4));
            friendList.add(new Friend("Frank",R.drawable.img5));
            friendList.add(new Friend("Frank",R.drawable.img6));
            friendList.add(new Friend("Frank",R.drawable.img7));
            friendList.add(new Friend("Frank",R.drawable.img8));
            friendList.add(new Friend("Frank",R.drawable.img9));
            friendList.add(new Friend("Frank",R.drawable.img10));
        }
        FriendAdapter adapter = new FriendAdapter(
                getContext(),
                R.layout.friend_item,
                friendList
        );
        ListView listView = view.findViewById(R.id.friend_list);
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                LottieAnimationView animationView =
                        getView().findViewById(R.id.load_animate);
                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(
                        animationView,
                        "alpha",
                        1f,
                        0f
                );
                alphaAnimator.setRepeatCount(0);
                alphaAnimator.setInterpolator(new LinearInterpolator());
                alphaAnimator.setDuration(1000);
                alphaAnimator.start();
                alphaAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        ListView friend_list = getView().findViewById(R.id.friend_list);
                        friend_list.setVisibility(View.VISIBLE);
                        ObjectAnimator alphaAnimator2 = ObjectAnimator.ofFloat(
                                friend_list,
                                "alpha",
                                0f,
                                1f
                        );
                        alphaAnimator2.setRepeatCount(0);
                        alphaAnimator2.setInterpolator(new LinearInterpolator());
                        alphaAnimator2.setDuration(1000);
                        alphaAnimator2.start();
                    }
                });

            }
        }, 5000);
    }
}
