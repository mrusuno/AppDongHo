package com.example.pc.appbandh.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.appbandh.activity.ChiTietSPActivity;
import com.example.pc.appbandh.model.sanpham;
import com.example.pc.appbandh.ultil.CheckConnection;
import com.example.pc.appbandh.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by PC on 10/21/2017.
 */

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<sanpham> arraysanpham;

    public SanphamAdapter(Context context, ArrayList<sanpham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanpham,null);
        ItemHolder itemHolder=new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        sanpham sanpham=arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText("Giá :"+ decimalFormat.format(sanpham.getGiasanpham())+"đ");
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(holder.imghinhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham,txtgiasanpham;

        public ItemHolder(View itemView) {
            super(itemView);
            imghinhsanpham= (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txtgiasanpham = (TextView) itemView.findViewById(R.id.textviewgiasanphamsanpham);
            txttensanpham= (TextView) itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, ChiTietSPActivity.class);
                    intent.putExtra("thongtinsp",arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraysanpham.get(getPosition()).getTensanpham());
                    context.startActivity(intent);
                }
            });
        }

    }
}
