package com.example.pc.appbandh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.appbandh.model.sanpham;
import com.example.pc.appbandh.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by PC on 10/23/2017.
 */

public class NuAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arraynu;

    public NuAdapter(Context context, ArrayList<sanpham> arraynu) {
        this.context = context;
        this.arraynu = arraynu;
    }
    @Override
    public int getCount() {
        return arraynu.size();
    }

    @Override
    public Object getItem(int i) {
        return arraynu.get(i);
    }
    public class  ViewHolder{
        public TextView txttennu,txtgianu,txtmotanu;
        public ImageView imgnu;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view == null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_nu,null);
            viewHolder.txttennu= (TextView) view.findViewById(R.id.textviewnu);
            viewHolder.txtgianu= (TextView) view.findViewById(R.id.textviewgianu);
            viewHolder.txtmotanu= (TextView) view.findViewById(R.id.textviewmotanu);
            viewHolder.imgnu= (ImageView) view.findViewById(R.id.imageviewnu);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        sanpham sanpham= (sanpham) getItem(i);
        viewHolder.txttennu.setText(sanpham.getTensanpham());
        //viewHolder.txttennu.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgianu.setText("Giá :"+ decimalFormat.format(sanpham.getGiasanpham())+"đ");
        viewHolder.txtmotanu.setMaxLines(2);
        viewHolder.txtmotanu.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotanu.setText(sanpham.getMota());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(viewHolder.imgnu);
        return view;
    }
}
