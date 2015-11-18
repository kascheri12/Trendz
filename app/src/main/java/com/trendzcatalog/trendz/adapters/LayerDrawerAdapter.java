package com.trendzcatalog.trendz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.ClothingItem;

import java.util.ArrayList;

/**
 * Created by charbgr on 8/30/14.
 */
public class LayerDrawerAdapter extends BaseAdapter {

    private ArrayList<ClothingItem> layers;
    private Context context;

    public LayerDrawerAdapter(Context context, ArrayList<ClothingItem> layers) {
        this.context = context;
        this.layers = layers;
    }

    @Override
    public int getCount() {
        return layers.size();
    }

    @Override
    public ClothingItem getItem(int position) {
        return layers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ClothingItem layer = getItem(position);
        ImageView imageView;
        TextView textView;


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_movie, parent,
                    false);
        }

        imageView = (ImageView)convertView.findViewById(R.id.picture);
//        textView = (TextView)convertView.findViewById(R.id.text);

        imageView.setImageResource(layer.imageResId);
//        textView.setText(layer.getTitleResId());

        return convertView;
    }
}
