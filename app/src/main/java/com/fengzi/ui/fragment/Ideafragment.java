package com.fengzi.ui.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.fengzi.test.R;

import fengzi.library.base.fragment.FBaseFragment;
import fengzi.library.mail.SendMail;
import fengzi.library.tool.PixTool;

/**
 * 作者: {yff} time: 2017/3/30  09:58
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{意见}
 */

public class Ideafragment extends FBaseFragment {

    EditText et_tel,et_content;

    @Override
    protected int getViewId() {

        return R.layout.ac_idea;
    }

    @Override
    protected void initView() {
        init();

    }

    void init(){
        et_tel = findViewById(R.id.et_idea_tel);
        et_content = findViewById(R.id.et_idea_content);
        findViewById(R.id.btn_feelback).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String content = et_content.getText().toString();
                if(TextUtils.isEmpty(content)){
                    toast("反馈内容不能为空");
                    return;
                }
                SendMail.sendMail("demo",et_tel.getText().toString(),content, PixTool.getPhoneW(getActivity()),PixTool.getPhoneH(getActivity()));
            }
        });
    }
}
