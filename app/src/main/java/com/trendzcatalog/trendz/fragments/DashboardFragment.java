package com.trendzcatalog.trendz.fragments;

//import android.support.v4.app.Fragment;

//import android.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.trendzcatalog.trendz.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DashboardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static int TAKE_PICTURE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance() {
        final Bundle bundle = new Bundle();
        final DashboardFragment fragment = new DashboardFragment();
        bundle.putString(ARG_PARAM1, "string1");
        bundle.putString(ARG_PARAM2, "string12");
        fragment.setArguments(bundle);
        return fragment;
    }

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initButtons(view);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initButtons(final View view) {
        List<ImageView> btnList = new ArrayList<>();
        btnList.add((ImageView)view.findViewById(R.id.btnFillItUp));
        btnList.add((ImageView)view.findViewById(R.id.btnCloset));
        btnList.add((ImageView)view.findViewById(R.id.btnLaundry));
        btnList.add((ImageView) view.findViewById(R.id.btnPublish));

        for ( int i = 0; i < btnList.size(); i++ ) {
            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()){
                        case R.id.btnFillItUp:
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, TAKE_PICTURE);
                            break;
                        case R.id.btnCloset:
//                            Intent intent = new Intent(getActivity(), ClosetActivity.class)
                        default:
                            Toast.makeText(getContext(), "Hi", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        super.onActivityResult(requestCode, resultCode, intent);
//
//        if ( resultCode == RESULT_OK ) {
//            if (requestCode == TAKE_PICTURE) {
//                Bitmap thumbnail = (Bitmap) intent.getExtras().get("data");
//                Toast.makeText(getApplicationContext(), "HI", Toast.LENGTH_SHORT);
//            }
//        }
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
