package com.example.androidproject;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Specialities extends AppCompatActivity {
    RecyclerView rc;
    int[] img={android.R.drawable.btn_star,android.R.drawable.btn_star,android.R.drawable.btn_star,android.R.drawable.btn_star};
    String []phno={"9674426455","9825077998","9830181115","8980433160"};
    String []names={"handicrafts","ShoppingMalls","Specislists","Street Areas"};
    String []add={"Ashram Road","Navrangpura","Paldi","Prahladnagar"};
    // RatingBar rb;
    float []rb ={5.0f,4.0f,3.0f,2.0f};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialities);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        DBHelpher_Shopping db1 = DBHelpher_Shopping.getInstance(getApplicationContext());

        Cursor c = db1.getData4(1,1);
        c.moveToFirst();
        int count=c.getCount();
        int shop_id[]=new int[count];
        String phno1[]=new String[count];
        String add1[]=new String[count];
        double rate1[]=new double[count];
        String name1[] = new String[count];
        Bitmap b[]=new Bitmap[count];
        byte img1[]=c.getBlob(c.getColumnIndex("shop_image"));
        b[0]= BitmapFactory.decodeByteArray(img1,0,img1.length);
        c.moveToFirst();
        name1[0] = c.getString(c.getColumnIndex("shop_placename"));
        add1[0]=c.getString(c.getColumnIndex("shop_area"));
        rate1[0]=c.getInt(c.getColumnIndex("rating_bar"));
        shop_id[0]=c.getInt(c.getColumnIndex("shop_id"));
        for (int i = 1; i < count; i++) {
            c.moveToNext();
            name1[i] = c.getString(c.getColumnIndex("shop_placename"));
            add1[i]=c.getString(c.getColumnIndex("shop_area"));
            rate1[i]=c.getInt(c.getColumnIndex("rating_bar"));
            byte img2[]=c.getBlob(c.getColumnIndex("shop_image"));
            b[i]= BitmapFactory.decodeByteArray(img2,0,img2.length);
            shop_id[i]=c.getInt(c.getColumnIndex("shop_id"));
        }
        db1.close();
        rc=findViewById(R.id.my_recycler_view);
        //rb.findViewById(R.id.ratingBar_display);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rc.setLayoutManager(linearLayoutManager);
        rc.setHasFixedSize(true);
//        CustomAdapter_shopping customAdapterShopping =new CustomAdapter_shopping(this,names,phno,b,add,rb);
//        rc.setAdapter(customAdapterShopping);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CustomAdapter_shopping customAdapter=new CustomAdapter_shopping(getApplicationContext(),name1,b,add1,rate1,shop_id);
        rc.setAdapter(customAdapter);

//       // return v;



    }

}

