package com.beta.dataface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trentonknight on 7/24/13.
 */
public class DatabaseH extends SQLiteOpenHelper{

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "learning";
    private static final String TABLE_NAME = "objects";
    //table columns
    private static final String KEY_ID = "id";
    private static final String KEY_OBNAME = "objectName";
    private static final String KEY_CONTENT = "content";

    public DatabaseH(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_OBJECT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_OBNAME + " TEXT,"
                + KEY_CONTENT + " TEXT" + ")";
        db.execSQL(CREATE_OBJECT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
    //add new contact
    public void addObject(LittleConstructor constructObject){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_OBNAME, constructObject.getOB());//object name
        values.put(KEY_CONTENT, constructObject.getContent());//inside content for object
        //insert row
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    LittleConstructor getSingleObject(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID, KEY_OBNAME,KEY_CONTENT}, KEY_ID + "=?",
                new String[] { String.valueOf(id)}, null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
            LittleConstructor con = new LittleConstructor(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1), cursor.getString(2));

        return con;
    }

    public Cursor getAllColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[] {KEY_ID, KEY_OBNAME, KEY_CONTENT},null,null,null,null,null);
    }

    public List<String> DisplayObject(Cursor c){
    List<String> array = new ArrayList<String>();
        while(c.moveToNext()){
            String uname = c.getString(c.getColumnIndex(KEY_OBNAME));
            array.add(uname);
        }
        return array;
    }

}
