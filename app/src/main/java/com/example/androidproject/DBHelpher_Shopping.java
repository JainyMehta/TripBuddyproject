package com.example.androidproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelpher_Shopping {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DBHelpher_Shopping instance;
    Cursor c = null;

    private DBHelpher_Shopping(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }



    public static DBHelpher_Shopping getInstance(Context context){
        if(instance==null){
            instance= new DBHelpher_Shopping(context);
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

    public Cursor getData1(int c_id, int cat_id) {
        //int c_id=1;
        open();
        String c_id1 = Integer.toString(c_id);
        String cat_id1 = Integer.toString(cat_id);
        Cursor c = db.rawQuery("select shop_name.shop_id,shop_name.shop_image, shop_name.shop_placename, shop_name.rating_bar,shop_name.shop_area from shop_name , shop_category where shop_name.shop_id=shop_category.shop_id and shop_category.category_id_shopping=? and shop_name.city_id=?;", new String[]{c_id1, cat_id1});
        return c;
    }

    public Cursor getData2(int c_id, int cat_id) {
        c_id = 2;
        open();
        String c_id2 = Integer.toString(c_id);
        String cat_id2 = Integer.toString(cat_id);
        Cursor c = db.rawQuery("select shop_name.shop_id,shop_name.shop_image, shop_name.shop_placename, shop_name.rating_bar,shop_name.shop_area from shop_name , shop_category where shop_name.shop_id=shop_category.shop_id and shop_category.category_id_shopping=? and shop_name.city_id=?;", new String[]{c_id2, cat_id2});
        return c;

    }

    public Cursor getData3(int c_id, int cat_id) {
        c_id = 3;
        open();
        String c_id3 = Integer.toString(c_id);
        String cat_id3 = Integer.toString(cat_id);
        Cursor c = db.rawQuery("select shop_name.shop_id,shop_name.shop_image, shop_name.shop_placename, shop_name.rating_bar,shop_name.shop_area from shop_name , shop_category where shop_name.shop_id=shop_category.shop_id and shop_category.category_id_shopping=? and shop_name.city_id=?;", new String[]{c_id3, cat_id3});
        return c;

    }

    public Cursor getData4(int c_id, int cat_id) {
        c_id = 4;
        open();
        String c_id4 = Integer.toString(c_id);
        String cat_id4 = Integer.toString(cat_id);
        Cursor c = db.rawQuery("select shop_name.shop_id,shop_name.shop_image, shop_name.shop_placename, shop_name.rating_bar,shop_name.shop_area from shop_name , shop_category where shop_name.shop_id=shop_category.shop_id and shop_category.category_id_shopping=? and shop_name.city_id=?;", new String[]{c_id4, cat_id4});
        return c;

    }
    public Cursor getData5(String shop_id)
    {
        //int c_id=1;
        open();
        //String r_id1= Integer.toString(rest_id);

        Cursor c=db.rawQuery("select * from shop_name where shop_id=?",new String[] {shop_id});
        //Cursor c=db.rawQuery("select top_sports.restaurant_id,top_sports.restaurant_name,top_sports.restaurant_contact,top_sports.restaurant_area,top_sports.restaurant_rating from top_sports,restaurant_category where top_sports.restaurant_id=restaurant_category.restaurant_id and restaurant_category.category_id=? and top_sports.city_id=?",new String[] {cat_id1,c_id1});
        return c;
    }

}
