package fengzi.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 
 * @类功能说明:(自定义gridview,不带滑动) 
 * @author yff
 * @date 2016年10月17日 下午1:40:36 
 * @version V2.0
 */
public class FGridView extends GridView {
	public FGridView(Context context) {
		super(context);
	}

	public FGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int expandSpec = MeasureSpec.makeMeasureSpec(

		Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);

	}

}
