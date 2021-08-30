package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;

public class Place_list_Activity extends AppCompatActivity {

    RecyclerView rc;
    int city_id, cat_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list_);

        Intent intent=getIntent();
        //city_id= intent.getExtras().getInt("city_id");
        //cat_id=intent.getExtras().getInt("cat_id");
        ThingsToDoDatabaseAccess db1 =ThingsToDoDatabaseAccess.getInstance(getApplicationContext());

        ArrayList<String> n=new ArrayList<>();
        Cursor c = db1.getData(1);


        int total=c.getCount();
        double rate1[]=new double[total];
        Bitmap b[]=new Bitmap[total];
//        for (int i = 0; i < total; i++) {
//            if(i==0)
//            {
        c.moveToFirst();
//            }
        n.add( c.getString(c.getColumnIndex("place_name")));
        rate1[0]=c.getInt(c.getColumnIndex("rating"));
        byte img2[]=c.getBlob(c.getColumnIndex("image"));
        b[0]= BitmapFactory.decodeByteArray(img2,0,img2.length);
        for (int i = 1; i < 3; i++) {
            c.moveToNext();
            n.add( c.getString(c.getColumnIndex("place_name")));
            rate1[i]=c.getInt(c.getColumnIndex("rating"));
            byte img1[]=c.getBlob(c.getColumnIndex("image"));
            b[i]= BitmapFactory.decodeByteArray(img1,0,img1.length);
        }
        db1.close();

//        do{
//            n.add( c.getString(c.getColumnIndex("place_name")));
//            rate1[i]=c.getInt(c.getColumnIndex("rating"));
////            byte img2[]=c.getBlob(c.getColumnIndex("shop_image"));
////            b[i]= BitmapFactory.decodeByteArray(img2,0,img2.length);
//            i++;
//        }while(c.moveToNext());
        String[] names = new String[n.size()];
        names = n.toArray(names);
        rc=findViewById(R.id.rc_place);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rc.setLayoutManager(linearLayoutManager);
        rc.setHasFixedSize(true);
        CustomAdapterThingsToDo customAdapter=new CustomAdapterThingsToDo(this,names,b,rate1);
        //CustomAdapterThingsToDo customAdapter=new CustomAdapterThingsToDo(this,names,rate1);
        rc.setAdapter(customAdapter);
    }

}