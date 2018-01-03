package com.example.pc.appbandh.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pc.appbandh.R;
import com.example.pc.appbandh.adapter.GioHangAdapter;
import com.example.pc.appbandh.ultil.CheckConnection;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView listView;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btthanhtoan,bttieptucmua;
    Toolbar toolbar;
    GioHangAdapter gioHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        Anhxa();
        ActionToobar();
        CheckData();
        EvUltil();
        EvButton();
        CactchOnItemListView();
    }
    private void CactchOnItemListView() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,  final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GioHangActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.manggiohang.size() <=0) {
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.manggiohang.remove(position);
                            gioHangAdapter.notifyDataSetChanged();
                            EvUltil();
                            if(MainActivity.manggiohang.size() <=0) {
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else {
                                txtthongbao.setVisibility(View.INVISIBLE);
                                gioHangAdapter.notifyDataSetChanged();
                                EvUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gioHangAdapter.notifyDataSetChanged();
                        EvUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }


    private void EvButton() {
        bttieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(it);
            }
        });
        btthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size()>0){
                    Intent it=new Intent(getApplicationContext(),KhachHangActivity.class);
                    startActivity(it);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm");
                }
            }
        });
    }

    public static void EvUltil() {
        int tongtien=0;
        for (int i=0;i<MainActivity.manggiohang.size();i++){
            tongtien+=MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat dF=new DecimalFormat("###,###,###");
        txttongtien.setText(dF.format(tongtien)+"đ");
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

    private void CheckData() {
        if (MainActivity.manggiohang.size()<=0){
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listView.setVisibility(View.INVISIBLE);
        }else {
            gioHangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
        }
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
        listView= (ListView) findViewById(R.id.lv_gh);
        txtthongbao= (TextView) findViewById(R.id.tv_thongbao);
        txttongtien= (TextView) findViewById(R.id.tv_tongtien);
        btthanhtoan= (Button) findViewById(R.id.thanhtoan);
        bttieptucmua= (Button) findViewById(R.id.tieptucmuahang);
        toolbar= (Toolbar) findViewById(R.id.toobar_gh);
        gioHangAdapter=new GioHangAdapter(GioHangActivity.this,MainActivity.manggiohang);
        listView.setAdapter(gioHangAdapter);
    }

}
