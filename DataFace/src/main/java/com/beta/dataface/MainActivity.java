package com.beta.dataface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseH db = new DatabaseH(this);
        Log.d("Insert: ", "Inserting ..");
        db.addObject(new LittleConstructor("Animals","snake"));
        db.addObject(new LittleConstructor("Humans","Brian"));
        db.addObject(new LittleConstructor("Vehicles", "Jeep"));

    }

    public void activateList(View view){
        Intent intent = new Intent(view.getContext(), ListHandlerActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
