package fengzi.library.base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @类功能说明:(viewpage使用适配器,传过来的view的集合) 
 * @author yff
 * @date 2016年10月18日 下午4:57:21 
 * @version V2.0
 */
public class FBaseViewPageAdapter extends PagerAdapter {

	List<View> listView = new ArrayList<View>();
	
	public FBaseViewPageAdapter(List<View> listView ){
		this.listView = listView;
	}
	
	@Override
	public int getCount() {
		 
		return listView == null ? 0 : listView.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {

		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(listView.get(position));
		return listView.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(listView.get(position));
	}

}
