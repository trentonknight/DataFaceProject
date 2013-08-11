package com.beta.dataface;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class ContentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);
        // Show the Up button in the action bar.
        setupActionBar();

        DatabaseTwo db = new DatabaseTwo(this);






/*
        TextView tv = (TextView)findViewById(R.id.textView);
        TextView tv_two = (TextView) findViewById(R.id.textView2);
        tv.setText(content_now);
        tv_two.setText(OB_name); */   }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //need to create a bundle to return to selected object
                Intent intent = new Intent(getApplicationContext(), ListViewLoader.class);
                startActivity(intent);
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
