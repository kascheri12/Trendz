package com.trendzcatalog.trendz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.DashboardItem;

import java.util.ArrayList;

/**
 * Created by kennethascheri on 11/10/15.
 */
public class DashboardAdapter extends BaseAdapter {
    private ArrayList<DashboardItem> dashboardItems;
    private Context context;

    public DashboardAdapter(Context context, ArrayList<DashboardItem> dashboardItems) {
        this.context = context;
        this.dashboardItems = dashboardItems;
    }

    @Override
    public int getCount() {
        return dashboardItems.size();
    }

    @Override
    public DashboardItem getItem(int position) {
        return dashboardItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DashboardItem movie = getItem(position);
        ImageView imageView;


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_dashboard, parent,
                    false);
        }

        imageView = (ImageView)convertView.findViewById(R.id.itemDashboardImage);
        imageView.setImageResource(movie.imageResId);

        return convertView;
    }
}
