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
 * Created by kennethascheri on 11/8/15.
 */
public class ClosetFlowAdapter extends BaseAdapter {

    private ArrayList<ClothingItem> mData = new ArrayList<>(0);
    private ClothingItem.ClothingItemStyle clothingItemStyle;
    private Context mContext;

    public ClosetFlowAdapter(Context context, ClothingItem.ClothingItemStyle clothingItemStyle) {
        mContext = context;
        mData = getClothingItemsByType(clothingItemStyle);
    }

    private ArrayList<ClothingItem> getClothingItemsByType(ClothingItem.ClothingItemStyle clothingItemStyle) {
        ArrayList<ClothingItem> clothingItems = new ArrayList<>(0);
        switch (clothingItemStyle) {
            case TOPS:
                clothingItems.add(new ClothingItem(R.drawable.top01,R.string.tops));
                clothingItems.add(new ClothingItem(R.drawable.top02, R.string.tops));
                clothingItems.add(new ClothingItem(R.drawable.top03, R.string.tops));
//                clothingItems.add(new ClothingItem(R.drawable.top04, R.string.tops));
//                clothingItems.add(new ClothingItem(R.drawable.top05, R.string.tops));
//                clothingItems.add(new ClothingItem(R.drawable.top06, R.string.tops));
                break;
            case BOTTOMS:
                clothingItems.add(new ClothingItem(R.drawable.bottom01, R.string.bottoms));
                clothingItems.add(new ClothingItem(R.drawable.bottom02, R.string.bottoms));
                clothingItems.add(new ClothingItem(R.drawable.bottom03, R.string.bottoms));
                break;
            case SHOES:
                clothingItems.add(new ClothingItem(R.drawable.shoes01, R.string.shoes));
                clothingItems.add(new ClothingItem(R.drawable.shoes02, R.string.shoes));
                clothingItems.add(new ClothingItem(R.drawable.shoes03, R.string.shoes));
                clothingItems.add(new ClothingItem(R.drawable.shoes04, R.string.shoes));
                clothingItems.add(new ClothingItem(R.drawable.shoes05, R.string.shoes));
                break;
            default:
                break;
        }
        return clothingItems;
    }

    public void setData(ArrayList<ClothingItem> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int pos) {
        return mData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_coverflow, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.image);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.image.setImageResource(mData.get(position).imageResId);

        return rowView;
    }


    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}
