package com.beta.dataface;

import android.*;
import android.R;
import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

/**
 * Created by trentonknight on 8/3/13.
 */
public class ListViewLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    SimpleCursorAdapter mAdapter;

    static final String[] PROJECTION = new String[] {DatabaseH.KEYS.KEY_ID,
            DatabaseH.KEYS.KEY_OBNAME};
    static final String SELECTION = "((" + DatabaseH.KEYS.KEY_OBNAME +
            DatabaseH.KEYS.KEY_OBNAME + " != '' ))";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT,
                AbsListView.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        getListView().setEmptyView(progressBar);

        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        root.addView(progressBar);


        String[] fromColumns = {DatabaseH.KEYS.KEY_OBNAME};
        int[] toViews = {R.id.text1};
        DatabaseH db = new DatabaseH(this);
        db.onOpen(db.getReadableDatabase());

        Cursor data = db.getAllColumns();


        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                data, fromColumns, toViews, 0);
        ListView listView = getListView();
        listView.setAdapter(mAdapter);


    }

    // Called when a new Loader needs to be created
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Now create and return a CursorLoader that will take care of
        // creating a Cursor for the data being displayed.
        return new CursorLoader(this, null,
                PROJECTION, SELECTION, null, null);
    }

    // Called when a previously created loader has finished loading
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
    }

    // Called when a previously created loader is reset, making the data unavailable
    public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    }
}