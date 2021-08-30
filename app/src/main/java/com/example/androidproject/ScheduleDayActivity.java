package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class ScheduleDayActivity extends AppCompatActivity implements ScheduleDialog.ScheduleDialogListner{

    String start_date, end_date;
    Date st_date,ed_date;
    String new_time, new_place,temp;
    int flag,id;
    ArrayList<String> date;
    ArrayList<String> schedule;
    private RecyclerView mRecyclerView;
    private PlanAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_day);

        Intent intent=getIntent();
        id=intent.getIntExtra("plan_id",1);
        start_date=intent.getStringExtra("start_date");
        end_date=intent.getStringExtra("end_date");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            st_date = simpleDateFormat.parse(start_date);
            ed_date = simpleDateFormat.parse(end_date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //String dateStr = simpleDateFormat.format( dateObj );        //Date to String
        date=new ArrayList<>();
        schedule=new ArrayList<>();
        Date begin = new Date(st_date.getTime());
        LinkedList list = new LinkedList();
        list.add(new Date(begin.getTime()));

        while(begin.compareTo(ed_date)<0)
        {
            begin = new Date(begin.getTime() + 86400000);
            list.add(new Date(begin.getTime()));
        }

        for(int i=0; i<list.size(); i++) {
            date.add(new SimpleDateFormat("dd/MM/yyyy - E").format(((Date) list.get(i))));
            schedule.add("");
        }

        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new PlanAdapter(date,schedule);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListner(new PlanAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position) {
                date.get(position);
            }

            @Override
            public void onAddClick(int position) {
                openDialog();

                flag=position;


            }
        });
    }
    public void openDialog()
    {
        ScheduleDialog sd=new ScheduleDialog();
        sd.show(getSupportFragmentManager(), "schedule dialog");
    }
    public void changeschedule()
    {
        temp=schedule.get(flag)+new_time + " - " +new_place+"\n";
        ThingsToDoDatabaseAccess db1 = ThingsToDoDatabaseAccess.getInstance(getApplicationContext());

        Boolean result=db1.insertSchedule(id,date.get(flag),new_time,new_place);
        if (result == true) {

            schedule.set(flag,temp);
            mAdapter.notifyItemChanged(flag);
            Toast.makeText(ScheduleDayActivity.this,schedule.get(flag)+id,Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(ScheduleDayActivity.this, "Insert UnSuccessfull!!", Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void applyTexts(String time, String place) {
        new_time=time;
        new_place=place;
        changeschedule();
    }
}
