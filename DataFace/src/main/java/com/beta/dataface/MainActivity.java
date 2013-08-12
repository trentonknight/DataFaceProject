package com.beta.dataface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);

    }
    public void newObject(){
        Intent intent = new Intent(this, GetUserTextInputOne.class);
        startActivity(intent);
        finish();
    }

    public void activateList(){
        Intent intent = new Intent(this, ListViewLoaderOne.class);
        startActivity(intent);
        finish();
    }
    public void destroyDb(){
        Intent intent = new Intent(getApplicationContext(), DatabaseOne.class);
        DatabaseOne db = new DatabaseOne(this);
        db.destroyTable();
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.content_discard:
                destroyDb();
                return true;
            case R.id.content_add:
                newObject();
                return true;
            case R.id.view_as_list:
                activateList();
                return true;
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(),ListViewLoaderOne.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
