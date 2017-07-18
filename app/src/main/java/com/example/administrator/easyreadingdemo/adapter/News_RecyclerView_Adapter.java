package com.example.administrator.easyreadingdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.bean.News_HomeNews_Info;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class News_RecyclerView_Adapter extends RecyclerView.Adapter<News_RecyclerView_Adapter.MyViewHolder> {

    private List<News_HomeNews_Info.NewslistBean> mList = new ArrayList<>();
    private Context context;
    private OnItemClickListener mItemClickListener;

    public News_RecyclerView_Adapter(List<News_HomeNews_Info.NewslistBean> mList, Context context){
        this.mList = mList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }



    @Override
    public News_RecyclerView_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recyclerview_item,null);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    public void addDatas(List<News_HomeNews_Info.NewslistBean> datas, int position){
        if (datas != null && datas.size()>0){


            notifyItemRangeChanged(position, datas.size());

        }
    }

    @Override
    public void onBindViewHolder(final News_RecyclerView_Adapter.MyViewHolder holder, int position) {

        holder.title.setText(mList.get(position).getTitle());
        holder.description.setText(mList.get(position).getDescription());
        holder.ctime.setText(mList.get(position).getCtime());
        holder.picView.setImageURI(mList.get(position).getPicUrl());

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
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        TextView ctime;
        SimpleDraweeView picView;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_recyclerview_item_title);
            description = (TextView) itemView.findViewById(R.id.news_recyclerview_item_description);
            ctime = (TextView) itemView.findViewById(R.id.news_recyclerview_item_ctime);
            picView = (SimpleDraweeView) itemView.findViewById(R.id.news_recyclerview_item_pic);

        }



    }




}
