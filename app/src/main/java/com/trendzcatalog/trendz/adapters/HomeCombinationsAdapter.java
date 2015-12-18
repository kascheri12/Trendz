package com.trendzcatalog.trendz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kennethascheri on 12/12/15.
 */
public class HomeCombinationsAdapter extends BaseAdapter {
    private List<Combination> combinations;
    private Context context;

    public HomeCombinationsAdapter(Context context) {
        this.context = context;
    }

    public void setCombinations (List<Combination> combinations) { this.combinations = combinations; }

    @Override
    public int getCount() {
        return combinations.size();
    }

    @Override
    public Combination getItem(int position) {
        return combinations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Combination layer = getItem(position);
        List<ImageView> imageViews = new ArrayList<>(0);
        TextView textView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_combination_home, parent,false);
        }

        imageViews.add((ImageView) convertView.findViewById(R.id.top));
        imageViews.add((ImageView) convertView.findViewById(R.id.bottoms));
        imageViews.add((ImageView) convertView.findViewById(R.id.shoes));
        imageViews.add((ImageView) convertView.findViewById(R.id.dress));
        imageViews.add((ImageView) convertView.findViewById(R.id.layer));

        Picasso.with(parent.getContext()).load(layer.ClothingArticleTop.ImageFile.getImageFileLocation()).rotate(90).resizeDimen(R.dimen.profile_combination_image_width,R.dimen.profile_combination_image_height).into(imageViews.get(0));
        Picasso.with(parent.getContext()).load(layer.ClothingArticleBottom.ImageFile.getImageFileLocation()).rotate(90).resizeDimen(R.dimen.profile_combination_image_width, R.dimen.profile_combination_image_height).into(imageViews.get(1));
        Picasso.with(parent.getContext()).load(layer.ClothingArticleShoes.ImageFile.getImageFileLocation()).rotate(90).resizeDimen(R.dimen.profile_combination_image_width, R.dimen.profile_combination_image_height).into(imageViews.get(2));

        return convertView;
    }
}
