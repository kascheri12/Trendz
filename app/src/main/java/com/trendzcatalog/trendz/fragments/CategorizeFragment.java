package com.trendzcatalog.trendz.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.squareup.picasso.Picasso;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.models.ClothingArticle;
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
    private LinearLayout mSubStyleTopLayout;
    private LinearLayout mSubStyleBottomLayout;
    private LinearLayout mSubStyleShoesLayout;
    private LinearLayout mSubStyleDressLayout;
    private FrameLayout mMaterialFrameLayout;
    private FrameLayout mColorFrameLayout;
    private ImageView myImage;
    private ImageView mImageViewBackground;
    private ClothingArticle mClothingArticle;

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
        mClothingArticle = new ClothingArticle(0);
    }

    private int getClosetIDFromPref() {
        SharedPreferences pref = getActivity().getSharedPreferences("UserInfo", 0);
        return pref.getInt("ClosetID", 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new MainActivityViewModel();
        mContext = getActivity();
        mClothingArticle.ClosetID = getClosetIDFromPref();

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

        return view;
    }

    private void setColorLayoutVisibility(View v, boolean visible) {
        if (visible) { mColorFrameLayout.setVisibility(View.VISIBLE); }
        else { mColorFrameLayout.setVisibility(View.GONE); }
    }
    private void setMaterialLayoutVisibility(View v, boolean visible) {
        if (visible) { mMaterialFrameLayout.setVisibility(View.VISIBLE); }
        else { mMaterialFrameLayout.setVisibility(View.GONE); }
    }
    private void setSubStyleLayoutVisibility(View v, boolean visible) {
        if (visible) { mSubStyleFrameLayout.setVisibility(View.VISIBLE); }
        else { mSubStyleFrameLayout.setVisibility(View.GONE); }
    }
    private void setStyleLayoutVisibility(View v, boolean visible) {
        if (visible) { mStyleFrameLayout.setVisibility(View.VISIBLE); }
        else { mStyleFrameLayout.setVisibility(View.GONE); }
    }
    private void setFrameLayoutVisibility(View view, boolean visible) {
        if (visible) { mFrameLayout.setVisibility(View.VISIBLE); }
        else { mFrameLayout.setVisibility(View.GONE); }
    }

    private void uploadFile() {
        Toast.makeText(getActivity(), "Uploading...", Toast.LENGTH_SHORT).show();
        List<ImageFile> selectedFiles = new ArrayList<>();
        ImageFile imageFile = new ImageFile(0,"pic.jpg",mParam1);
        selectedFiles.add(imageFile);
        viewModel.uploadImageFiles(selectedFiles, mClothingArticle);
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mState) {
                case ONE:
                    myImage.setOnClickListener(null);
                    setFrameLayoutVisibility(v, false);
                    setStyleLayoutVisibility(v, true);
                    mState = STATE.TWO;
                    break;
                case TWO:
                    switch (v.getId()) {
                        case R.id.btnTop:
                            mClothingArticle.StyleTypeID = 1;
                            mSubStyleTopLayout.setVisibility(View.VISIBLE);
                            break;
                        case R.id.btnBottom:
                            mClothingArticle.StyleTypeID = 2;
                            mSubStyleBottomLayout.setVisibility(View.VISIBLE);
                            break;
                        case R.id.btnShoes:
                            mClothingArticle.StyleTypeID = 3;
                            mSubStyleShoesLayout.setVisibility(View.VISIBLE);
                            break;
                        case R.id.btnDress:
                            mClothingArticle.StyleTypeID = 4;
                            mSubStyleShoesLayout.setVisibility(View.VISIBLE);
                            break;
                    }
                    setStyleLayoutVisibility(v, false);
                    initStateThree(getView());
                    setSubStyleLayoutVisibility(v, true);
                    mState = STATE.THREE;
                    break;
                case THREE:
                    switch(v.getId()) {
                        case R.id.btnTopsBlouse:
                            mClothingArticle.SubStyleTypeID = 1;
                            break;
                        case R.id.btnTopsAthletic:
                            mClothingArticle.SubStyleTypeID = 2;
                            break;
                        case R.id.btnTopsButtonDown:
                            mClothingArticle.SubStyleTypeID = 3;
                            break;
                        case R.id.btnTopsTankCami:
                            mClothingArticle.SubStyleTypeID = 4;
                            break;
                        case R.id.btnTopsCollar:
                            mClothingArticle.SubStyleTypeID = 5;
                            break;
                        case R.id.btnTopsPullover:
                            mClothingArticle.SubStyleTypeID = 6;
                            break;
                        case R.id.btnTopsSweater:
                            mClothingArticle.SubStyleTypeID = 7;
                            break;
                        case R.id.btnTopsTShirt:
                            mClothingArticle.SubStyleTypeID = 8;
                            break;
                        case R.id.btnTopsTunic:
                            mClothingArticle.SubStyleTypeID = 9;
                            break;

                        case R.id.btnBottomsActiveYoga:
                            mClothingArticle.SubStyleTypeID = 10;
                            break;
                        case R.id.btnBottomsJeans:
                            mClothingArticle.SubStyleTypeID = 11;
                            break;
                        case R.id.btnBottomsLeggings:
                            mClothingArticle.SubStyleTypeID = 12;
                            break;
                        case R.id.btnBottomsPants:
                            mClothingArticle.SubStyleTypeID = 13;
                            break;
                        case R.id.btnBottomsShorts:
                            mClothingArticle.SubStyleTypeID = 14;
                            break;
                        case R.id.btnBottomsSkirt:
                            mClothingArticle.SubStyleTypeID = 15;
                            break;

                        case R.id.btnShoesAthletic:
                            mClothingArticle.SubStyleTypeID = 16;
                            break;
                        case R.id.btnShoesBooties:
                            mClothingArticle.SubStyleTypeID = 17;
                            break;
                        case R.id.btnShoesBoots:
                            mClothingArticle.SubStyleTypeID = 18;
                            break;
                        case R.id.btnShoesCasual:
                            mClothingArticle.SubStyleTypeID = 19;
                            break;
                        case R.id.btnShoesDress:
                            mClothingArticle.SubStyleTypeID = 20;
                            break;
                        case R.id.btnShoesFlats:
                            mClothingArticle.SubStyleTypeID = 21;
                            break;
                        case R.id.btnShoesHeels:
                            mClothingArticle.SubStyleTypeID = 22;
                            break;
                        case R.id.btnShoesSandals:
                            mClothingArticle.SubStyleTypeID = 23;
                            break;

                        case R.id.btnDressBodycon:
                            mClothingArticle.SubStyleTypeID = 24;
                            break;
                        case R.id.btnDressCocktail:
                            mClothingArticle.SubStyleTypeID = 25;
                            break;
                        case R.id.btnDressEvening:
                            mClothingArticle.SubStyleTypeID = 26;
                            break;
                        case R.id.btnDressLbd:
                            mClothingArticle.SubStyleTypeID = 27;
                            break;
                        case R.id.btnDressMaxi:
                            mClothingArticle.SubStyleTypeID = 28;
                            break;
                        case R.id.btnDressShirt:
                            mClothingArticle.SubStyleTypeID = 29;
                            break;
                        case R.id.btnDressSummer:
                            mClothingArticle.SubStyleTypeID = 30;
                            break;
                        case R.id.btnDressSweater:
                            mClothingArticle.SubStyleTypeID = 31;
                            break;
                        case R.id.btnDressWork:
                            mClothingArticle.SubStyleTypeID = 32;
                            break;
                    }
                    initStateFour(getView());
                    setSubStyleLayoutVisibility(v, false);
                    setColorLayoutVisibility(v, true);
                    mState = STATE.FOUR;
                    break;
                case FOUR:
                    switch(v.getId()) {
                        case R.id.btnColorBlack:
                            mClothingArticle.ColorID = 1;
                    }
                    initStateFive(getView());
                    setColorLayoutVisibility(v, false);
                    setMaterialLayoutVisibility(v, true);
                    mState = STATE.FIVE;
                    break;
                case FIVE:
                    switch (v.getId()) {
                        case R.id.btnMaterialCashmere:
                            mClothingArticle.MaterialID = 1;
                            break;
                        case R.id.btnMaterialCotton:
                            mClothingArticle.MaterialID = 2;
                            break;
                        case R.id.btnMaterialDenim:
                            mClothingArticle.MaterialID = 3;
                            break;
                        case R.id.btnMaterialJerseyKnit:
                            mClothingArticle.MaterialID = 4;
                            break;
                        case R.id.btnMaterialLace:
                            mClothingArticle.MaterialID = 5;
                            break;
                        case R.id.btnMaterialLeather:
                            mClothingArticle.MaterialID = 6;
                            break;
                        case R.id.btnMaterialSilk:
                            mClothingArticle.MaterialID = 7;
                            break;
                        case R.id.btnMaterialSpandex:
                            mClothingArticle.MaterialID = 8;
                            break;
                        case R.id.btnMaterialWool:
                            mClothingArticle.MaterialID = 9;
                            break;
                    }
            }

            if (mClothingArticle.StyleTypeID > 0
                    && mClothingArticle.SubStyleTypeID > 0
                    && mClothingArticle.ColorID > 0
                    && mClothingArticle.MaterialID > 0
                    && mClothingArticle.ClosetID > 0) {
                uploadFile();

            }
        }
    };

    private void initStateOne(View view) {
        mImageViewBackground = (ImageView) view.findViewById(R.id.imageS);
        myImage = (ImageView) view.findViewById(R.id.imageViewSlideLayout);
        mSeekbar = (SeekBar) view.findViewById(R.id.seekbar_slider);
        mSeekbar.setProgress(mSeekbar.getMax() / 2);
//        mSeekbar.setProgressTintMode(PorterDuff.Mode.CLEAR);
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


        File imgFile = new File(mParam1);
        if(imgFile.exists()){
            Picasso.with(getActivity()).load(imgFile).into(myImage);
            setFrameLayoutVisibility(view, true);
            myImage.setOnClickListener(onClickListener);
        }
    }

    private void initStateTwo(View view) {
        ImageView btnTop = (ImageView) view.findViewById(R.id.btnTop);
        ImageView btnBottom = (ImageView) view.findViewById(R.id.btnBottom);
        ImageView btnShoes = (ImageView) view.findViewById(R.id.btnShoes);
        ImageView btnDress = (ImageView) view.findViewById(R.id.btnDress);

        btnTop.setOnClickListener(onClickListener);
        btnBottom.setOnClickListener(onClickListener);
        btnShoes.setOnClickListener(onClickListener);
        btnDress.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.top_button).resize(300,300).into(btnTop);
        Picasso.with(getActivity()).load(R.drawable.bottom_button).resize(300, 300).into(btnBottom);
        Picasso.with(getActivity()).load(R.drawable.shoes_button).resize(300, 300).into(btnShoes);
        Picasso.with(getActivity()).load(R.drawable.dress_button).resize(300,300).into(btnDress);
    }

    private void initSubStyleTop(View view) {
        ImageView btnBlouse = (ImageView) view.findViewById(R.id.btnTopsBlouse);
        ImageView btnCrop = (ImageView) view.findViewById(R.id.btnTopsCrop);
        ImageView btnCami = (ImageView) view.findViewById(R.id.btnTopsTankCami);
        ImageView btnTunic = (ImageView) view.findViewById(R.id.btnTopsTunic);
        ImageView btnCollar = (ImageView) view.findViewById(R.id.btnTopsCollar);
        ImageView btnButtonDown = (ImageView) view.findViewById(R.id.btnTopsButtonDown);
        ImageView btnSweater = (ImageView) view.findViewById(R.id.btnTopsSweater);
        ImageView btnPullover = (ImageView) view.findViewById(R.id.btnTopsPullover);
        ImageView btnTShirt = (ImageView) view.findViewById(R.id.btnTopsTShirt);
        ImageView btnAthletic = (ImageView) view.findViewById(R.id.btnTopsAthletic);

        btnBlouse.setOnClickListener(onClickListener);
        btnCrop.setOnClickListener(onClickListener);
        btnCami.setOnClickListener(onClickListener);
        btnTunic.setOnClickListener(onClickListener);
        btnCollar.setOnClickListener(onClickListener);
        btnButtonDown.setOnClickListener(onClickListener);
        btnSweater.setOnClickListener(onClickListener);
        btnPullover.setOnClickListener(onClickListener);
        btnTShirt.setOnClickListener(onClickListener);
        btnAthletic.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.top_blouse).resize(200, 200).into(btnBlouse);
        Picasso.with(getActivity()).load(R.drawable.top_cami).resize(200, 200).into(btnCami);
        Picasso.with(getActivity()).load(R.drawable.top_crop).resize(200, 200).into(btnCrop);
        Picasso.with(getActivity()).load(R.drawable.top_tunic).resize(200, 200).into(btnTunic);
        Picasso.with(getActivity()).load(R.drawable.top_collar).resize(200, 200).into(btnCollar);
        Picasso.with(getActivity()).load(R.drawable.top_button_down).resize(200, 200).into(btnButtonDown);
        Picasso.with(getActivity()).load(R.drawable.top_sweater).resize(200, 200).into(btnSweater);
        Picasso.with(getActivity()).load(R.drawable.top_pullover).resize(200, 200).into(btnPullover);
        Picasso.with(getActivity()).load(R.drawable.top_tshirt).resize(200, 200).into(btnTShirt);
        Picasso.with(getActivity()).load(R.drawable.top_athletic).resize(200, 200).into(btnAthletic);
    }

    private void initSubStyleBottom(View view) {
        ImageView btnPants = (ImageView) view.findViewById(R.id.btnBottomsPants);
        ImageView btnJeans = (ImageView) view.findViewById(R.id.btnBottomsJeans);
        ImageView btnSkirt = (ImageView) view.findViewById(R.id.btnBottomsSkirt);
        ImageView btnShorts = (ImageView) view.findViewById(R.id.btnBottomsShorts);
        ImageView btnLeggings = (ImageView) view.findViewById(R.id.btnBottomsLeggings);
        ImageView btnActiveYoga = (ImageView) view.findViewById(R.id.btnBottomsActiveYoga);

        btnPants.setOnClickListener(onClickListener);
        btnJeans.setOnClickListener(onClickListener);
        btnSkirt.setOnClickListener(onClickListener);
        btnShorts.setOnClickListener(onClickListener);
        btnLeggings.setOnClickListener(onClickListener);
        btnActiveYoga.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.bottoms_pants).resize(200, 200).into(btnPants);
        Picasso.with(getActivity()).load(R.drawable.bottoms_jeans).resize(200, 200).into(btnJeans);
        Picasso.with(getActivity()).load(R.drawable.bottoms_skirt).resize(200, 200).into(btnSkirt);
        Picasso.with(getActivity()).load(R.drawable.bottoms_shorts).resize(200, 200).into(btnShorts);
        Picasso.with(getActivity()).load(R.drawable.bottoms_leggings).resize(200, 200).into(btnLeggings);
        Picasso.with(getActivity()).load(R.drawable.bottoms_active_yoga).resize(200, 200).into(btnActiveYoga);
    }

    private void initSubStyleShoes(View view) {
        ImageView btnAthletic = (ImageView) view.findViewById(R.id.btnShoesAthletic);
        ImageView btnBooties = (ImageView) view.findViewById(R.id.btnShoesBooties);
        ImageView btnBoots = (ImageView) view.findViewById(R.id.btnShoesBoots);
        ImageView btnCasual = (ImageView) view.findViewById(R.id.btnShoesCasual);
        ImageView btnDress = (ImageView) view.findViewById(R.id.btnShoesDress);
        ImageView btnFlats = (ImageView) view.findViewById(R.id.btnShoesFlats);
        ImageView btnHeels = (ImageView) view.findViewById(R.id.btnShoesHeels);
        ImageView btnSandals = (ImageView) view.findViewById(R.id.btnShoesSandals);
        ImageView btnWedges = (ImageView) view.findViewById(R.id.btnShoesWedges);

        btnAthletic.setOnClickListener(onClickListener);
        btnBooties.setOnClickListener(onClickListener);
        btnBoots.setOnClickListener(onClickListener);
        btnCasual.setOnClickListener(onClickListener);
        btnDress.setOnClickListener(onClickListener);
        btnFlats.setOnClickListener(onClickListener);
        btnHeels.setOnClickListener(onClickListener);
        btnSandals.setOnClickListener(onClickListener);
        btnWedges.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.shoes_athletic).resize(200,200).into(btnAthletic);
        Picasso.with(getActivity()).load(R.drawable.shoes_booties).resize(200,200).into(btnBooties);
        Picasso.with(getActivity()).load(R.drawable.shoes_boots).resize(200,200).into(btnBoots);
        Picasso.with(getActivity()).load(R.drawable.shoes_casual).resize(200,200).into(btnCasual);
        Picasso.with(getActivity()).load(R.drawable.shoes_dress).resize(200,200).into(btnDress);
        Picasso.with(getActivity()).load(R.drawable.shoes_flats).resize(200,200).into(btnFlats);
        Picasso.with(getActivity()).load(R.drawable.shoes_heels).resize(200,200).into(btnHeels);
        Picasso.with(getActivity()).load(R.drawable.shoes_sandals).resize(200,200).into(btnSandals);
        Picasso.with(getActivity()).load(R.drawable.shoes_wedges).resize(200,200).into(btnWedges);
    }

    private void initSubStyleDresses(View view) {
        ImageView btnBodycon = (ImageView) view.findViewById(R.id.btnDressBodycon);
        ImageView btnCocktail = (ImageView) view.findViewById(R.id.btnDressCocktail);
        ImageView btnEvening = (ImageView) view.findViewById(R.id.btnDressEvening);
        ImageView btnLBD = (ImageView) view.findViewById(R.id.btnDressLbd);
        ImageView btnMaxi = (ImageView) view.findViewById(R.id.btnDressMaxi);
        ImageView btnShirt = (ImageView) view.findViewById(R.id.btnDressShirt);
        ImageView btnSummer = (ImageView) view.findViewById(R.id.btnDressSummer);
        ImageView btnSweater = (ImageView) view.findViewById(R.id.btnDressSweater);
        ImageView btnWork = (ImageView) view.findViewById(R.id.btnDressWork);

        btnBodycon.setOnClickListener(onClickListener);
        btnCocktail.setOnClickListener(onClickListener);
        btnEvening.setOnClickListener(onClickListener);
        btnLBD.setOnClickListener(onClickListener);
        btnMaxi.setOnClickListener(onClickListener);
        btnShirt.setOnClickListener(onClickListener);
        btnSummer.setOnClickListener(onClickListener);
        btnSweater.setOnClickListener(onClickListener);
        btnWork.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.dress_bodycon).resize(200,200).into(btnBodycon);
        Picasso.with(getActivity()).load(R.drawable.dress_cocktail).resize(200,200).into(btnCocktail);
        Picasso.with(getActivity()).load(R.drawable.dress_evening).resize(200,200).into(btnEvening);
        Picasso.with(getActivity()).load(R.drawable.dress_lbd).resize(200,200).into(btnLBD);
        Picasso.with(getActivity()).load(R.drawable.dress_maxi).resize(200,200).into(btnMaxi);
        Picasso.with(getActivity()).load(R.drawable.dress_shirt).resize(200,200).into(btnShirt);
        Picasso.with(getActivity()).load(R.drawable.dress_summer).resize(200,200).into(btnSummer);
        Picasso.with(getActivity()).load(R.drawable.dress_sweater).resize(200,200).into(btnSweater);
        Picasso.with(getActivity()).load(R.drawable.dress_work).resize(200,200).into(btnWork);
    }

    private void initStateThree(View view) {
        switch(mClothingArticle.StyleTypeID) {
            case 1:
                mSubStyleTopLayout.setVisibility(View.VISIBLE);
                initSubStyleTop(view);
                break;
            case 2:
                mSubStyleBottomLayout.setVisibility(View.VISIBLE);
                initSubStyleBottom(view);
                break;
            case 3:
                mSubStyleShoesLayout.setVisibility(View.VISIBLE);
                initSubStyleShoes(view);
                break;
            case 4:
                mSubStyleDressLayout.setVisibility(View.VISIBLE);
                initSubStyleDresses(view);
                break;
        }
    }

    private void initStateFour(View view) {
        ImageView btnBlack = (ImageView) view.findViewById(R.id.btnColorBlack);

        btnBlack.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.dress_button).resize(200,200).into(btnBlack);
    }

    private void initStateFive(View view) {
        ImageView btnCashmere = (ImageView) view.findViewById(R.id.btnMaterialCashmere);
        ImageView btnCotton = (ImageView) view.findViewById(R.id.btnMaterialCotton);
        ImageView btnDenim = (ImageView) view.findViewById(R.id.btnMaterialDenim);
        ImageView btnJersey = (ImageView) view.findViewById(R.id.btnMaterialJerseyKnit);
        ImageView btnLace = (ImageView) view.findViewById(R.id.btnMaterialLace);
        ImageView btnLeather = (ImageView) view.findViewById(R.id.btnMaterialLeather);
        ImageView btnLinen = (ImageView) view.findViewById(R.id.btnMaterialLinen);
        ImageView btnSilk = (ImageView) view.findViewById(R.id.btnMaterialSilk);
        ImageView btnSpandex = (ImageView) view.findViewById(R.id.btnMaterialSpandex);
        ImageView btnWool = (ImageView) view.findViewById(R.id.btnMaterialWool);

        btnCashmere.setOnClickListener(onClickListener);
        btnCotton.setOnClickListener(onClickListener);
        btnDenim.setOnClickListener(onClickListener);
        btnJersey.setOnClickListener(onClickListener);
        btnLace.setOnClickListener(onClickListener);
        btnLeather.setOnClickListener(onClickListener);
        btnLinen.setOnClickListener(onClickListener);
        btnSilk.setOnClickListener(onClickListener);
        btnSpandex.setOnClickListener(onClickListener);
        btnWool.setOnClickListener(onClickListener);

        Picasso.with(getActivity()).load(R.drawable.material_cashmere).resize(200,200).into(btnCashmere);
        Picasso.with(getActivity()).load(R.drawable.material_cotton).resize(200,200).into(btnCotton);
        Picasso.with(getActivity()).load(R.drawable.material_denim).resize(200,200).into(btnDenim);
        Picasso.with(getActivity()).load(R.drawable.material_jersey_knit).resize(200,200).into(btnJersey);
        Picasso.with(getActivity()).load(R.drawable.material_lace).resize(200,200).into(btnLace);
        Picasso.with(getActivity()).load(R.drawable.material_leather).resize(200,200).into(btnLeather);
        Picasso.with(getActivity()).load(R.drawable.material_linen).resize(200,200).into(btnLinen);
        Picasso.with(getActivity()).load(R.drawable.material_silk).resize(200,200).into(btnSilk);
        Picasso.with(getActivity()).load(R.drawable.material_spandex).resize(200,200).into(btnSpandex);
        Picasso.with(getActivity()).load(R.drawable.material_wool).resize(200,200).into(btnWool);
    }

    private void initLayouts(View view) {
        mFrameLayout = (FrameLayout) view.findViewById(R.id.sliderLayout);
        mImageLayout = (FrameLayout) view.findViewById(R.id.imageLayout);
        mStyleFrameLayout = (FrameLayout) view.findViewById(R.id.styleLayout);
        mSubStyleFrameLayout = (FrameLayout) view.findViewById(R.id.subStyleLayout);
        mMaterialFrameLayout = (FrameLayout) view.findViewById(R.id.materialLayout);
        mColorFrameLayout = (FrameLayout) view.findViewById(R.id.colorLayout);
        mSubStyleTopLayout = (LinearLayout) view.findViewById(R.id.layoutTops);
        mSubStyleBottomLayout = (LinearLayout) view.findViewById(R.id.layoutBottoms);
        mSubStyleShoesLayout = (LinearLayout) view.findViewById(R.id.layoutShoes);
        mSubStyleDressLayout = (LinearLayout) view.findViewById(R.id.layoutDress);
    }

    private void initViews(View view) {
        initLayouts(view);
        initStateOne(view);
        initStateTwo(view);
    }
}
