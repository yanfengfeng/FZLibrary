package com.fengzi.ui.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fengzi.test.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fengzi.library.base.activity.FBaseActivity;

/**
 * butterknife 注解的使用
 配置如下:
 dependencies {
 compile 'com.jakewharton:butterknife:8.6.0'
 下面这两个有一个可以在你的项目里面用,至于哪个不确定
 apt 'com.jakewharton:butterknife-compiler:8.6.0'
 annotationProcessor 'com.jakewharton:butterknife-compiler:8.6.0'
 }


 buildscript {
 repositories {
 mavenCentral()
 }
 dependencies {
 classpath 'com.jakewharton:butterknife-gradle-plugin:8.6.0'
 }
 }
 apply plugin: 'com.android.library' 如果是配置在引用项目里,使用这个
 apply plugin: 'com.jakewharton.butterknife'


 */
public class ButterknifeAc extends FBaseActivity {

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindString(R.string.app_name)
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acbutterknife);
        ButterKnife.bind(this);
        setTitle("Butterknife");
        tv_name.setText(msg);
    }

    @OnClick({R.id.tv_click,R.id.tv_click1})
    public void clock(View v) {
        if(v.getId() == R.id.tv_click){
            toast("clcik.事件..");
        }else if(v.getId() == R.id.tv_click1){
            toast("点击事件1...");
        }
    }
}
