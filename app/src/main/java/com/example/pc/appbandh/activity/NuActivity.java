package com.example.pc.appbandh.activity;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appbandh.adapter.NuAdapter;
import com.example.pc.appbandh.model.sanpham;
import com.example.pc.appbandh.ultil.CheckConnection;
import com.example.pc.appbandh.ultil.Server;
import com.example.pc.appbandh.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NuActivity extends AppCompatActivity {
    Toolbar toolbardhnu;
    ListView lvnu;
    NuAdapter nuAdapter;
    ArrayList<sanpham> mangnu;
    int iddhnu=0;
    int page=1;
    View footerview;
    boolean isLoading=false;
    boolean limitdata=false;
    mHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nu);
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            Anhxa();
            GetIdloaisp();
            ActionToolBar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"KT lại");
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

    private void Anhxa() {
        toolbardhnu= (Toolbar) findViewById(R.id.toolbarnu);
        lvnu= (ListView) findViewById(R.id.listviewnu);
        mangnu=new ArrayList<>();
        nuAdapter=new NuAdapter(getApplicationContext(),mangnu);
        lvnu.setAdapter(nuAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview=inflater.inflate(R.layout.progressbar,null);
        mHandler = new mHandler();

    }
    private void LoadMoreData() {
        lvnu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),ChiTietSPActivity.class);
                intent.putExtra("thongtinsp",mangnu.get(i));
                startActivity(intent);
            }
        });
        lvnu.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem+visibleItemCount==totalItemCount && totalItemCount !=0 && isLoading==false && limitdata==false){
                    isLoading =true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();

                }

            }
        });
    }
    public  class  mHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    lvnu.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public  class  ThreadData extends  Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message  message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

    private void GetIdloaisp() {
        iddhnu=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisp",iddhnu+"");
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbardhnu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardhnu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan= Server.duongdansp +String.valueOf(Page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int IDn =0;
                int Idlspn=0;
                String Tensanphamn="";
                int Giasanphamn=0;
                String Motan="";
                String Hinhanhsanphamn="";
                if(response != null&& response.length()!=2){
                    lvnu.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray=new JSONArray(response);
                        for (int i = 0; i < jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            IDn=jsonObject.getInt("id");
                            Idlspn=jsonObject.getInt("idlsp");
                            Tensanphamn=jsonObject.getString("tensanpham");
                            Giasanphamn=jsonObject.getInt("giasanpham");
                            Motan=jsonObject.getString("mota");
                            Hinhanhsanphamn=jsonObject.getString("hinhanhsanpham");
                            mangnu.add(new sanpham(IDn,Idlspn,Tensanphamn,Giasanphamn,Motan,Hinhanhsanphamn));
                            nuAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    }else {
                        limitdata=true;
                        lvnu.removeFooterView(footerview);
                        CheckConnection.ShowToast_Short(getApplicationContext(),"hết dữ liệu");

                    }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idlsp",String.valueOf(iddhnu));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
}
