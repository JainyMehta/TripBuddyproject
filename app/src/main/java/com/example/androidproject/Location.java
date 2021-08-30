package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Location extends AppCompatActivity {
    TextView shop_name,rating_display,desc,address,area1,city1;
    ImageButton im,img2;
    ImageView imv1;
    RatingBar rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        shop_name = findViewById(R.id.textView_shop_name);
        rating_display= findViewById(R.id.textView_digt_rating);
        desc = findViewById(R.id.textView_short_description);
        address= findViewById(R.id.textView_locate);
        im= findViewById(R.id.imageButton_directionsLocation);
        img2= findViewById(R.id.imageButton_website);
        rb= findViewById(R.id.ratingBar_shop_display);
        imv1= findViewById(R.id.imageView_Display);
        area1= findViewById(R.id.textView_area123);
//        city1 = findViewById(R.id.textView_city1234);

        Intent intent = getIntent();
        int shop=intent.getExtras().getInt("shopid12");
        Toast.makeText(getApplicationContext(), "Shop_id is:"+shop, Toast.LENGTH_SHORT).show();
        String shop_id1= String.valueOf(shop);
        DBHelpher_Shopping db1 = DBHelpher_Shopping.getInstance(getApplicationContext());

        Cursor c= db1.getData5(shop_id1);
        c.moveToFirst();
        String shopname="",shopdescription="",shopaddress="",sdirection="",swebsite1="",city="",area="";
        double rate=0.0f;
        byte[] img1=c.getBlob(c.getColumnIndex("shop_image"));
        Bitmap b= BitmapFactory.decodeByteArray(img1, 0, img1.length);
        shopname=c.getString(c.getColumnIndex("shop_placename"));
        shopdescription=c.getString(c.getColumnIndex("shop_placedescription"));
        shopaddress=c.getString(c.getColumnIndex("shop_address"));
//        city=c.getString(c.getColumnIndex("shop_city"));
        area= c.getString(c.getColumnIndex("shop_area"));
        sdirection=c.getString(c.getColumnIndex("shop_direction"));
        swebsite1=c.getString(c.getColumnIndex("shop_website"));
        rate=c.getDouble(c.getColumnIndex("rating_bar"));
        db1.close();
        shop_name.setText(shopname);
        rating_display.setText(String.valueOf(rate));
        desc.setText(shopdescription);
        address.setText(shopaddress);
        imv1.setImageBitmap(b);
        area1.setText(area);
        rb.setRating((float) rate);
        final String shopfinalwebsite1=swebsite1;
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(shopfinalwebsite1));
                startActivity(i);
            }
        });
        final String shopfinaldirection1=sdirection;
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(shopfinaldirection1));
                startActivity(i);
            }
        });

    }
}
