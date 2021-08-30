package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;


public class ShoppingActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    SliderLayout sl;
    HashMap<String, Integer> imageslide ;
    ImageButton handicraft_image_button,img_shopping,img_specialities,img_streetareas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sl=findViewById(R.id.slider);
        handicraft_image_button= findViewById(R.id.ib_handicraft);
        img_shopping= findViewById(R.id.ib_shoppingmalls);
        img_specialities=findViewById(R.id.ib_specialities);
        img_streetareas =findViewById(R.id.ib_streetareas);
        AddImageUrlFormLocalRes();
        for(String name : imageslide.keySet()) {
            TextSliderView tsv=new TextSliderView(ShoppingActivity.this);
            tsv.description(name);
            tsv.image(imageslide.get(name));
            tsv.setScaleType(BaseSliderView.ScaleType.Fit);
            tsv.setOnSliderClickListener(this);
            tsv.bundle(new Bundle());
            tsv.getBundle().putString("extra",name);
            sl.addSlider(tsv);
        }
        sl.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sl.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sl.setCustomAnimation(new DescriptionAnimation());

        sl.setDuration(3000);

        sl.addOnPageChangeListener(ShoppingActivity.this);
        handicraft_image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ShoppingActivity.this,Handicrafts.class);
                startActivity(intent);
            }
        });
        img_shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ShoppingActivity.this,shoppingMall.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        img_streetareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShoppingActivity.this,StreetAreas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        img_specialities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShoppingActivity.this,Specialities.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

    }
    public void AddImageUrlFormLocalRes(){

        imageslide = new HashMap<String, Integer>();

        imageslide.put("First",R.drawable.rotate1);
        imageslide.put("Second",R.drawable.rotate2);
        imageslide.put("Third",R.drawable.rotate3);
        imageslide.put("Fourth",R.drawable.rotate4);

    }
    @Override
    protected void onStop() {
        sl.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position)

    {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}