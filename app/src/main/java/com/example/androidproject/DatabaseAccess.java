package com.example.androidproject;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseAccess{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //TABLE NAME
    public static final String TABLE_USERS = "user_details";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "user_id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }



    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance= new DatabaseAccess(context);
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

    public User Authenticate(User user) {
        open();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_USER_NAME + "=?",
                new String[]{user.userName},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                close();
                return user1;
            }
        }
        close();
        //if user password does not matches or there is no record with that email then return @false
        return null;
    }


    public void addUser(User user) {
        open();
        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
        close();
    }

    public boolean isEmailExists(String email) {
        open();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            close();
            return true;
        }

        close();
        //if email does not exist return false
        return false;
    }
}