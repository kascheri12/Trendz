package com.trendzcatalog.trendz.fragments;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.ServiceGenerator;
import com.trendzcatalog.trendz.adapters.ImageAdapter;
import com.trendzcatalog.trendz.models.Closet;
import com.trendzcatalog.trendz.models.ClothingArticle;
import com.trendzcatalog.trendz.models.Combination;
import com.trendzcatalog.trendz.models.StyleType;
import com.trendzcatalog.trendz.models.UserInfo;
import com.trendzcatalog.trendz.services.ClosetService;

import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import retrofit.Call;
import retrofit.Response;

/**
 *  Created by kennethascheri on 11/9/15.
 */
public class ClosetFragment extends Fragment {
    private Closet mCloset;
    private UserInfo mUserInfo;

    private FeatureCoverFlow mCoverFlowTop;
    private FeatureCoverFlow mCoverFlowMiddle;
    private FeatureCoverFlow mCoverFlowBottom;
    private int mCoverFlowTopPos;
    private int mCoverFlowMiddlePos;
    private int mCoverFlowBottomPos;
    private ImageAdapter mAdapterTop;
    private ImageAdapter mAdapterMiddle;
    private ImageAdapter mAdapterBottom;

    private CombinationSaveTask combinationSaveTask;
    private ClothingArticleGetTask clothingArticleGetTask;
    private ClothingArticleGetTask clothingArticleGetTaskBottoms;
    private ClothingArticleGetTask clothingArticleGetTaskPants;


    public static ClosetFragment newInstance() {
        final Bundle args = new Bundle();
        final ClosetFragment closetFragment = new ClosetFragment();
        closetFragment.setArguments(args);
        return closetFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_closet, container, false);

        clothingArticleGetTask = new ClothingArticleGetTask();
        clothingArticleGetTaskBottoms = new ClothingArticleGetTask();
        clothingArticleGetTaskPants = new ClothingArticleGetTask();

        initUserData();

        refreshFlows();
        initFlows(view);
        initButtons(view);

        return view;
    }

    private void initUserData() {
        initUserInfo();
    }

    private void initUserInfo() {
        SharedPreferences pref = getActivity().getSharedPreferences("UserInfo", 0);
        mUserInfo = new UserInfo(pref.getInt("UserInfoID", 0), pref.getString("Username", null), pref.getString("Password", null));
        mCloset = new Closet(pref.getInt("ClosetID", 0));
    }

    private void refreshFlows() {
        try {
            clothingArticleGetTask.execute(new StyleType(1)).get();
            clothingArticleGetTaskBottoms.execute(new StyleType(2)).get();
            clothingArticleGetTaskPants.execute(new StyleType(3)).get();
        } catch (Exception ex) {
            Toast.makeText(getContext(), ex.getMessage().toString(), Toast.LENGTH_LONG);
        }

    }

    private void initButtons(View view) {
        ImageView btn = (ImageView) view.findViewById(R.id.btnButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int topid = mAdapterTop.getItemClothingArticleID(mCoverFlowTopPos);
                int bottomid = mAdapterMiddle.getItemClothingArticleID(mCoverFlowMiddlePos);
                int shoesid = mAdapterBottom.getItemClothingArticleID(mCoverFlowBottomPos);

                combinationSaveTask = new CombinationSaveTask();
                combinationSaveTask.execute(new Combination(0, mCloset.ClosetID, topid, bottomid, shoesid, 0, 0));
            }
        });

    }

    private void initFlows(View view) {

        mCoverFlowTop = (FeatureCoverFlow) view.findViewById(R.id.coverflowtop);
        mCoverFlowMiddle = (FeatureCoverFlow) view.findViewById(R.id.coverflowmiddle);
        mCoverFlowBottom = (FeatureCoverFlow) view.findViewById(R.id.coverflowbottom);

        mCoverFlowTop.setAdapter(mAdapterTop);
        mCoverFlowMiddle.setAdapter(mAdapterMiddle);
        mCoverFlowBottom.setAdapter(mAdapterBottom);
        mCoverFlowTop.setReflectionOpacity(0);
        mCoverFlowMiddle.setReflectionOpacity(0);
        mCoverFlowBottom.setReflectionOpacity(0);
        mCoverFlowTop.setAdjustPositionMultiplier(2.5f);
        mCoverFlowMiddle.setAdjustPositionMultiplier(2.5f);
        mCoverFlowBottom.setAdjustPositionMultiplier(2.5f);
        mCoverFlowTop.setMaxScaleFactor(1.8f);
        mCoverFlowMiddle.setMaxScaleFactor(1.8f);
        mCoverFlowBottom.setMaxScaleFactor(1.3f);

        mCoverFlowTop.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowTopPos = position;
            }

            @Override
            public void onScrolling() {
            }
        });
        mCoverFlowMiddle.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowMiddlePos = position;
            }

            @Override
            public void onScrolling() {

            }
        });
        mCoverFlowBottom.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mCoverFlowBottomPos = position;
            }

            @Override
            public void onScrolling() {

            }
        });
    }

    public class CombinationSaveTask extends AsyncTask<Combination, Void, Boolean> {
        CombinationSaveTask() {
        }
        @Override
        protected Boolean doInBackground(Combination... params) {
            Combination c = params[0];
            ClosetService closetService = ServiceGenerator.createService(ClosetService.class);
            Call<Combination> call = closetService.SaveCombination(c.CombinationID, c.ClosetID, c.ClothingArticleTopID, c.ClothingArticleBottomID, c.ClothingArticleShoesID, c.ClothingArticleDressesID, c.ClothingArticleLayerID);
            try {
                Response<Combination> combinations = call.execute();
                if (combinations.body() != null
                        && combinations.body().CombinationID > 0) {
                    return true;
                }
            } catch (Exception ex) {

            }
            return false;
        }
        @Override
        protected void onPostExecute(final Boolean success) {
            if (!success) {
                Toast.makeText(getContext(), "Error saving combination.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Saved!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public class ClothingArticleGetTask extends AsyncTask<StyleType, Void, Boolean> {

        ClothingArticleGetTask() {
            mAdapterTop = new ImageAdapter(getContext());
            mAdapterMiddle = new ImageAdapter(getContext());
            mAdapterBottom = new ImageAdapter(getContext());
        }

        private int getUserInfoID() {
            SharedPreferences pref = getActivity().getSharedPreferences("UserInfo", 0);
            return pref.getInt("UserInfoID", 0);
        }

        @Override
        protected Boolean doInBackground(StyleType... params) {
            int StyleTypeID = params[0].StyleTypeID;
            ClosetService closetService = ServiceGenerator.createService(ClosetService.class);
            Call<List<ClothingArticle>> call = closetService.GetClothes(String.valueOf(getUserInfoID()),String.valueOf(StyleTypeID));
            try {
                Response<List<ClothingArticle>> clothes = call.execute();
                if (clothes.body() != null) {
                    switch(StyleTypeID) {
                        case 1:
                            mAdapterTop.setClothes(clothes.body());
                            break;
                        case 2:
                            mAdapterMiddle.setClothes(clothes.body());
                            break;
                        case 3:
                            mAdapterBottom.setClothes(clothes.body());
                            break;
                        default:
                            break;

                    }
                    return true;
                }
            } catch (Exception ex) {
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }
    }
}
