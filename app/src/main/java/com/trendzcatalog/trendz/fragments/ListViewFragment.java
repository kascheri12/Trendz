package com.trendzcatalog.trendz.fragments;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.ServiceGenerator;
import com.trendzcatalog.trendz.adapters.HomeCombinationsAdapter;
import com.trendzcatalog.trendz.models.Combination;
import com.trendzcatalog.trendz.services.ClosetService;

import java.util.List;

import retrofit.Call;
import retrofit.Response;

/**
 * Created by kennethascheri on 10/25/15.
 */
public class ListViewFragment extends BaseFragment {

    private CombinationGetTask mCombinationGetTask;
    private HomeCombinationsAdapter mHomeCombinationAdapter;
    private ListView mListView;

    public static ListViewFragment newInstance() {
        final Bundle bundle = new Bundle();
        final ListViewFragment fragment = new ListViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
        final View view = inflater.inflate(R.layout.fragment_list_view, parent, false);
        mCombinationGetTask = new CombinationGetTask();
        refreshView();

        mListView = (ListView) view.findViewById(R.id.list_view);
        mListView.setAdapter(mHomeCombinationAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void refreshView() {
        try {
            mCombinationGetTask.execute((Void) null).get();
        } catch (Exception ex) {

        }
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return mListView != null && mListView.canScrollVertically(direction);
    }

    @Override
    public CharSequence getTitle(Resources r) {
        return null;
    }

    @Override
    public String getSelfTag() {
        return null;
    }


    public class CombinationGetTask extends AsyncTask<Void, Void, Boolean> {

        CombinationGetTask() {
            mHomeCombinationAdapter = new HomeCombinationsAdapter(getContext());
        }

        private int getUserInfoID() {
            SharedPreferences pref = getActivity().getSharedPreferences("UserInfo", 0);
            return pref.getInt("UserInfoID", 0);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ClosetService closetService = ServiceGenerator.createService(ClosetService.class);
            Call<List<Combination>> call = closetService.GetCombinations(String.valueOf(getUserInfoID()));
            try {
                Response<List<Combination>> combinations = call.execute();
                if (combinations.body() != null) {
                    mHomeCombinationAdapter.setCombinations(combinations.body());
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
