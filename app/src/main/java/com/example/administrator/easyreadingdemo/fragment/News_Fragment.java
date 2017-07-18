package com.example.administrator.easyreadingdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.easyreadingdemo.R;
import com.example.administrator.easyreadingdemo.adapter.News_PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */

public class News_Fragment extends Fragment {

    private List<String> list_title;
    private List<Fragment> list_fragment;
    private News_PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private News_HomeNews_Fragment homenewsframgent;
    private News_AgricultureNews_Fragment agriculturefragment ;
    private News_ScienceNews_Fragment sciencefragment;
    private News_SportNews_Fragment sportfragment;
    private News_TravelNews_Fragment travelfragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_fragment, null);

        initView(view);

        return view;

    }


    private void initView(View view){

        mViewPager = (ViewPager) view.findViewById(R.id.news_fragment_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.news_fragment_tablayout);

        list_title = new ArrayList<String>();
        list_title.add("头条");
        list_title.add("体育");
        list_title.add("创业");
        list_title.add("科技");
        list_title.add("旅游");

        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(4)));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        list_fragment = new ArrayList<Fragment>();
        homenewsframgent = new News_HomeNews_Fragment();
        sportfragment = new News_SportNews_Fragment();
        agriculturefragment = new News_AgricultureNews_Fragment();
        sciencefragment = new News_ScienceNews_Fragment();
        travelfragment = new News_TravelNews_Fragment();

        list_fragment.add(homenewsframgent);
        list_fragment.add(sportfragment);
        list_fragment.add(agriculturefragment);
        list_fragment.add(sciencefragment);
        list_fragment.add(travelfragment);


        mPagerAdapter = new News_PagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);//ViewPager缓存或预加载页面数量
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
