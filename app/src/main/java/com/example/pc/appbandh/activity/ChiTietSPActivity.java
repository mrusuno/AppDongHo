package com.example.pc.appbandh.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pc.appbandh.model.giohang;
import com.example.pc.appbandh.model.sanpham;

import com.example.pc.appbandh.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSPActivity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageView;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button button;
    int ID =0;
    int Idlsp=0;
    String Tensanpham="";
    int Giasanpham=0;
    String Mota="";
    String Hinhanhsanpham="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sp);
        Anhxa();
        ActionToobar();
        GetInformation();
        CatchEventSpinner();
        //EvButton();
        EventButton();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugiohang,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugh:
                Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() >0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for(int i =0;i<MainActivity.manggiohang.size();i++ ){
                        if(MainActivity.manggiohang.get(i).getIdsp() == ID){
                            MainActivity.manggiohang.get(i).setSoluong(MainActivity.manggiohang.get(i).getSoluong() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluong()>=10){
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(Giasanpham * MainActivity.manggiohang.get(i).getSoluong());
                            exists = true;
                        }
                    }
                    if (exists == false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        int Giamoi = soluong * Giasanpham;
                        MainActivity.manggiohang.add(new giohang(ID,Tensanpham,Giamoi,Hinhanhsanpham,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    int Giamoi = soluong * Giasanpham;
                    MainActivity.manggiohang.add(new giohang(ID,Tensanpham,Giamoi,Hinhanhsanpham,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
            }
        });
    }
    /*private void EvButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0){
                    int sluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists =false;
                    for (int i=0;i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getIdsp()==ID){
                            MainActivity.manggiohang.get(i).getSoluong(MainActivity.manggiohang.get(i).getSoluong()+sluong);
                            if (MainActivity.manggiohang.get(i).getSoluong()>=10){
                                MainActivity.manggiohang.get(i).setSoluong(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(Giasanpham*MainActivity.manggiohang.get(i).getSoluong());
                            exists=true;
                        }
                        if (exists==false){
                            int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                            int giamoi=sl* Giasanpham;
                            MainActivity.manggiohang.add(new giohang(ID,Tensanpham,giamoi,Hinhanhsanpham,sl));
                        }
                    }
                }else {
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    int giamoi=sl* Giasanpham;
                    MainActivity.manggiohang.add(new giohang(ID,Tensanpham,giamoi,Hinhanhsanpham,sl));
                }
                Intent intent =new Intent(getApplicationContext(),com.example.pc.appbandh.activity.GioHangActivity.class);
                startActivity(intent);
            }
        });
    }*/
    private void CatchEventSpinner() {
        Integer[ ] solg=new Integer[ ]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,solg);
        spinner.setAdapter(arrayAdapter);
    }
    private void GetInformation() {
        sanpham sanpham= (sanpham) getIntent().getSerializableExtra("thongtinsp");
        ID=sanpham.getId();
        Idlsp=sanpham.getIdlsp();
        Tensanpham=sanpham.getTensanpham();
        Giasanpham=sanpham.getGiasanpham();
        Mota=sanpham.getMota();
        Hinhanhsanpham=sanpham.getHinhanhsanpham();
        txtten.setText(Tensanpham);
        DecimalFormat decimalFormat=new DecimalFormat("###,###,###");
        txtgia.setText("Giá :"+decimalFormat.format(Giasanpham)+"đ");
        txtmota.setText(Mota);
        Picasso.with(getApplicationContext()).load(Hinhanhsanpham)
                .placeholder(R.drawable.loading)
                .error(R.drawable.loading)
                .into(imageView);
    }

    private void ActionToobar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                
            }
        });
    }

    private void Anhxa() {
        toolbar= (Toolbar) findViewById(R.id.toobar_ctsp);
        imageView= (ImageView) findViewById(R.id.img_ctsp);
        txtten= (TextView) findViewById(R.id.textten_ctsp);
        txtgia=(TextView) findViewById(R.id.textviewgia_ctspsp);
        txtmota=(TextView) findViewById(R.id.textviewmt_ctsp);
        spinner= (Spinner) findViewById(R.id.spinner);
        button= (Button) findViewById(R.id.button_ctsp);
    }
}
