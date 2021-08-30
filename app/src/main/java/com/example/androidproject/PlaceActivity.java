package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class PlaceActivity extends AppCompatActivity {

    ImageView iv;
    RatingBar rb;
    TextView tv_name, tv_about, tv_time, tv_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        iv=findViewById(R.id.iv_image);
        rb=findViewById(R.id.rb_rate);
        tv_name=findViewById(R.id.tv_name);
        tv_about=findViewById(R.id.tv_about);
        tv_time=findViewById(R.id.tv_time);
        tv_address=findViewById(R.id.tv_address);

        Intent it=getIntent();
        String p_id=it.getExtras().getString("c-id");
        ThingsToDoDatabaseAccess db1 = ThingsToDoDatabaseAccess.getInstance(getApplicationContext());
        Cursor c=db1.getPlace(p_id);
        c.moveToFirst();
    }
}