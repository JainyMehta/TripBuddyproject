package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThingsToDoDatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static ThingsToDoDatabaseAccess instance;
    Cursor c = null;

    private ThingsToDoDatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }



    public static ThingsToDoDatabaseAccess getInstance(Context context){
        if(instance==null){
            instance= new ThingsToDoDatabaseAccess(context);
        }
        return instance;

    }

    public void open(){
        this.db=openHelper.getWritableDatabase();

    }


    public void close(){
        if(db!=null){
            this.db.close();
        }

    }

    public Cursor getData(int c_id)
    {
        //int c_id=1;
        open();
        String c_id1= Integer.toString(c_id);
        //Cursor c=db.rawQuery("Select things_to_do.place_name, things_to_do.rating from things_to_do, things_to_do_category_place where things_to_do_category_place.place_id=things_to_do.place_id and things_to_do_category_place.category_id=?",new String[] {c_id1});
//        Cursor c=db.rawQuery("Select things_to_do.place_name, things_to_do.image, things_to_do.rating from things_to_do, things_to_do_category_place where things_to_do_category_place.place_id=things_to_do.place_id and things_to_do_category_place.category_id=?",new String[] {c_id1});
        Cursor c=db.rawQuery("Select things_to_do.place_name, things_to_do.image, things_to_do.rating from things_to_do, things_to_do_category_place where things_to_do_category_place.place_id=things_to_do.place_id and things_to_do_category_place.category_id=1",null);
        return c;
    }
    public Cursor getPlace(String c_id)
    {
        //int c_id=1;
        open();
        //String c_id1= Integer.toString(c_id);
        Cursor c=db.rawQuery("Select * from things_to_do where things_to_do.place_name=?",new String[]{c_id});
        return c;
    }
    public boolean insertPlan(int u_id,String plan_name,String start, String end)
    {
        open();
        ContentValues cv= new ContentValues();
        cv.put("user_id",u_id);
        cv.put("plan_name",plan_name);
        cv.put("start_day",start);
        cv.put("end_day",end);
        db.insert("user_plan",null,cv);
        db.close();
        return true;
    }
    public Integer getPlanID()
    {
        //int c_id=1;
        open();
        //String u_id1= Integer.toString(u_id);
        Cursor c=db.rawQuery("Select * from user_plan",null);
        c.moveToFirst();
        int id;
        do{
            id=c.getInt(c.getColumnIndex("plan_id"));
        }while(c.moveToNext());
        close();
        return id;
    }
    public Cursor getPlanList()
    {
        //int c_id=1;
        open();
        //String u_id1= Integer.toString(u_id);
        Cursor c=db.rawQuery("Select * from user_plan",null);

        return c;
    }
    public Cursor getPlanSchedule(int plan_id)
    {
        //int c_id=1;
        open();
        String p_id1= Integer.toString(plan_id);
        Cursor c=db.rawQuery("Select * from plan_schedule where plan_schedule.plan_id=?",new String[]{p_id1});

        return c;
    }
    public boolean insertSchedule(int p_id,String p_date,String p_time, String p_place)
    {
        open();
        ContentValues cv= new ContentValues();
        cv.put("plan_id",p_id);
        cv.put("schedule_date",p_date);
        cv.put("schedule_time",p_time);
        cv.put("destination",p_place);
        db.insert("plan_schedule",null,cv);
        close();
        return true;
    }
}
