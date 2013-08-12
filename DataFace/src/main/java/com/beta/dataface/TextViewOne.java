package com.beta.dataface;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TextViewOne extends Activity {


private static int position = 0;
SimpleCursorAdapter twoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_view_one);
        // Show the Up button in the action bar.
        setupActionBar();



        DatabaseOne db = new DatabaseOne(this);

        Bundle bundle = getIntent().getExtras();
        int newContent = bundle.getInt("position");
        newContent++;
        position = newContent;///pass to position for delObjectColumn

        ConstructorOne singleObject = db.getSingleObject(newContent);

        //int id_number = singleObject.getID(); save for rainy day
        String OB_name = singleObject.getOB();
        String content_now = singleObject.getContent();
        db.close();

        TextView tv = (TextView)findViewById(R.id.textView);
        TextView tv_two = (TextView) findViewById(R.id.textView2);
        tv.setText(content_now);
        tv_two.setText(OB_name);

    }

    public void delObjectColumn(){
        DatabaseOne db = new DatabaseOne(this);
         try{
       db.deleteTable(position);
             Cursor data = db.getAllColumns();
               int set = 0;//SQL SET command
               int where = 0;//SQL WHERE command
               int toTheBottom = 0;//index to the final column

               int count = data.getCount();//get existing columns in table
               toTheBottom = count;//get columns which need the IDs updated
               where = count;
               set = count -1;

               //negative one to reach final column
             do{
               db.updateColumnItem(set,where);//uses the SQLLiteDatabase update method
               where++;
               set++;
               toTheBottom--;
               }while(toTheBottom != 0);

             Intent intent = new Intent(this, ListViewLoaderOne.class);
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
         getMenuInflater().inflate(R.menu.text_view_one, menu);
        return true;
    }
    
    public void contentAdder()
    {           DatabaseOne db = new DatabaseOne(this);
                ConstructorOne singleObject = db.getSingleObject(position);
                String OB_name = singleObject.getOB();
                Intent intentTwo = new Intent(getApplicationContext(), GetUserTextInputTwo.class);
                Bundle bundle = new Bundle();
                bundle.putString("OB_name",OB_name);
                intentTwo.putExtras(bundle);
                startActivity(intentTwo);
    }
    public void contentViewer()
    {
                DatabaseOne db = new DatabaseOne(this);
                ConstructorOne singleObject = db.getSingleObject(position);
                String OB_name = singleObject.getOB();
                Intent intentTwo = new Intent(getApplicationContext(), ListViewLoaderTwo.class);
                Bundle bundle = new Bundle();
                bundle.putString("OB_name",OB_name);
                intentTwo.putExtras(bundle);
                startActivity(intentTwo);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.content_discard:
                 delObjectColumn();
                return true;
            case R.id.content_add:
                contentAdder();
                return true;
            case R.id.view_as_list:
                contentViewer();
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
