package com.fengzi.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengzi.bean.BluetoothBean;
import com.fengzi.test.R;
import com.fengzi.ui.bluetooth.BluetoothAc;

import java.io.IOException;
import java.util.List;

/**
 * 作者: {yff} time: 2017/3/31  14:45
 * 版本 viersionCode:{1} viersionName:{V1.0.0}
 * 备注{蓝牙}
 */

public class MyBluetoothAdapter extends RecyclerView.Adapter<MyBluetoothAdapter.BluetoothHold> {

    List<BluetoothBean> listData;

    Activity activity;


    public MyBluetoothAdapter(Activity activity,List<BluetoothBean> listData){
        this.listData = listData;
        this.activity = activity;
    }

    @Override
    public MyBluetoothAdapter.BluetoothHold onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BluetoothHold(LayoutInflater.from(activity).inflate(R.layout.item_bluetooth,null));
    }

    @Override
    public void onBindViewHolder(BluetoothHold holder, int position) {
        holder.tv_anme.setText(listData.get(position).name + "\t\t" + listData.get(position).address);
    }

    @Override
    public int getItemCount() {

        return listData == null ? 0 : listData.size();
    }

    public class  BluetoothHold extends  RecyclerView.ViewHolder{

        TextView tv_anme;

        public BluetoothHold(View itemView) {
            super(itemView);
            tv_anme = (TextView) itemView.findViewById(R.id.tv_bluetooth_name);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    ((BluetoothAc)activity).onClickListern(getAdapterPosition(),listData.get( getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemCickLisetern{

        public void  onClickListern(int postion,BluetoothBean s) throws IOException;
    }
}
