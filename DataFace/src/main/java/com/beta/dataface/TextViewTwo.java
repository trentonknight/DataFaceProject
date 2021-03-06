package com.beta.dataface;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TextViewTwo extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view_two);
        // Show the Up button in the action bar.
        setupActionBar();

        DatabaseTwo db = new DatabaseTwo(this);

        Bundle bundle = getIntent().getExtras();
        int newContent = bundle.getInt("position");


        ConstructorTwo singleObject = db.getSingleObject(newContent);

        String parent = singleObject.getParent();
        String cubename = singleObject.getCubeName();
        String cube = singleObject.getCube();
        db.close();


        TextView ptxt = (TextView)findViewById(R.id.parent_txt);
        TextView name_txt = (TextView) findViewById(R.id.cubename_txt);
        TextView cube_txt = (TextView) findViewById(R.id.cube_txt);
        ptxt.setText(parent);
        name_txt.setText(cubename);
        cube_txt.setText(cube);


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
        getMenuInflater().inflate(R.menu.text_view_two, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(getApplicationContext(),ListViewLoaderOne.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
