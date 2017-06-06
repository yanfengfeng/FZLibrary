package fengzi.library.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fengzi.library.interfaces.BaseUtilInterface;
import fengzi.library.tool.FragmentUtil;
import fengzi.library.tool.LogUtil;
import fengzi.library.tool.ToastUtil;


/**
 * @类功能说明:(activity的父类)
 * @author yff
 * @date 2016年10月14日 上午11:05:49
 * @version V2.0
 */

public class FBaseActivity extends AppCompatActivity implements BaseUtilInterface {

	FragmentUtil fragmentUtil;
	
 
	public <E extends View> E findViewId(int resId) {

		return (E) findViewById(resId);
	}

	@Override
	public void log(String msg) {
		LogUtil.d(msg);
	}

	@Override
	public void toast(String msg){
		
		ToastUtil.show(this, msg);
	}

	@Override
	public int getScreemHeight(boolean isHeight) {
		return 0;
	}

	@Override
	public boolean isEmpty(Object o) {

		return false;
	}


	/**
	 * 添加需要显示fragment
	 * @param fragment
	 */
	protected void addFragment(Fragment fragment) {
		if(fragmentUtil == null){
			fragmentUtil = new FragmentUtil(this);
		}
		fragmentUtil.addFragment(fragment);
	}
	
	/**
	 * 设置fragment 的资源id
	 * @param resId
	 */
	protected void setFragmentId(int resId){
		if(fragmentUtil == null){
			fragmentUtil = new FragmentUtil(this);
		}
		fragmentUtil.setFragmentResId(resId);
	}

	/**
	 * 需要显示fragment
	 * @param index
	 */
	protected void showFragment(int index) {
		if(fragmentUtil == null){
			LogUtil.d("=====fragmentUtil is  null ======");
			return;
		}
		fragmentUtil.showFrl(index);
	}
	/**
	 * 获取当前前显示分fragment
	 * @return
	 */
	protected Fragment getCurrentFrl() {
		if(fragmentUtil == null){
			LogUtil.d("=====fragmentUtil is  null ======");
			return null;
		}
		return fragmentUtil.getCurrentFrl();
	}

	public void gotoActivity(Class clas){
		gotoActivity(clas,null,null,-1);
	}

	public void gotoActivity(Class clas,Bundle bundle){
		gotoActivity(clas,bundle,null,-1);
	}

	public void gotoActivity(Class clas,Intent intent){
		gotoActivity(clas,null,intent,-1);
	}

	public void gotoActivity(Class clas,Intent intent,int reqestCode){
		gotoActivity(clas,null,intent,reqestCode);
	}

	public void gotoActivity(Class clas,Bundle bundle,int reqestCode){
		gotoActivity(clas,bundle,null,reqestCode);
	}

	public void gotoActivity(Class clas,Bundle bundle,Intent intent){
		gotoActivity(clas,bundle,intent,-1);
	}

	/**
	 * @param clas 跳转过去activity
	 * @param bundle 数据携带者
	 * @param intent
	 * @param reqestCode 默认为-1 ,startActivity这个方式跳转 ,否则startActivityForResult这种跳转
	 */
	public void gotoActivity(Class clas,Bundle bundle,Intent intent,int reqestCode){

		if(intent == null){
			intent = new Intent();
			intent.setClass(this,clas);
		}else if(intent.getClass() == null){
			intent.setClass(this,clas);
		}
		if(bundle != null){
			intent.putExtras(bundle);
		}
		if(reqestCode != -1){
			startActivityForResult(intent,reqestCode);
		}else{
			startActivity(intent);
		}
	}
}
