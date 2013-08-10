package com.beta.dataface;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListChildActivity extends FragmentActivity {


private static int position = 0;
SimpleCursorAdapter twoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_activity);
        // Show the Up button in the action bar.
        setupActionBar();

   /*     FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ObjectFragment obFrag = new ObjectFragment();
        String frag1 = "fragOne";
        fragmentTransaction.add(R.id.fragment_container,obFrag,frag1);
        fragmentTransaction.commit();*/

        DatabaseH db = new DatabaseH(this);

        Bundle bundle = getIntent().getExtras();
        int newContent = bundle.getInt("position");
        newContent++;
        position = newContent;///pass to position for delObjectColumn

        LittleConstructor singleObject = db.getSingleObject(newContent);

        //int id_number = singleObject.getID(); save for rainy day
        String OB_name = singleObject.getOB();
        String content_now = singleObject.getContent();
        db.close();

        TextView tv = (TextView)findViewById(R.id.textView);
        TextView tv_two = (TextView) findViewById(R.id.textView2);
        tv.setText(content_now);
        tv_two.setText(OB_name);

    }
    SimpleCursorAdapter gridCursor(){
        DatabaseTwo dbTwo = new DatabaseTwo(this);
        String[] fromColumns = {DatabaseTwo.CUBES.KEY_OBNAME};
        //int[] toViews = {R.id.gridview};
        dbTwo.getReadableDatabase();
        dbTwo.getAllColumns();
        Cursor dataTwo = dbTwo.getAllColumns();
       // SimpleCursorAdapter twoAdapter = new SimpleCursorAdapter(this, R.layout.child_activity, dataTwo, fromColumns, toViews,0);
        return twoAdapter;
    }
    public void delObjectColumn(){
        DatabaseH db = new DatabaseH(this);
         try{
       db.deleteTable(position);
             Cursor data = db.getAllColumns();
               int set = 0;//SQL SET command
               int where = 0;//SQL WHERE command
               int toTheBottom = 0;//index to the final column

               int count = data.getCount();//get existing columns in table
               toTheBottom = count - position;//get columns which need the IDs updated
               where = count;
               set = count -1;

               while(toTheBottom != -1){//negative one to reach final column
               db.updateColumnItem(set,where);//uses the SQLLiteDatabase update method
               where++;
               set++;
               toTheBottom--;
               }
             Intent intent = new Intent(this, ListViewLoader.class);
             startActivity(intent);
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

                Intent intent = new Intent(getApplicationContext(),ListViewLoader.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
