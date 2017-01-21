package com.org.zl.welcomedemo.activity;

import android.os.Bundle;
import android.util.Log;

import com.org.zl.welcomedemo.utils.NetUtil;
import com.org.zl.welcomedemo.R;
import com.org.zl.welcomedemo.utils.ScreenUtils;
import com.org.zl.welcomedemo.base.BaseActivity;
import com.org.zl.welcomedemo.base.BaseWebView;

/**
 * 作者：朱亮 on 2017/1/20 19:31
 * 邮箱：171422696@qq.com
 * ${导航界面}(这里用一句话描述这个方法的作用)
 */

public class DaoHangActivity extends BaseActivity {
    private String url = "";
    private BaseWebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dh_activity);
        webView = (BaseWebView) findViewById(R.id.webView);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        setUrl(url4);//设置网页方法
        if(!hasFocus){//如果界面还灭加载完毕
            return;
        }
        //获取网络是否连接
        if(NetUtil.CheckNetworkState(mContext)){//如果有网络
            java.util.Random random=new java.util.Random();// 定义随机类
            int result=random.nextInt(3);// 返回[0,3)集合中的整数，注意不包括3
            switch (result){
                case 0:
                    url = "http://pic1.win4000.com/pic/e/08/f8b91258152.jpg";
                    break;
                case 1:
                    url = "http://pic1.win4000.com/pic/f/f1/3ca81257793.jpg";
                    break;
                case 2:
                    url = "http://pic1.win4000.com/pic/d/9c/daae1254766.jpg";
                    break;
            }
            setImg(url);//设置图片方法
        }else{//加载本地LOGO图片
            url = "file:///android_asset/bg_guide.png";
            setUrl(url);
        }
    }

    private void setUrl(String url) {
        webView.getWebView().loadUrl(url);//设置加载网页
    }

    private void setImg(String url) {

        Log.e("LOGSe","加载的地址是 :"+url);
        int num = ScreenUtils.getScreenWidth(mContext);//获取屏幕宽度
        Log.e("---------------------","  num = "+num);
        String body="<img  src=\"" + url + "\" width = "+num+"/>";//根据屏幕宽度动态设置图片大小自适应
        String html="<html><body>"+body+"</html></body>";
        webView.getWebView()
                .loadDataWithBaseURL(null, html, "text/html","UTF-8", null);
        webView.setZoom(false);//设置web不支持缩放(自适应)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            webView.webDestory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
