package com.example.administrator.easyreadingdemo;

import com.example.administrator.easyreadingdemo.bean.News_HomeNews_Info;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface NewsDataCallBack {

    public void getData(List<News_HomeNews_Info.NewslistBean> mList);

    public void getDatas(List<News_HomeNews_Info.NewslistBean> mList, int page);

}
