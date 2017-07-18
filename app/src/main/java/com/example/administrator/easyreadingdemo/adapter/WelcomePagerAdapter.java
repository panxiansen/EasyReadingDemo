package com.example.administrator.easyreadingdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class WelcomePagerAdapter extends PagerAdapter {

    //欢迎界面图片ID
    private int[] mResIds = null;
    private Context mContext;

    public WelcomePagerAdapter(Context mContext, int[] mResIds){
        this.mContext = mContext;
        this.mResIds = mResIds;
    }

    @Override
    public int getCount() {
        return mResIds.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(mResIds[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
