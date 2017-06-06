package com.fengzi.ui.nfc;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fengzi.test.R;

import fengzi.library.base.activity.BaseNfcActivity;

/**
 * nfc 功能
 */
public class NfcAc extends BaseNfcActivity {

    ProgressBar bar;

    TextView tv;
    EditText et_edit;
    LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_nfc);
        initView();
    }

    void initView() {

        bar = (ProgressBar) findViewById(R.id.progressBar);
        tv = (TextView) findViewById(R.id.textView1);
        et_edit = (EditText) findViewById(R.id.et_edit);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        findViewById(R.id.btn_read).setOnClickListener(new Click());
        findViewById(R.id.btn_write).setOnClickListener(new Click());
        findViewById(R.id.btn_delete).setOnClickListener(new Click());
        findViewById(R.id.btn_read_tag).setOnClickListener(new Click());
        chage(0);
    }

    class Click implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btn_delete:
                    chage(3);
                    break;

                case R.id.btn_read:
                    readOrwriteStatue(NfcType.readId);
                    chage(0);
                    break;

                case R.id.btn_write:
                    writeCardTag(TextUtils.isEmpty(et_edit.getText()) ?  "123456" : et_edit.getText().toString());
                    chage(1);
                    break;

                case R.id.btn_read_tag:
                    readOrwriteStatue(NfcType.readTag);
                    chage(2);
                    break;
            }
        }
    }

    void chage(int index){
        et_edit.setVisibility(index == 1 ? View.VISIBLE : View.GONE);
        for (int i = 0 ; i < ll_content.getChildCount() ; i++){
            ((Button)ll_content.getChildAt(i)).setTextColor(index == i ? Color.RED : Color.BLACK);
        }
    }

    @Override
    protected void readCardId(String msg) {
        tv.setText("读取id:" + msg);
    }

    @Override
    protected void readCardTag(String msg) {
        tv.setText("读取tag:" + msg);
    }


    @Override
    protected void writeCardCallBack(boolean isSucces) {
        tv.setText("写入:" + (isSucces ? "成功" : "失败"));
    }

}
