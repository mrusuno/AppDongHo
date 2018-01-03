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
 * Created by PC on 10/22/2017.
 */

public class NamAdapter extends BaseAdapter {
    Context context;
    ArrayList<sanpham> arraynam;

    public NamAdapter(Context context, ArrayList<sanpham> arraynam) {
        this.context = context;
        this.arraynam = arraynam;
    }

    @Override
    public int getCount() {
        return arraynam.size();
    }

    @Override
    public Object getItem(int i) {
        return arraynam.get(i);
    }
    public class  ViewHolder{
        public TextView txttennam,txtgianam,txtmotanam;
        public ImageView imgnam;
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
            view=inflater.inflate(R.layout.dong_nam,null);
            viewHolder.txttennam= (TextView) view.findViewById(R.id.textviewnam);
            viewHolder.txtgianam= (TextView) view.findViewById(R.id.textviewgianam);
            viewHolder.txtmotanam= (TextView) view.findViewById(R.id.textviewmotanam);
            viewHolder.imgnam= (ImageView) view.findViewById(R.id.imageviewnam);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        sanpham sanpham= (sanpham) getItem(i);
        viewHolder.txttennam.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        viewHolder.txtgianam.setText("Giá :"+ decimalFormat.format(sanpham.getGiasanpham())+"đ");
        viewHolder.txtmotanam.setMaxLines(2);
        viewHolder.txtmotanam.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotanam.setText(sanpham.getMota());
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(viewHolder.imgnam);
        return view;
    }

}
