package com.example.administrator.easyreadingdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.bean.Pic_Info;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Pic_RecyclerView_Adapter extends RecyclerView.Adapter<Pic_RecyclerView_Adapter.MyViewHolder> {

    private List<Pic_Info.ImgsBean> mList;
    private Context context;
    private Bitmap mBitmap;
    private OnItemClickListener mItemClickListener;

    public Pic_RecyclerView_Adapter(List<Pic_Info.ImgsBean> mList, Context context){
        this.mList = mList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mItemClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pic_recyclerview_item,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Picasso.with(context).load(mList.get(position).getImageUrl()).placeholder(R.mipmap.waiting).into(holder.mPic);

        if (mItemClickListener != null){
            holder.mPic.getDrawingCache();

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mItemClickListener.onItemClick(v, position);

                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemClickListener != null){
                        mItemClickListener.onItemLongClick(v,position);
                    }
                    return false;
                }
            });
        }

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mPic;
        LinearLayout mLinearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mPic = (ImageView) itemView.findViewById(R.id.pic_recyclerview_item_pic);

        }
    }
}
