package com.example.androidproject;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapterThingsToDo extends RecyclerView.Adapter<CustomAdapterThingsToDo.MyViewHolder> {
    Context context;
    String[] names;
    Bitmap[] img;
    double rate[];
    public CustomAdapterThingsToDo(Context context, String[] names,Bitmap[] img, double[] rate){
        //public CustomAdapterThingsToDo(Context context, String[] names, double[] rate){
        this.context=context;
        this.names=names;
        this.img=img;
        this.rate=rate;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.place_layout, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_name.setText(names[position]);
        holder.img.setImageBitmap(img[position]);
        holder.rb.setRating((float) rate[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent=new Intent(context,PlaceActivity.class);
                intent.putExtra("c_id",names[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (names.length);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_name;
        ImageView img;
        RatingBar rb;
        public MyViewHolder(View itemView){
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            rb=itemView.findViewById(R.id.rb_rate);
            img=itemView.findViewById(R.id.iv_image);
        }
    }

}

