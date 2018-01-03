package com.example.pc.appbandh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.appbandh.model.loaisanpham;
import com.example.pc.appbandh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PC on 10/19/2017.
 */

public class LoaispAdapter extends BaseAdapter {
    ArrayList<loaisanpham> arrayListloaisanpham;
    Context context;
    public LoaispAdapter(ArrayList<loaisanpham> arrayListloaisanpham, Context context) {
        this.arrayListloaisanpham = arrayListloaisanpham;
        this.context = context;
    }



    @Override
    public int getCount() {
        return arrayListloaisanpham.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListloaisanpham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class viewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder viewHolder=null;
        if(view==null) {
            viewHolder=new viewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_lv_loaisp,null);
            viewHolder.txttenloaisp= (TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp= (ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);

        }else {
            viewHolder= (viewHolder) view.getTag();

        }
        loaisanpham loaisanpham= (loaisanpham) getItem(i);
        viewHolder.txttenloaisp.setText(loaisanpham.getTenloaisp());
        Picasso.with(context).load(loaisanpham.getHinhanhloaisp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(viewHolder.imgloaisp);
        return view;
    }
}
