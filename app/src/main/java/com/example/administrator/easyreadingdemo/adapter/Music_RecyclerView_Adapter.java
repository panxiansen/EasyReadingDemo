package com.example.administrator.easyreadingdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.bean.Music_Info;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Music_RecyclerView_Adapter extends RecyclerView.Adapter<Music_RecyclerView_Adapter.MyViewHolder> {

    private List<Music_Info> musicList = new ArrayList<>();
    private OnItemClickListener mItemClickListener;

    public Music_RecyclerView_Adapter(List<Music_Info> musicList){
        this.musicList = musicList;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }

    @Override
    public Music_RecyclerView_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_fragment_item,null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.title.setText(musicList.get(position).title);
        holder.singer.setText(musicList.get(position).display_name);
        holder.mPic.setImageResource(R.mipmap.music_pic);

        if (mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mItemClickListener.onItemClick(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView mPic;
        TextView title;
        TextView singer;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPic = (SimpleDraweeView) itemView.findViewById(R.id.music_fragment_item_pic);
            title = (TextView) itemView.findViewById(R.id.music_fragment_item_title);
            singer = (TextView) itemView.findViewById(R.id.music_fragment_item_singer);
        }
    }
}
