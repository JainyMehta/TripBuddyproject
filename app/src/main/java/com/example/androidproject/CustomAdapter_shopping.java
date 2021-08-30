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

public class CustomAdapter_shopping extends RecyclerView.Adapter<CustomAdapter_shopping.MyViewHolder> {
    Context context;
    String[] names;
    Bitmap[] img;
    String[] add;
    double rate[];
    int[] shop_id;
    public CustomAdapter_shopping(Context context, String[] names, Bitmap[] img, String[] add, double[] rate, int[] shop_id){
        this.context=context;
        this.names=names;
        this.img=img;
        this.add=add;
        this.rate=rate;
        this.shop_id=shop_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_layout, null);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.t1.setText(names[position]);
        holder.img.setImageBitmap(img[position]);
        holder.t3.setText(add[position]);
        holder.rb.setRating((float) rate[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent=new Intent(context,Location.class);
                intent.putExtra("shopid12",shop_id[position]);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        TextView t1,t2,t3;
        ImageView img;
        RatingBar rb;
        public MyViewHolder(View itemView){
            super(itemView);
            t1=itemView.findViewById(R.id.textView_123);
            t3=itemView.findViewById(R.id.tv_rest_add);
            rb=itemView.findViewById(R.id.ratingBar_display);
            img=itemView.findViewById(R.id.imageView);
        }
    }

}
