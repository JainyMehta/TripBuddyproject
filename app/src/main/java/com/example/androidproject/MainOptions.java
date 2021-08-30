package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class MainOptions extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    SliderLayout sl;
    HashMap<String, Integer> imageslide ;
    AutoCompleteTextView auto;
    String destination[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);

        sl = findViewById(R.id.slider);
        auto = findViewById(R.id.autoCompleteTextView);
        destination = getResources().getStringArray(R.array.Destination);
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,destination);
        auto.setThreshold(1);
        auto.setAdapter(adapter);


        AddImageUrlFormLocalRes();
        for(String name : imageslide.keySet()) {
            TextSliderView tsv=new TextSliderView(MainOptions.this);
            tsv.description(name);
            tsv.image(imageslide.get(name));
            tsv.setScaleType(BaseSliderView.ScaleType.Fit);
            tsv.setOnSliderClickListener(this);
            tsv.bundle(new Bundle());
            tsv.getBundle().putString("extra",name);
//            Intent i=new Intent(MainOptions.this,food_and_drinks_activity.class);
//            i.putExtra("city_name","ahmedabad");
//            Bundle bundle = new Bundle();
//            bundle.putString("city_value",name);
//            //display_restaurant_act fragInfo = new display_restaurant_act();
//            TopSpotsFragment fragInfo=new TopSpotsFragment();
//            fragInfo.setArguments(bundle);



            sl.addSlider(tsv);
        }
        sl.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sl.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        sl.setCustomAnimation(new DescriptionAnimation());

        sl.setDuration(3000);
        sl.addOnPageChangeListener(MainOptions.this);
    }

    public void AddImageUrlFormLocalRes(){

        imageslide = new HashMap<String, Integer>();

        imageslide.put("Kolkata",R.drawable.kolkata);
        imageslide.put("Ahmedabad",R.drawable.ahmedabad);
        imageslide.put("Delhi",R.drawable.delhi);
        imageslide.put("Jaipur",R.drawable.jaipur);
        imageslide.put("Bangalore",R.drawable.bangalore);

    }

    @Override
    protected void onStop() {
        sl.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(),MainOptions2.class);
        String value= (String) slider.getBundle().get("extra");
        i.putExtra("city_name",value);
        startActivity(i);
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