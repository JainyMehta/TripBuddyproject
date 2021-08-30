package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainOptions2 extends AppCompatActivity {

    private static final int NUM_COLUMNS = 1;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options2);

        initImageBitmaps();
    }

    private void initImageBitmaps(){
        mImageUrls.add("https://i.pinimg.com/564x/39/0e/1a/390e1ac4b646b99116f69cd33470869a.jpg");
        mNames.add("Things to do");

        mImageUrls.add("https://i.pinimg.com/564x/ac/4f/ee/ac4fee2d5e981c873cdd5d99f1537c66.jpg");
        mNames.add("Shopping");

        mImageUrls.add("https://i.pinimg.com/564x/54/47/f9/5447f9aff75981b28cda0efb368b1f5b.jpg");
        mNames.add("Plan a Day");


        initRecyclerView();
    }

    private void initRecyclerView(){
        Intent intent = getIntent();
        String str = intent.getStringExtra("city_name");
        Toast.makeText(getApplicationContext(), "CITY NAME IS"+str, Toast.LENGTH_SHORT).show();
        RecyclerView recyclerView =findViewById(R.id.recyclerView2);
        StaggeredRecyclerViewAdapter staggeredRecyclerViewAdapter = new StaggeredRecyclerViewAdapter(mNames,mImageUrls,this,str);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(staggeredRecyclerViewAdapter);
    }

}