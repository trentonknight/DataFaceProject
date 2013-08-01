package com.beta.dataface;

import android.*;
import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trentonknight on 7/24/13.
 */
public class DatabaseH extends SQLiteOpenHelper{


    private static final int DATABASE_VER = 2;
    private static final String DATABASE_NAME = "learning";
    //table columns

 public static final class KEYS implements BaseColumns{
    private KEYS() {}
    private static final String TABLE_NAME = "objects";
    public static final String KEY_ID = "id";
    public static final String KEY_OBNAME = "objectName";
    public static final String KEY_CONTENT = "content";
 }
    public DatabaseH(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + KEYS.TABLE_NAME);
        String CREATE_OBJECT_TABLE = "CREATE TABLE " + KEYS.TABLE_NAME + "("
                + KEYS.KEY_ID + " INTEGER PRIMARY KEY," + KEYS.KEY_OBNAME + " TEXT,"
                + KEYS.KEY_CONTENT+ " TEXT" + ")";
        db.execSQL(CREATE_OBJECT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + KEYS.TABLE_NAME);
        onCreate(db);
    }

    //add new contact
    public void addObject(LittleConstructor constructObject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEYS.KEY_OBNAME, constructObject.getOB());//object name
        values.put(KEYS.KEY_CONTENT, constructObject.getContent());//inside content for object
        //insert row
        db.insert(KEYS.TABLE_NAME, null, values);
        db.close();
    }
    LittleConstructor getSingleObject(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(KEYS.TABLE_NAME, new String[] {KEYS.KEY_ID, KEYS.KEY_OBNAME,KEYS.KEY_CONTENT}, KEYS.KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
            LittleConstructor con = new LittleConstructor(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1), cursor.getString(2));

        return con;
    }

    public Cursor getAllColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(KEYS.TABLE_NAME, new String[] {KEYS.KEY_ID, KEYS.KEY_OBNAME, KEYS.KEY_CONTENT},null,null,null,null,null);
    }

    public List<String> DisplayObjectID(Cursor c){
    List<String> array = new ArrayList<String>();
        while(c.moveToNext()){
            String uname = c.getString(c.getColumnIndex(KEYS.KEY_ID));
            array.add(uname);
        }
        return array;
    }
    public List<String> DisplayObjectName(Cursor c){
        List<String> array = new ArrayList<String>();
        while(c.moveToNext()){
            String uname = c.getString(c.getColumnIndex(KEYS.KEY_OBNAME));
            array.add(uname);
        }
        return array;
    }
    public List<String> DisplayObjectContent(Cursor c){
        List<String> array = new ArrayList<String>();
        while(c.moveToNext()){
            String uname = c.getString(c.getColumnIndex(KEYS.KEY_CONTENT));
            array.add(uname);
    }
       return array;
    }

}
