package com.fengzi.ui.note;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fengzi.test.R;

import fengzi.library.base.activity.FBaseActivity;

/**
 * 几种注解的使用
 */
public class NoteAc extends FBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_note);
       setTitle("注解");
    }

    public  void onClick(View v){

        switch (v.getId()){

            case R.id.btn_butterknife:
                gotoActivity(ButterknifeAc.class);
                break;

        }
    }


}
