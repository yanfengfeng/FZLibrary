package fengzi.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 
 * @类功能说明:(自定义listview,不带滑动的listview) 
 * @author yff
 * @date 2016年10月17日 下午1:39:54 
 * @version V2.0
 */
public class FListView extends ListView{

    public FListView(Context context) {
        super(context);
    }
    public FListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
