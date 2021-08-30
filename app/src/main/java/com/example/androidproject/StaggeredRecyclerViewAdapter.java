package com.example.androidproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mNames;
    private ArrayList<String> mImageUrls;
    private Context mContext;
    String name;


    public StaggeredRecyclerViewAdapter(ArrayList<String> mNames, ArrayList<String> mImageUrls, Context mContext,String name) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mContext = mContext;
        this.name=name;
    }


    @NonNull
    @Override
    public StaggeredRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredRecyclerViewAdapter.ViewHolder viewHolder, final int i) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(mImageUrls.get(i))
                .apply(requestOptions)
                .into(viewHolder.image);

        viewHolder.name.setText(mNames.get(i));

        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (i){
                    case 0:
                        Toast.makeText(mContext, mNames.get(i), Toast.LENGTH_SHORT).show();
                        Intent c = new Intent(mContext,ThingsToDoActivity.class);
                        mContext.startActivity(c);
                        break;

                    case 1:
                        Intent b = new Intent(mContext,ShoppingActivity.class);
                        mContext.startActivity(b);
                        break;

                    case 2:
                        Intent d =new Intent(mContext,Plan_List_Activity.class);
                        mContext.startActivity(d);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image_widget);
            this.name = itemView.findViewById(R.id.name_widget);
        }
    }
}
