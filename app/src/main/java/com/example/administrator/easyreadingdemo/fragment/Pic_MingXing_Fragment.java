package com.example.administrator.easyreadingdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.easyreadingdemo.LoadPicData;
import com.example.administrator.easyreadingdemo.OnItemClickListener;
import com.example.administrator.easyreadingdemo.PicBigActivity;
import com.example.administrator.easyreadingdemo.PicDataCallBack;
import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.adapter.Pic_RecyclerView_Adapter;
import com.example.administrator.easyreadingdemo.bean.Pic_Info;
import com.example.administrator.easyreadingdemo.utils.PicItemDecoration;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public class Pic_MingXing_Fragment extends Fragment implements PicDataCallBack{

    private String url = "http://image.baidu.com/data/imgs?col=明星&tag=明星&sort=0";
    private int page = 1;
    private LoadPicData mLoadPic;
    private List<Pic_Info.ImgsBean> mList = new ArrayList<>();

    private Handler handler = new Handler();

    private RecyclerView mRecyclerView;
    private Pic_RecyclerView_Adapter mAdapter;
    private TwinklingRefreshLayout mRefreshLayout;
    private StaggeredGridLayoutManager mLayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pic_mingxing, null);

        initView(view);
        sendPicUrl(url, page);
        initRefreshLayout(view);

        return view;

    }

    private void sendPicUrl(String url, int page) {

        mLoadPic = new LoadPicData();
        mLoadPic.loadData(Pic_MingXing_Fragment.this, url, page);

    }

    private void initView(View view) {

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pic_mingxing_recyclerview);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void initRefreshLayout(View view){

        mRefreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.pic_mingxing_refreshlayout);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "已刷新数据", Toast.LENGTH_SHORT).show();
                        mRefreshLayout.onFinishLoadMore();
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


    @Override
    public void getData(List<Pic_Info.ImgsBean> list) {

        mList.clear();
        for (int i = 0;i<list.size()-1;i++){
            mList.add(list.get(i));
        }

        new Thread(){
            @Override
            public void run() {
                handler.post(mFirstRun);
            }
        }.start();

    }

    @Override
    public void getDatas(final List<Pic_Info.ImgsBean> list, int page) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (list != null && list.size()>0){
                    for (int i = 0;i<list.size()-1;i++){
                        mList.add(list.get(i));
                    }
                    mAdapter.notifyItemRangeChanged(0,mList.size());
                }
            }
        });


    }

    Runnable mFirstRun = new Runnable() {
        @Override
        public void run() {
            mAdapter = new Pic_RecyclerView_Adapter(mList, getContext());
            mRecyclerView.setAdapter(mAdapter);
            PicItemDecoration decoration = new PicItemDecoration(getContext());
            mRecyclerView.addItemDecoration(decoration);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                    Intent intent = new Intent(getContext(), PicBigActivity.class);
                    String sendUrl = mList.get(position).getImageUrl();
                    Bundle bundle = new Bundle();
                    bundle.putString("url", sendUrl);

                    intent.putExtras(bundle);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
        }
    };

    Runnable mRefreshRun = new Runnable() {
        @Override
        public void run() {
            page = page + 2;
            sendPicUrl(url, page);
            mRefreshLayout.finishLoadmore();
        }
    };

}
