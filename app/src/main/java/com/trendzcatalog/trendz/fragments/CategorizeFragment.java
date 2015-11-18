package com.trendzcatalog.trendz.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;

import java.io.File;

public class CategorizeFragment extends Fragment {
    private static final String ARG_PARAM1 = "mFile";

    private LinearLayout mImageLayout;
    private SeekBar mSeekbar;
    private int mProgress;
    private STATE mState;
    private String mParam1;

    private enum STATE {
        ONE, TWO, THREE, FOUR, FIVE
    }
    // TODO: Rename and change types and number of parameters
    public static CategorizeFragment newInstance(String param1) {
        CategorizeFragment fragment = new CategorizeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public CategorizeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mState = STATE.ONE;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorize, container, false);

        initViews(view);

        File imgFile = new File(mParam1);
        if(imgFile.exists()){
            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            Bitmap rotatedBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
            ImageView myImage = (ImageView) view.findViewById(R.id.imageViewSlideLayout);
            myImage.setImageBitmap(rotatedBitmap);
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.sliderLayout);
            frameLayout.setVisibility(View.VISIBLE);
            frameLayout.setOnClickListener(onClickListener);
        }
        return view;
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mState) {
                case ONE:
                    Toast.makeText(getActivity(), "HEEEY ONE", Toast.LENGTH_SHORT);
                    mState = STATE.TWO;
                case TWO:
                    // Do something else
            }
        }
    };

    private void initViews(View view) {
        mImageLayout = (LinearLayout) view.findViewById(R.id.imageLayout);
        mSeekbar = (SeekBar) view.findViewById(R.id.seekbar_slider);
        mSeekbar.setProgress(mSeekbar.getMax() / 2);
        mSeekbar.setProgressTintMode(PorterDuff.Mode.DARKEN);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;

                GradientDrawable gd = new GradientDrawable();
                gd.setColors(new int[] {Color.BLACK, Color.TRANSPARENT});
                gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                gd.setGradientRadius(progress*20);
                gd.setCornerRadius(progress);

                mImageLayout.setBackground(gd);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
