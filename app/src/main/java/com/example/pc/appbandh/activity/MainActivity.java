package com.example.pc.appbandh.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appbandh.adapter.LoaispAdapter;
import com.example.pc.appbandh.adapter.SanphamAdapter;
import com.example.pc.appbandh.model.giohang;
import com.example.pc.appbandh.model.loaisanpham;
import com.example.pc.appbandh.model.sanpham;
import com.example.pc.appbandh.ultil.CheckConnection;
import com.example.pc.appbandh.ultil.Server;
import com.example.pc.appbandh.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toobar ;
    ViewFlipper viewflipper;
    RecyclerView recyclerviewmanhinhchinh;
    NavigationView navigationview;
    ListView lisviewmenu;
    DrawerLayout drawerlayout;
    ArrayList<loaisanpham> mangloaisanpham;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static  ArrayList<giohang> manggiohang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaisp();
            GetDuLieusanpham();
            CatchOnItemListView();
        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"KT lại kết nối");
            finish();
        }


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

    private void CatchOnItemListView() {
        lisviewmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"hãy đăng nhập lại");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,NamActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisanpham.get(i).getId());
                            startActivity(intent);

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"hãy đăng nhập lại");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,NuActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisanpham.get(i).getId());
                            startActivity(intent);

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"hãy đăng nhập lại");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,ThongtinActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisanpham.get(i).getId());
                            startActivity(intent);

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"hãy đăng nhập lại");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,ThongtinActivity.class);
                            intent.putExtra("idloaisanpham",mangloaisanpham.get(i).getId());
                            startActivity(intent);

                        }else {
                            CheckConnection.ShowToast_Short(getApplicationContext(),"hãy đăng nhập lại");
                        }
                        drawerlayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieusanpham() {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.duongdansanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    int ID =0;
                    int Idlsp=0;
                    String Tensanpham="";
                    int Giasanpham=0;
                    String Mota="";
                    String Hinhanhsanpham="";
                    for (int i = 0;i < response.length(); i++){
                        try {
                            JSONObject jsonObject =response.getJSONObject(i);
                            ID=jsonObject.getInt("id");
                            Idlsp=jsonObject.getInt("idlsp");
                            Tensanpham=jsonObject.getString("tensanpham");
                            Giasanpham=jsonObject.getInt("giasanpham");
                            Mota=jsonObject.getString("mota");
                            Hinhanhsanpham=jsonObject.getString("hinhanhsanpham");
                            mangsanpham.add(new sanpham(ID,Idlsp,Tensanpham,Giasanpham,Mota,Hinhanhsanpham));
                            sanphamAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaisp() {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.duongdanloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenloaisp = jsonObject.getString("tenloaisp");
                            hinhanhloaisp = jsonObject.getString("hinhanhloaisp");
                            mangloaisanpham.add(new loaisanpham(id, tenloaisp, hinhanhloaisp));
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    //mangloaisanpham.add(3,new loaisanpham(0,"Giỏ Hàng","http://aice.com.vn/Images/icon_LargeShoppingCart.jpg"));
                    mangloaisanpham.add(3,new loaisanpham(0,"Thông Tin","https://previews.123rf.com/images/siamimages/siamimages1504/siamimages150400206/38412478-Information-icon-Stock-Photo.jpg"));
                    //mangloaisanpham.add(5,new loaisanpham(0,"Liên Hệ","http://quanlegging.com/wp-content/uploads/2015/07/Call-icon-blue.png"));
                    //mangloaisanpham.add(6,new loaisanpham(0,"Cài Đặt","https://fptshop.com.vn/Uploads/images/2015/Tin-Tuc/05/18/cac_ky_hieu_va_icon_tren_Apple_Watch(2).jpg"));
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFlipper(){
        ArrayList<String> mangquangcao =new ArrayList<>();
        mangquangcao.add("https://dantricdn.com/thumb_w/640/2017/photo-3-1492047610256.jpg");
        mangquangcao.add("https://dantricdn.com/thumb_w/640/2016/photo-2-1482900391922.jpg");
        for (int i = 0;i<mangquangcao.size();i++) {
            ImageView imageview = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageview);
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);
            viewflipper.addView(imageview);
        }
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewflipper.setOutAnimation(animation_slide_in);
        viewflipper.setOutAnimation(animation_slide_out);
    }
    private void ActionBar(){
        setSupportActionBar(toobar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toobar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toobar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerlayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa(){
        toobar= (Toolbar) findViewById(R.id.toolbarmanhinhchinh);
        viewflipper= (ViewFlipper) findViewById(R.id.viewflipper);
        recyclerviewmanhinhchinh= (RecyclerView) findViewById(R.id.recyclerview);
        navigationview= (NavigationView) findViewById(R.id.navigationview);
        lisviewmenu= (ListView) findViewById(R.id.listviewmenu);
        drawerlayout= (DrawerLayout) findViewById(R.id.draverlayout);
        mangloaisanpham=new ArrayList<>();
        mangloaisanpham.add(0,new loaisanpham(0,"Trang Chính","http://findicons.com/files/icons/1580/devine_icons_part_2/128/home_w.png"));
        loaispAdapter=new LoaispAdapter(mangloaisanpham,getApplicationContext());
        lisviewmenu.setAdapter(loaispAdapter);
        mangsanpham =new ArrayList<>();
        sanphamAdapter =new SanphamAdapter(getApplicationContext(),mangsanpham);
        recyclerviewmanhinhchinh.setHasFixedSize(true);
        recyclerviewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerviewmanhinhchinh.setAdapter(sanphamAdapter);
        if (manggiohang!=null){

        }else {
            manggiohang=new ArrayList<>();
        }
    }
}


