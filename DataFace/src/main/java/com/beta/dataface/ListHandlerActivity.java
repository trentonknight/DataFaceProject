package com.beta.dataface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by trentonknight on 7/27/13.
 */
public class ListHandlerActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.list_activity_main);

        DatabaseH db = new DatabaseH(this);


        final ListView listview = (ListView) findViewById(R.id.listview);
        final GetArrayAdapter adapter = new GetArrayAdapter(this,
        android.R.layout.simple_list_item_1, db.DisplayObjectName(db.getAllColumns()));
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                final String item = (String) adapterView.getItemAtPosition(i);
                        passDataToTheChild(i + 1);
            }
        });
    }
    public void passDataToTheChild(int position){
        DatabaseH db = new DatabaseH(this);
        LittleConstructor singleObject = db.getSingleObject(position);
        String content = singleObject.getContent().toString();
        //String content = "Test Test";
        Intent intent = new Intent(this, ListChildActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("content",content);
        intent.putExtras(bundle);
        startActivity(intent);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStop(){
        super.onStop();
    }

}
