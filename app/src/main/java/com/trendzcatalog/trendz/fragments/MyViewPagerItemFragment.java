package com.trendzcatalog.trendz.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.MyItem;

import java.util.ArrayList;

/**
 * Created by kennethascheri on 10/22/15.
 */
public class MyViewPagerItemFragment extends Fragment {
    private static final String BUNDLE_KEY_IMAGE_RESOURCE_ID    = "bundle_key_image_resource_id";
    private static final String BUNDLE_KEY_TITLE                = "bundle_key_title";
    private int                 mImageResourceId;
    private String              mTitle;

    ArrayList<String> al;
    ArrayAdapter<String> arrayAdapter;


    public static MyViewPagerItemFragment instantiateWithArgs(final Context context, final MyItem item) {
        final MyViewPagerItemFragment fragment = (MyViewPagerItemFragment) instantiate(context, MyViewPagerItemFragment.class.getName());
        final Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_IMAGE_RESOURCE_ID, item.mImageResourceId);
        args.putString(BUNDLE_KEY_TITLE, item.mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initArguments();
//        initFragments();

        al = new ArrayList<String>();
        al.add("php");
        al.add("c");
        al.add("python");
        al.add("java");

        //choose your favorite adapter
        arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.item, R.id.helloText, al );


    }

    private void initArguments() {
        final Bundle arguments = getArguments();
        if(arguments != null) {
            mImageResourceId = arguments.getInt(BUNDLE_KEY_IMAGE_RESOURCE_ID);
            mTitle = arguments.getString(BUNDLE_KEY_TITLE);
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(view, savedInstanceState);

        //add the view via xml or programmatically
        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) view.findViewById(R.id.frame);

        //set the listener and the adapter
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float f) {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(getContext(), "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(getContext(), "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                for (int i = 0; i < itemsInAdapter; i++) {
                    al.add("XML ".concat(String.valueOf(i)));
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("LIST", "notified");
                }
            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(getContext(), "Clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void initViews(final View view, final Bundle bundle) {
        initLayouts(view, bundle);
    }

    private void initLayouts(final View view, final Bundle bundle) {
        final FrameLayout aFrameLayout, bFrameLayout, content_frame;

        aFrameLayout = (FrameLayout) view.findViewById(R.id.AFrameLayoutWrapper);
        bFrameLayout = (FrameLayout) view.findViewById(R.id.BFrameLayout);
        content_frame = (FrameLayout) view.findViewById(R.id.content_frame);

        if (mTitle == "0") {
            if (bundle == null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.AFrameLayoutWrapper, DashboardFragment.newInstance())
                        .commit();
            }
            aFrameLayout.setVisibility(View.VISIBLE);
        } else if (mTitle == "1") {
            bFrameLayout.setVisibility(View.VISIBLE);
        } else if (mTitle == "2" ) {
            if (bundle == null) {
                getChildFragmentManager()
                        .beginTransaction()
                        .add(R.id.content_frame, ScrollableFragment.newInstance())
                        .commit();
            }
            content_frame.setVisibility(View.VISIBLE);
        }
    }

}
