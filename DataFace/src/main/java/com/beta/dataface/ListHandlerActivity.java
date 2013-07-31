package com.beta.dataface;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by trentonknight on 7/27/13.
 */
public class ListHandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);

        DatabaseH db = new DatabaseH(this);
        final ListView listview = (ListView) findViewById(R.id.listview);
        final GetArrayAdapter adapter = new GetArrayAdapter(this,
        android.R.layout.simple_list_item_1, db.DisplayObject(db.getAllColumns()));
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final String item = (String) adapterView.getItemAtPosition(i);
                view.animate().setDuration(2000).alpha(0).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        //list.remove(item);
                        //db.DisplayObject(db.getAllColumns());
                        adapter.notifyDataSetChanged();
                        view.setAlpha(1);
                    }
                });
            }
        });
    }

    private class GetArrayAdapter extends ArrayAdapter<String>{
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        public GetArrayAdapter(Context context, int textViewID, List<String> objects){
            super(context, textViewID, objects);
            for(int i = 0; i < objects.size(); ++i){
                map.put(objects.get(i), i);
            }
        }

    }

    protected void onStop(){
        super.onStop();
    }
}
