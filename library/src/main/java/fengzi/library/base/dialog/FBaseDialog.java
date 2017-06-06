package fengzi.library.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

/**
 * 作者：${yff} on 2015/12/17 13:20
 * 功能名字: {对话框父类}
 */
public abstract class FBaseDialog extends Dialog {

	protected abstract int getViewId();
	
	protected abstract int initView();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 int resId = getViewId();
		 if(resId > 0){
			 setContentView(resId);
		 }
		 initView();
	}

    public FBaseDialog(Context context) {
        super(context);
    }

    public FBaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FBaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    //显示位置
    public void setShowLoaction(Loction loc){
        if(loc == Loction.LEFT){
            getWindow().setGravity(Gravity.LEFT);
        }else if(loc == Loction.TOP){
            getWindow().setGravity(Gravity.TOP);
        }else if(loc == Loction.RIGHT){
            getWindow().setGravity(Gravity.RIGHT);
        }else if(loc == Loction.BUTTOM){
            getWindow().setGravity(Gravity.BOTTOM);
        }else {
            getWindow().setGravity(Gravity.CENTER);
        }
    }

    public  int getScreemWidth(){
        return getWindow().getWindowManager().getDefaultDisplay().getWidth();
    }

    public  int getScreemHeight(){

        return getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }



    public enum Loction {
        LEFT, RIGHT, BUTTOM, CENTER , TOP;
    }
}
