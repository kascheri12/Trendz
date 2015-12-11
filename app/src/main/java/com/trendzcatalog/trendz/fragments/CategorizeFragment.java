package com.trendzcatalog.trendz.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.ImageFile;
import com.trendzcatalog.trendz.viewmodels.implementation.MainActivityViewModel;
import com.trendzcatalog.trendz.viewmodels.interfaces.IMainActivityViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CategorizeFragment extends Fragment {
    private static final String ARG_PARAM1 = "mFile";
    private Context mContext;
    private IMainActivityViewModel viewModel;

    private SeekBar mSeekbar;
    private int mProgress;
    private STATE mState;
    private String mParam1;
    private FrameLayout mImageLayout;
    private FrameLayout mFrameLayout;
    private FrameLayout mStyleFrameLayout;
    private FrameLayout mSubStyleFrameLayout;
    private FrameLayout mMaterialFrameLayout;
    private FrameLayout mColorFrameLayout;
    private ImageView myImage;
    private ImageView mImageViewBackground;

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
        viewModel = new MainActivityViewModel();
        mContext = getActivity();

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
            Picasso.with(getActivity()).load(imgFile).into(myImage);
            setFrameLayoutVisibility(view, true);
            myImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Uploading...", Toast.LENGTH_SHORT).show();
                    List<ImageFile> selectedFiles = new ArrayList<>();
                    ImageFile imageFile = new ImageFile(0,"pic.jpg",mParam1);
                    selectedFiles.add(imageFile);
                    viewModel.uploadImageFiles(selectedFiles);

                    mState = STATE.TWO;
                    setFrameLayoutVisibility(v, false);
                    setStyleLayoutVisibility(v, true);
                    
                    getFragmentManager().popBackStack();
                }
            });
        }
        return view;
    }

    private void setColorLayoutVisibility(View v, boolean visible) {
        if (visible) { mColorFrameLayout.setVisibility(View.VISIBLE); }
        else { mColorFrameLayout.setVisibility(View.INVISIBLE); }
    }
    private void setMaterialLayoutVisibility(View v, boolean visible) {
        if (visible) { mMaterialFrameLayout.setVisibility(View.VISIBLE); }
        else { mMaterialFrameLayout.setVisibility(View.INVISIBLE); }
    }
    private void setSubStyleLayoutVisibility(View v, boolean visible) {
        if (visible) { mSubStyleFrameLayout.setVisibility(View.VISIBLE); }
        else { mSubStyleFrameLayout.setVisibility(View.INVISIBLE); }
    }
    private void setStyleLayoutVisibility(View v, boolean visible) {
        if (visible) { mStyleFrameLayout.setVisibility(View.VISIBLE); }
        else { mStyleFrameLayout.setVisibility(View.INVISIBLE); }
    }
    private void setFrameLayoutVisibility(View view, boolean visible) {
        if (visible) { mFrameLayout.setVisibility(View.VISIBLE); }
        else { mFrameLayout.setVisibility(View.INVISIBLE); }
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mState) {
                case TWO:
                    Toast.makeText(getActivity(), "STATE TWO -> THREE", Toast.LENGTH_SHORT).show();
                    mState = STATE.THREE;
                    break;
                default:
                    Toast.makeText(getActivity(), "Default", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void initViews(View view) {
        mFrameLayout = (FrameLayout) view.findViewById(R.id.sliderLayout);
        mStyleFrameLayout = (FrameLayout) view.findViewById(R.id.styleLayout);
        mSubStyleFrameLayout = (FrameLayout) view.findViewById(R.id.subStyleLayout);
        mMaterialFrameLayout = (FrameLayout) view.findViewById(R.id.materialLayout);
        mColorFrameLayout = (FrameLayout) view.findViewById(R.id.colorLayout);
        mImageLayout = (FrameLayout) view.findViewById(R.id.imageLayout);

        mImageViewBackground = (ImageView) view.findViewById(R.id.imageS);

        myImage = (ImageView) view.findViewById(R.id.imageViewSlideLayout);
        mSeekbar = (SeekBar) view.findViewById(R.id.seekbar_slider);
        mSeekbar.setProgress(mSeekbar.getMax() / 2);
        mSeekbar.setProgressTintMode(PorterDuff.Mode.CLEAR);
        mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            GradientDrawable gd = new GradientDrawable();
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mProgress = progress;
                gd.setGradientRadius(progress * 12);
                gd.setColors(new int[]{Color.TRANSPARENT, Color.rgb(91, 196, 194)});
                gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                mImageViewBackground.setBackground(gd);
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
