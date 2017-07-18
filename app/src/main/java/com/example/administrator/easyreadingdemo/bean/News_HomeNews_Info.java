package com.example.administrator.easyreadingdemo.bean;

import com.example.administrator.easyreadingdemo.utils.T;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class News_HomeNews_Info extends T {


    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-05-19 09:42","title":"\u201c解冻民族资产\u201d骗局升级 投65元坐收几十万？","description":"搜狐社会","picUrl":"","url":"http://news.sohu.com/20170519/n493603575.shtml"},{"ctime":"2017-05-18 17:56","title":"布局大健康 复华控股开启健康产业新格局","description":"搜狐社会","picUrl":"","url":"http://news.sohu.com/20170518/n493526253.shtml"},{"ctime":"2017-05-18 09:02","title":"AI英雄 | 李志飞：人工智能将成为人类智商的延","description":"网易VR","picUrl":"http://cms-bucket.nosdn.127.net/c768cd618c1842f09716449114ed3ccf20170518085327.png","url":"http://tech.163.com/17/0518/09/CKN5K8HB00098GJ5.html"},{"ctime":"2017-05-16 17:03","title":"湖南一黑火药生产厂发生爆炸 已致3死1伤2失踪","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493214493_ss.jpeg","url":"http://news.sohu.com/20170516/n493228615.shtml"},{"ctime":"2017-05-16 14:01","title":"两男子共享单车扫码失败怒砸车锁 被行拘5日","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493205885_ss.jpeg","url":"http://news.sohu.com/20170516/n493205884.shtml"},{"ctime":"2017-05-16 14:13","title":"江苏一小贩与城管起纠纷捅伤5人 包括两名城管","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493207377_ss.jpeg","url":"http://news.sohu.com/20170516/n493207376.shtml"},{"ctime":"2017-05-16 14:57","title":"山东郯城女生疑遭高中教师性侵后自杀 涉事人被拘","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493214493_ss.jpeg","url":"http://news.sohu.com/20170516/n493214492.shtml"},{"ctime":"2017-05-16 13:06","title":"勒索病毒\u201c想哭\u201d会感染手机？ 安全机构称没监测到","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493188003_ss.jpeg","url":"http://news.sohu.com/20170516/n493202366.shtml"},{"ctime":"2017-05-16 13:37","title":"香港一医院将人工授精导管标记遗漏女患者体内","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493202367_ss.jpg","url":"http://news.sohu.com/20170516/n493204222.shtml"},{"ctime":"2017-05-16 13:45","title":"顾客点8只扇贝被收1048元 饭店称\u201c明码标价\u201d","description":"搜狐社会","picUrl":"http://photocdn.sohu.com/20170516/Img493203815_ss.jpeg","url":"http://news.sohu.com/20170516/n493203813.shtml"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-05-19 09:42
         * title : “解冻民族资产”骗局升级 投65元坐收几十万？
         * description : 搜狐社会
         * picUrl :
         * url : http://news.sohu.com/20170519/n493603575.shtml
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
