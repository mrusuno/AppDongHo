package com.example.pc.appbandh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.appbandh.R;
import com.example.pc.appbandh.activity.GioHangActivity;
import com.example.pc.appbandh.activity.MainActivity;
import com.example.pc.appbandh.model.giohang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by PC on 10/27/2017.
 */

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<giohang> arraygiohang;

    public GioHangAdapter(Context context, ArrayList<giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imgiohang;
        public Button btminus,btvalues,btplus;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang= (TextView) view.findViewById(R.id.tvtengiohang);
            viewHolder.txtgiagiohang= (TextView) view.findViewById(R.id.tvgiagiohang);
            viewHolder.imgiohang= (ImageView) view.findViewById(R.id.imgvgiohang);
            viewHolder.btminus= (Button) view.findViewById(R.id.buttonminus);
            viewHolder.btvalues= (Button) view.findViewById(R.id.buttonvalue);
            viewHolder.btplus= (Button) view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        giohang giohang= (giohang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp()) + "đ");
        Picasso.with(context).load(giohang.getHinhanhsp())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(viewHolder.imgiohang);
        viewHolder.btvalues.setText(giohang.getSoluong() + "");
        int sl = Integer.parseInt(viewHolder.btvalues.getText().toString());
        if (sl >= 10) {
            viewHolder.btplus.setVisibility(View.INVISIBLE);
            viewHolder.btminus.setVisibility(View.VISIBLE);
        } else if (sl <= 1) {
            viewHolder.btminus.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.btplus.setVisibility(View.VISIBLE);
            viewHolder.btminus.setVisibility(View.VISIBLE);
        }
        viewHolder.btplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slm = Integer.parseInt(viewHolder.btvalues.getText().toString()) + 1;
                int slht = MainActivity.manggiohang.get(i).getSoluong();
                int giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluong(slm);
                int giamoinhat = (giaht * slm) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                GioHangActivity.EvUltil();
                if (slm > 9) {
                    viewHolder.btplus.setVisibility(View.INVISIBLE);
                    viewHolder.btminus.setVisibility(View.VISIBLE);
                    viewHolder.btvalues.setText(String.valueOf(slm));
                } else {
                    viewHolder.btminus.setVisibility(View.VISIBLE);
                    viewHolder.btplus.setVisibility(View.VISIBLE);
                    viewHolder.btvalues.setText(String.valueOf(slm));
                }
            }
        });
        viewHolder.btminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slm = Integer.parseInt(viewHolder.btvalues.getText().toString()) - 1;
                int slht = MainActivity.manggiohang.get(i).getSoluong();
                int giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluong(slm);
                int giamoinhat = (giaht * slm) / slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                GioHangActivity.EvUltil();
                if (slm < 2) {
                    viewHolder.btminus.setVisibility(View.INVISIBLE);
                    viewHolder.btplus.setVisibility(View.VISIBLE);
                    viewHolder.btvalues.setText(String.valueOf(slm));
                } else {
                    viewHolder.btminus.setVisibility(View.VISIBLE);
                    viewHolder.btplus.setVisibility(View.VISIBLE);
                    viewHolder.btvalues.setText(String.valueOf(slm));
                }
            }
        });
        return view;
    }
}
