package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private static final String JSON_URL = "https://webd.savonia.fi/home/ktkoiju/j2me/test_json.php?dates&delay=1";
        ListView listView;
        List<Demo> DemoList;
        Button btn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = (ListView)findViewById(R.id.list1);
            btn=(Button)findViewById(R.id.btn);
            DemoList = new ArrayList<>();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataList();
                }
        });
        }
        private void dataList() {
            StringRequest stringrequest = new StringRequest(Request.Method.GET, JSON_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jarray = new JSONArray(response);
                                for (int i = 0; i < jarray.length(); i++) {
                                    JSONObject demoObject = jarray.getJSONObject(i);
                                    Demo demo = new Demo(demoObject.getString("nimi"), demoObject.getString("pvm").substring(0,10));
                                    DemoList.add(demo);
                                }
                                ListViewAdapter adapter = new ListViewAdapter(DemoList, getApplicationContext());
                                //adding the adapter to listView
                                listView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), ("No internet connection"), Toast.LENGTH_SHORT).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringrequest);
        }
    }
