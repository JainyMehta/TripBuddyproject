package com.example.androidproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private ArrayList<String> mDate;
    private ArrayList<String> mSchedule;
    //yt-4
    private OnItemClickListner mListner;

    //yt-4
    public interface OnItemClickListner {
        void onItemClick(int position);
        void onAddClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListner listner)
    {
        mListner=listner;
    }

    public static class PlanViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tv, tv_schedule;
        public ImageButton ib_add;
        public PlanViewHolder(@NonNull View itemView, final OnItemClickListner listner) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv_date);
            ib_add=itemView.findViewById(R.id.ib_add);
            tv_schedule=itemView.findViewById(R.id.tv_schedule);
            //tv_schedule.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner!=null)
                    {
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listner.onItemClick(position);
                        }
                    }
                }
            });
            ib_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listner!=null)
                    {
                        int position = getAdapterPosition();
                        //tv_schedule.setVisibility(View.VISIBLE);
                        if(position!= RecyclerView.NO_POSITION)
                        {
                            listner.onAddClick(position);

                        }
                    }
                }
            });
        }
    }
    public PlanAdapter(ArrayList<String> date, ArrayList<String> schedule)
    {
        mDate=date;
        mSchedule=schedule;
    }
    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plan_day_card_layout,viewGroup,false);
        PlanViewHolder pvh=new PlanViewHolder(v,mListner);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder planViewHolder, int i) {
        planViewHolder.tv.setText(mDate.get(i));
        planViewHolder.tv_schedule.setText(mSchedule.get(i));
    }

    @Override
    public int getItemCount() {
        return mDate.size();
    }

}
