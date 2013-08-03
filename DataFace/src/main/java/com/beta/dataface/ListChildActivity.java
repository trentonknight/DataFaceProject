package com.beta.dataface;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.ListView;
import android.widget.TextView;

public class ListChildActivity extends Activity {


private static int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.child_activity);
        // Show the Up button in the action bar.
        setupActionBar();

        DatabaseH db = new DatabaseH(this);

        Bundle bundle = getIntent().getExtras();
        int newContent = bundle.getInt("position");
        newContent++;
        position = newContent;///pass to position for delObjectColumn

        LittleConstructor singleObject = db.getSingleObject(newContent);

        //int id_number = singleObject.getID(); save for rainy day
        String OB_name = singleObject.getOB();
        String content_now = singleObject.getContent();

        TextView tv = (TextView)findViewById(R.id.textView);
        TextView tv_two = (TextView) findViewById(R.id.textView2);
        tv.setText(content_now);
        tv_two.setText(OB_name);


    }
    public void delObjectColumn(){
        DatabaseH db = new DatabaseH(this);
         try{
       final ListView listview = (ListView) findViewById(R.id.list);
       db.deleteTable(position);
      // int current = listview.getSelectedItemPosition();
       //listview.removeViewAt(current);
       listview.removeAllViews();
       ////Intent intent = new Intent(getApplicationContext(),ListHandlerActivity.class);
       //Bundle bundle = new Bundle();

       } catch (IndexOutOfBoundsException e){
          Log.d("Index out of bound","IndexOutOfBoundsException");
        }
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
         getMenuInflater().inflate(R.menu.list_child_menu, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.content_discard:
                 delObjectColumn();
                return true;
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
