package fengzi.library.base.adapter;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;


import java.util.List;

import fengzi.library.bean.ViewHolder;

/**
 * 
 * @类功能说明:(适配器的父类) 
 * @author yff
 * @date 2016年10月14日 下午2:49:53 
 * @version V2.0
 */
public abstract class FBaseAdapter<T> extends BaseAdapter {

    protected FragmentActivity mactivity;
    
    protected List<T> mlist;
    
    protected Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler){

        this.handler = handler;
    }

    public FBaseAdapter(FragmentActivity activity, List<T> list) {
        this.mactivity = activity;
        this.mlist = list;
    }


    public abstract  int getLayout();

    public abstract void getview(ViewHolder vh, int position, T T);
    
    @Override
    public int getCount() {

        return mlist != null ? mlist.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return mlist.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder vh = ViewHolder.get(mactivity, arg1, arg2, getLayout(), arg0);
        getview(vh, arg0, mlist.get(arg0));
        return vh.getConvertView();
    };

    public void add(T T) {
        if (T != null) {
            mlist.add(T);
            notifyDataSetChanged();
        }
    }

    public void addAll(List<? extends T> list) {
        if (list != null && list.size() > 0) {
            mlist.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void remove(T T) {
        if (T != null) {
            mlist.remove(T);
            notifyDataSetChanged();
        }
    }

    public List<T> getList() {
        return mlist;
    }

    public void setMlist(List<T> list) {
        this.mlist = list;
        notifyDataSetChanged();
    }

    public void clear() {
        mlist.clear();
    }

    protected void toast(String info){
        if(mactivity != null){
        	Toast.makeText(mactivity, "上下午对象为空", Toast.LENGTH_SHORT).show();
        }
    }

}
