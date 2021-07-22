package com.example.tictok.Stream;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import androidx.recyclerview.widget.StaggeredGridLayoutManager;


        import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.tictok.Broad.BroadActivity;
import com.example.tictok.R;

        import java.util.ArrayList;
        import java.util.List;

public class StreamAdapter extends RecyclerView.Adapter<StreamAdapter.ViewHolder> {
    String tryurl = "https://lf1-cdn-tos.bytescm.com/obj/static/ies/bytedance_official_cn/_next/static/images/0-390b5def140dc370854c98b8e82ad394.png";
    String TAG="MyAdapter";
    List<DataStream> mData = new ArrayList<>();

    private Context context;
    private AdapterView.OnItemClickListener mListen;
    private List<String> list=new ArrayList<>();
    private BroadClickListener broadClickListener;


    public void setBroadClickListener(BroadClickListener broadClickListener) {
        this.broadClickListener = broadClickListener;
    }

    public StreamAdapter(Context context){
        this.context=context;
        //list 可以用来显示文字
        for(int i=0;i<100;i++) list.add(String.format("%s",i));
    }

    public StreamAdapter(List<DataStream> data){
        mData.addAll(data);
    }

    @Override
    public StreamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.items_stream,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StreamAdapter.ViewHolder holder, int position) {
        holder.onBind(position);
        holder.mimagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到对应的视频界面
                broadClickListener.jump(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageButton mimagebutton;
        private ImageView id_pic;
        private TextView id;
        private TextView content;
        private View background;

        public ViewHolder(View itemView){
            super(itemView);
            mimagebutton=itemView.findViewById(R.id.stream_iv);
            id_pic= itemView.findViewById(R.id.stream_pic);
            id = itemView.findViewById(R.id.stream_id);
            content = itemView.findViewById(R.id.stream_content);
            background= itemView.findViewById(R.id.stream_back);
        }
        public void onBind(int position){

            if(position%3==0){
                //mImageView.setImageResource(R.drawable.num0);
                Glide.with(context)
                        .load(tryurl)
                        .placeholder(R.drawable.loading)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                        .transition(withCrossFade())
                        .into(mimagebutton);
            }
            else if(position%3==1){
//                mImageView.setImageResource(R.drawable.num4);
//                Glide.with(context).load(R.drawable.num4).into(mImageView);
                Glide.with(context)
                        .load(R.drawable.num4)
                        .placeholder(R.drawable.loading)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                        .transition(withCrossFade())
                        .into(mimagebutton);
            }
            else{
//                mImageView.setImageResource(R.drawable.num5);
//                Glide.with(context).load(R.drawable.num5).into(mImageView);
                Glide.with(context)
                        .load(R.drawable.num5)
                        .placeholder(R.drawable.loading)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(80)))
                        .transition(withCrossFade())
                        .into(mimagebutton);
            }

            Log.d(TAG, "onBind: "+ ViewGroup.LayoutParams.MATCH_PARENT);

        }
    }
}
