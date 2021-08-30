package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class Plan_List_Activity extends AppCompatActivity {

    String[] p_name;
    Integer[] p_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan__list_);

        ListView lv=findViewById(R.id.lv_plan);
        Button btn_new=findViewById(R.id.btn_new);
        ThingsToDoDatabaseAccess db1 = ThingsToDoDatabaseAccess.getInstance(getApplicationContext());

        Cursor c=db1.getPlanList();
        int total=c.getCount();
        p_id=new Integer[total];
        p_name=new String[total];
        c.moveToFirst();
        for (int i = 0; i < total; i++) {
            p_id[i]=c.getInt(c.getColumnIndex("plan_id"));
            p_name[i] = c.getString(c.getColumnIndex("plan_name"));
            c.moveToNext();
        }
        db1.close();
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,p_name);
        lv.setAdapter(adp);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(Plan_List_Activity.this,Planned_Schedule_List_Activity.class);
                intent.putExtra("plan_id",p_id[i]);
                startActivity(intent);
            }
        });
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Plan_List_Activity.this,Plan_a_DayActivity.class);
                startActivity(intent);
            }
        });
    }
}
