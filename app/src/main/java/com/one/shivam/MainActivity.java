package com.one.shivam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.one.shivam.Activity.ViewListData;
import com.one.shivam.Adapter.RoutDataAdapter;
import com.one.shivam.Database.DatabaseHelper;
import com.one.shivam.Model.CommonModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    String strJson="{ \"Employee\" :[" +
            "{\"number\":\"728491\",\"time\":\"10AM\",\"action\":\"Details\"}," +
            "{\"number\":\"728492\",\"time\":\"11AM\",\"action\":\"Details\"}," +
            "{\"number\":\"728493\",\"time\":\"12AM\",\"action\":\"Details\"}," +
            "{\"number\":\"728494\",\"time\":\"09AM\",\"action\":\"Details\"}" +
            "] }";
    List<CommonModel> list = new ArrayList<>();
    List<CommonModel> getlist = new ArrayList<>();
    RecyclerView rv_directory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        rv_directory = findViewById(R.id.rv_directory);

        RecyclerView.LayoutManager rvDir = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        rv_directory.setLayoutManager(rvDir);

        JSONObject jsonRootObject = null;
        try {
            jsonRootObject = new JSONObject(strJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = jsonRootObject.optJSONArray("Employee");
        for(int i=0; i < jsonArray.length(); i++){
            CommonModel cm = new CommonModel();
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            cm.number = jsonObject.optString("number");
            cm.time = jsonObject.optString("time");
            cm.action = jsonObject.optString("action");

            list.add(cm);
        }
        getlist = db.getShowRecord();
        if(getlist.size() > 0){
            Log.e("TEST","List full :"+getlist.size());
            RoutDataAdapter adapter = new RoutDataAdapter(MainActivity.this,getlist);
            rv_directory.setAdapter(adapter);
        }else {
            long a = db.InsertableData(list);
            Log.e("TEST","aaa : "+a);

        }
    }

    public void getShowData(String number, String time, String action){
        Intent intent = new Intent(MainActivity.this, ViewListData.class);
        intent.putExtra("N",number);
        intent.putExtra("T",time);
        intent.putExtra("A",action);
        startActivity(intent);
    }
}