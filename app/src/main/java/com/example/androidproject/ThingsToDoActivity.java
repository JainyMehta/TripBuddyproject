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

public class ThingsToDoActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sl;
    HashMap<String, Integer> imageslide ;
    ImageButton topspots,local_favourite,museum,temple,adventure,garden,kids,all;
    int city_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_things_to_do);

        city_id=1;
        sl=findViewById(R.id.slider);
        topspots=findViewById(R.id.ib_topspots);
        local_favourite=findViewById(R.id.ib_localfavourites);
        museum=findViewById(R.id.ib_monument);
        temple=findViewById(R.id.ib_temple);
        adventure=findViewById(R.id.ib_adventuresports);
        garden=findViewById(R.id.ib_garden);
        kids=findViewById(R.id.ib_kids);
        all=findViewById(R.id.ib_all);

        AddImageUrlFormLocalRes();
        for(String name : imageslide.keySet()) {
            TextSliderView tsv=new TextSliderView(ThingsToDoActivity.this);
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

        sl.addOnPageChangeListener(ThingsToDoActivity.this);
        topspots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",1);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        local_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",2);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",3);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        temple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",4);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",5);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        garden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",6);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        kids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",7);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThingsToDoActivity.this,Place_list_Activity.class);
                intent.putExtra("cat_id",8);
                intent.putExtra("city_id",city_id);
                startActivity(intent);
            }
        });

    }
    public void AddImageUrlFormLocalRes(){

        imageslide = new HashMap<String, Integer>();

        imageslide.put("First", R.drawable.adventuresports);
        imageslide.put("Second", R.drawable.paragliding);
        imageslide.put("Third", R.drawable.monument);

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
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}