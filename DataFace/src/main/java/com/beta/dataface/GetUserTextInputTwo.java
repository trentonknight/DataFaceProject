package com.beta.dataface;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class GetUserTextInputTwo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_uti_two);
        // Show the Up button in the action bar.
        setupActionBar();

 }
    public void newContentData(View view)
    {
         DatabaseTwo db = new DatabaseTwo(this);
         Bundle bundle = getIntent().getExtras();
         String OB_name = bundle.getString("OB_name");

         EditText ed1 = (EditText) findViewById(R.id.editText);
         EditText ed2 = (EditText) findViewById(R.id.editText2);

         String str1 = ed1.getText().toString();
         String str2 = ed2.getText().toString();

         db.addNewContent(OB_name,str1,str2);

         Intent intent = new Intent(getApplicationContext(), ListViewLoaderOne.class);
         startActivity(intent);
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    private void setupActionBar() {
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.get_uti_two_menu, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //need to create a bundle to return to selected object
                Intent intent = new Intent(getApplicationContext(), ListViewLoaderOne.class);
                startActivity(intent);
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
