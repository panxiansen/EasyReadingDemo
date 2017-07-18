package com.example.administrator.easyreadingdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.LoadNewsData;
import com.example.administrator.easyreadingdemo.NewsDataCallBack;
import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.WebViewActivity;
import com.example.administrator.easyreadingdemo.adapter.News_RecyclerView_Adapter;
import com.example.administrator.easyreadingdemo.bean.News_HomeNews_Info;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */

public class News_TravelNews_Fragment extends Fragment implements NewsDataCallBack{

    private String url = "http://api.tianapi.com/travel/?key=16b9d58e1149ecb6500c1e648728b9a4&num=10";
    private int page = 1;

    private Handler handler = null;

    private List<News_HomeNews_Info.NewslistBean> mList = new ArrayList<>();

    private TwinklingRefreshLayout mRefreshLayout;
    private News_RecyclerView_Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private LoadNewsData mLoadNewsData;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_travel_news, null);

        Fresco.initialize(getContext());
        initView(view);
        initRefreshLayout(view);
        sendNewsUrl(url, page);


        return view;
    }

    private void sendNewsUrl(String url, int page) {

        mLoadNewsData = new LoadNewsData();
        mLoadNewsData.loadData(News_TravelNews_Fragment.this, url, page);

    }

    private void initRefreshLayout(View view) {

        mRefreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.news_travel_news_refreshLayout);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "已刷新数据", Toast.LENGTH_SHORT).show();
                        mRefreshLayout.onFinishRefresh();
                    }
                },3000);

            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {

                new Thread(){
                    @Override
                    public void run() {
                        handler.post(mRefreshRun);
                    }
                }.start();

            }
        });

    }

    private void initView(View view){

        handler = new Handler();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_travel_news_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void getData(List<News_HomeNews_Info.NewslistBean> list) {

        mList.clear();
        for (int i = 0;i<list.size();i++){
            mList.add(list.get(i));
        }

        new Thread() {
            @Override
            public void run() {
                handler.post(mFristRun);
            }
        }.start();


    }

    @Override
    public void getDatas(final List<News_HomeNews_Info.NewslistBean> list, int page) {

        if (list != null && list.size()>0) {
            Log.e("GET DATA TEST", "上拉加载更多数据回调成功");
            mList.addAll(list);
            mAdapter.notifyItemRangeChanged(0, mList.size());
        }
    }

    Runnable mRefreshRun = new Runnable() {
        @Override
        public void run() {
            page = page + 1;
            sendNewsUrl(url, page);
            mRefreshLayout.finishLoadmore();
        }
    };

    Runnable mFristRun = new Runnable() {
        @Override
        public void run() {
            mAdapter = new News_RecyclerView_Adapter(mList, getContext());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    String url = mList.get(position).getUrl();
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", url);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });

        }
    };



}
