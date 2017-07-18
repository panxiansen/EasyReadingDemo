package com.example.administrator.easyreadingdemo;

import com.example.administrator.easyreadingdemo.bean.Pic_Info;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */

public interface PicDataCallBack {

    public void getData(List<Pic_Info.ImgsBean> mList);

    public void getDatas(List<Pic_Info.ImgsBean> mList, int page);

}
