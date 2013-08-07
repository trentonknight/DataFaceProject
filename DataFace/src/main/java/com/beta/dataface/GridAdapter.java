package com.beta.dataface;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by trentonknight on 8/6/13.
 */
public class GridAdapter extends BaseAdapter{
    private Context mContext;


    public GridAdapter(Context c){
        mContext = c;
    }

    public Object getItem(int position){
        return 0;
    }

    public int getCount(){
        return mThumbIds.length;
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageview;
        if(convertView == null){
            imageview = new ImageView(mContext);
            imageview.setLayoutParams(new GridView.LayoutParams(85,85));
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setPadding(8,8,8,8);
        }
        else{
            imageview = (ImageView) convertView;
        }
        imageview.setImageResource(mThumbIds[position]);
        return imageview;
    }

    private Integer[] mThumbIds = {
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker,
            R.drawable.thinker, R.drawable.thinker
    };

}
