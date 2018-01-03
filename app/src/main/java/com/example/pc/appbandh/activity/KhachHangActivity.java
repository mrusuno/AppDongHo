package com.example.pc.appbandh.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.appbandh.R;
import com.example.pc.appbandh.ultil.CheckConnection;
import com.example.pc.appbandh.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

public class KhachHangActivity extends AppCompatActivity {
    EditText edttenkh,edtemail,edtsdt;
    Button btxn,bttv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_kh);
        Anhxa();
        bttv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            EvBtXN();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"bạn hãy kiểm tra lại kết nối");
        }
    }
    private void EvBtXN() {
        btxn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten=edttenkh.getText().toString().trim();
                final String sdt=edtsdt.getText().toString().trim();
                final String email=edtemail.getText().toString().trim();
                if (edttenkh.length()>0&& edtsdt.length()>0&&edtemail.length()>0){
                    RequestQueue rQ= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stQ=new StringRequest(Request.Method.POST, Server.duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String response) {
                            Log.d("iddonhang",response);
                            if (Integer.parseInt(response)>0){
                              RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdanctdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm thành công");
                                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng bạn bị lỗi");

                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for (int i=0;i<MainActivity.manggiohang.size();i++){
                                            JSONObject jsonObject=new JSONObject();
                                            try {
                                                jsonObject.put("idkh",response);
                                                jsonObject.put("idsp",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensp",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("soluong",MainActivity.manggiohang.get(i).getSoluong());
                                                jsonObject.put("giasp",MainActivity.manggiohang.get(i).getGiasp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);

                                        }
                                        HashMap<String,String> hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                requestQueue.add(stringRequest);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("tenkh", ten);
                            hashMap.put("sdt", sdt);
                            hashMap.put("email", email);
                            return hashMap;
                        }
                    };
                    rQ.add(stQ);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Xin hãy kiểm tra lại");
                }
            }
        });
    }

    private void Anhxa() {
        edttenkh= (EditText) findViewById(R.id.edttenkh);
        edtsdt= (EditText) findViewById(R.id.edtsdt);
        edtemail= (EditText) findViewById(R.id.edtemail);
        btxn= (Button) findViewById(R.id.btxn);
        bttv= (Button) findViewById(R.id.bttv);
    }
}
