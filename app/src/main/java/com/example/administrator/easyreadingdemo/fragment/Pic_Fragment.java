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
import com.example.administrator.easyreadingdemo.adapter.Pic_PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */

public class Pic_Fragment extends Fragment {

    private List<String> list_title;
    private List<Fragment> list_fragment;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Pic_PagerAdapter mAdapter;

    private Pic_MeiNv_Fragment meinv_fragment;
    private Pic_DongMan_Fragment dongman_fragment;
    private Pic_MingXing_Fragment mingxing_fragment;
    private Pic_QiChe_Fragment qiche_fragment;
    private Pic_SheYing_Fragment sheying_fragment;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.pic_fragment, null);

        initView(view);
        return view;

    }

    private void initView(View view) {

        mViewPager = (ViewPager) view.findViewById(R.id.pic_fragment_viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.pic_fragment_tablayout);

        list_title = new ArrayList<>();
        list_title.add("美女");
        list_title.add("动漫");
        list_title.add("明星");
        list_title.add("汽车");
        list_title.add("摄影");

        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(2)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(3)));
        mTabLayout.addTab(mTabLayout.newTab().setText(list_title.get(4)));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        meinv_fragment = new Pic_MeiNv_Fragment();
        dongman_fragment = new Pic_DongMan_Fragment();
        mingxing_fragment = new Pic_MingXing_Fragment();
        qiche_fragment = new Pic_QiChe_Fragment();
        sheying_fragment = new Pic_SheYing_Fragment();

        list_fragment = new ArrayList<>();
        list_fragment.add(meinv_fragment);
        list_fragment.add(dongman_fragment);
        list_fragment.add(mingxing_fragment);
        list_fragment.add(qiche_fragment);
        list_fragment.add(sheying_fragment);

        mAdapter = new Pic_PagerAdapter(getActivity().getSupportFragmentManager(), list_fragment, list_title);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
