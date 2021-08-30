package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Planned_Schedule_List_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planned_list);
        Intent intent=getIntent();
        int id=intent.getIntExtra("plan_id",1);
        // Toast.makeText(Planned_Schedule_List_Activity.this, "Hello"+id, Toast.LENGTH_LONG).show();
        ListView lv=findViewById(R.id.lv_schedule);
        ThingsToDoDatabaseAccess db1 = ThingsToDoDatabaseAccess.getInstance(getApplicationContext());

        Cursor c=db1.getPlanSchedule(id);
        int total=c.getCount();
        String[] p_sche=new String[total];
        String temp;
        c.moveToFirst();
        for (int i = 0; i < total; i++) {
            temp = c.getString(c.getColumnIndex("schedule_date"))+"   ";
            temp += c.getString(c.getColumnIndex("schedule_time"))+"   ";
            temp+=c.getString(c.getColumnIndex("destination"));
            p_sche[i]=temp;
            c.moveToNext();
        }
        db1.close();
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,p_sche);
        lv.setAdapter(adp);
    }
}

