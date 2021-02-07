package com.one.shivam.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.one.shivam.Database.DatabaseHelper;
import com.one.shivam.R;
import com.one.shivam.Service.ForegroundService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewListData extends AppCompatActivity {
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_data);

        db = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        String number = bundle.getString("N");
        String time = bundle.getString("T");
        String action = bundle.getString("A");

        ((TextView) findViewById(R.id.txt_num)).setText(number);
        ((TextView) findViewById(R.id.txt_t)).setText(time);
        ((TextView) findViewById(R.id.txt_a)).setText(action);

        ((Button) findViewById(R.id.btn_accept)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(ViewListData.this,TripStart.class));

                                Intent serviceIntent = new Intent(ViewListData.this, ForegroundService.class);
                                serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
                                ContextCompat.startForegroundService(ViewListData.this, serviceIntent);

                                SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss.S");
                                String startTime = dateFormat.format(new Date()).toString();
                                Log.e("TEST","get Time :"+startTime);

                                long id = db.InserTime(startTime,"");
                                Log.e("TEST","Row Inserted :"+id);

                            }
                        }, 1000);
            }
        });
    }
}