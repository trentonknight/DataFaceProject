package com.beta.dataface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_main);

    }
    public void newObject(View view){
        Intent intent = new Intent(view.getContext(), AddNewObject.class);
        startActivity(intent);
        finish();
    }

    public void activateList(View view){
        Intent intent = new Intent(view.getContext(), ListViewLoader.class);
        startActivity(intent);
        finish();
    }
    public void destroyDb(View view){
        Intent intent = new Intent(getApplicationContext(), DatabaseH.class);
        DatabaseH db = new DatabaseH(this);
        db.destroyTable();
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
