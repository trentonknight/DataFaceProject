package com.beta.dataface;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

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
                        passDataToTheChild(i);
                        adapter.notifyDataSetInvalidated();

            }
        });
    }
    public void passDataToTheChild(int position){

        Intent intent = new Intent(this, ListChildActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putString("content",content);
        bundle.putInt("position", position);
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
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStop(){
        super.onStop();
    }

}
