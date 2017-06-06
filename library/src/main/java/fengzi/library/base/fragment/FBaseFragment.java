package fengzi.library.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fengzi.library.tool.FragmentUtil;
import fengzi.library.tool.LogUtil;
import fengzi.library.tool.ToastUtil;

/**
 * 
 * @类功能说明:(fragment父类)
 * @author yff
 * @date 2016年10月14日 上午11:18:22
 * @version V2.0
 */
public abstract class FBaseFragment extends Fragment {

	FragmentUtil fragmentUtil;

	/**
	 * 获取view 资源id
	 * @return
	 */
	protected abstract int getViewId();
	
	/**
	 * 初始化
	 * @return
	 */
	protected abstract void initView();
	
	protected void toast(String msg) {

		ToastUtil.show(getPActivity(), msg);
	}

	protected void log(String msg) {
		LogUtil.d(msg);
	}

	protected void log(String tag, String msg) {
		LogUtil.d(tag, msg);
	}

	@SuppressWarnings("unchecked")
	public <E extends View> E findViewById(int resId) {

		return (E) getView().findViewById(resId);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int resId = getViewId();
		if(resId > 0){
			return inflater.inflate(resId, null);
		}else{
			return super.onCreateView(inflater, container, savedInstanceState);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	protected Context getPContext() {

		return getContext();
	}

	protected FragmentActivity getPActivity() {

		return getActivity();
	}

	/**
	 * 添加需要显示fragment
	 * 
	 * @param fragment
	 */
	protected void addFragment(Fragment fragment) {
		if (fragmentUtil == null) {
			fragmentUtil = new FragmentUtil(getPActivity());
		}
		fragmentUtil.addFragment(fragment);
	}

	/**
	 * 设置fragment 的资源id
	 * 
	 * @param resId
	 */
	protected void setFragmentId(int resId) {
		if (fragmentUtil == null) {
			fragmentUtil = new FragmentUtil(getPActivity());
		}
		fragmentUtil.setFragmentResId(resId);
	}

	/**
	 * 需要显示fragment
	 * 
	 * @param index
	 */
	protected void showFrl(int index) {
		if (fragmentUtil == null) {
			LogUtil.d("=====fragmentUtil is  null ======");
			return;
		}
		fragmentUtil.showFrl(index);
	}

	/**
	 * 获取当前前显示分fragment
	 * 
	 * @return
	 */
	protected Fragment getCurrentFrl() {
		if (fragmentUtil == null) {
			LogUtil.e("=====fragmentUtil is  null ======");
			return null;
		}
		return fragmentUtil.getCurrentFrl();
	}
}
