package com.beta.dataface;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trentonknight on 8/6/13.
 */
public class DatabaseTwo extends SQLiteOpenHelper
{
    private SQLiteDatabase sqLiteDatabase;

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "objectcontent";
    //table columns

    public static final class CUBES implements BaseColumns {
        private CUBES() {}
        public static final String TABLE_NAME = "content";
        public static final String KEY_ID = "_id";
        public static final String KEY_PARENT = "parent";
        public static final String KEY_OBNAME = "cubename";
        public static final String KEY_CONTENT = "cube";
    }
    public DatabaseTwo(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }
    //Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + CUBES.TABLE_NAME);
        String CREATE_OBJECT_TABLE = "CREATE TABLE " + CUBES.TABLE_NAME + "("
                + CUBES.KEY_ID + " INTEGER PRIMARY KEY," + CUBES.KEY_PARENT + " TEXT," +
                CUBES.KEY_OBNAME + " TEXT," + CUBES.KEY_CONTENT+ " TEXT" + ")";
        db.execSQL(CREATE_OBJECT_TABLE);
    }
    public void updateCubeColumns(int set, int where){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(CUBES.KEY_ID,set);
        db.update(CUBES.TABLE_NAME, args, CUBES.KEY_ID + "=" + where, null);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + CUBES.TABLE_NAME);
        onCreate(db);
    }

    public void addNewContent(String parent, String obname, String content)
    {
        SQLiteDatabase dbTwo = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CUBES.KEY_PARENT, parent);
        values.put(CUBES.KEY_OBNAME, obname);
        values.put(CUBES.KEY_CONTENT, content);
        dbTwo.insert(CUBES.TABLE_NAME,null,values);
        dbTwo.close();

    }

    public Cursor getAllColumns(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(CUBES.TABLE_NAME, new String[] {CUBES.KEY_ID, CUBES.KEY_PARENT, CUBES.KEY_OBNAME, CUBES.KEY_CONTENT},null,null,null,null,null);


    }



    public List<String> DisplayObjectName(Cursor c){
        List<String> array = new ArrayList<String>();
        while(c.moveToNext()){
            String uname = c.getString(c.getColumnIndex(CUBES.KEY_OBNAME));
            array.add(uname);
        }
        return array;
    }

    public void deleteTable(int delID){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CUBES.TABLE_NAME, "_id =" + delID, new String[] {});
        Log.d("Deleted Database", "Database removed");
        sqLiteDatabase.close();
    }

    public void destroyTable(){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(CUBES.TABLE_NAME, "1", new String[] {});
        Log.d("Deleted Database", "Database removed");
        sqLiteDatabase.close();
    }




}
