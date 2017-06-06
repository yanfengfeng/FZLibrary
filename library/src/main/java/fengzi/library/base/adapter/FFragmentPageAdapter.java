package fengzi.library.base.adapter;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能说明:(带有滑动的fragmetn的适配器)
 * @author yff
 * @date 2016年10月14日 上午9:26:57
 * @version V1.0
 */
public class FFragmentPageAdapter extends FragmentStatePagerAdapter {

	List<Fragment> listFg = new ArrayList<Fragment>();

	public FFragmentPageAdapter(FragmentManager fm, List<Fragment> listFg) {
		super(fm);
		this.listFg = listFg;
	}

	public FFragmentPageAdapter(FragmentActivity activity, List<Fragment> listFg) {
		this(activity.getSupportFragmentManager(), listFg);
	}

	public FFragmentPageAdapter(Fragment fragment, List<Fragment> listFg) {
		this(fragment.getChildFragmentManager(), listFg);
	}

	public FFragmentPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {

		return listFg == null ? 0 : listFg.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}

	@Override
	public void finishUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.finishUpdate(container);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
		// TODO Auto-generated method stub
		super.restoreState(state, loader);
	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return super.saveState();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
	}

	@Override
	public void startUpdate(ViewGroup container) {
		// TODO Auto-generated method stub
		super.startUpdate(container);
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public Fragment getItem(int arg0) {

		return listFg.get(arg0);
	}

}
