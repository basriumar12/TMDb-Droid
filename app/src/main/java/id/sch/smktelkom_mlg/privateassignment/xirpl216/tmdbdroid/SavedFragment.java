package id.sch.smktelkom_mlg.privateassignment.xirpl216.tmdbdroid;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Dictionary;


/**
 * A simple {@link Fragment} subclass.
 */


public class SavedFragment extends Fragment {


    public SavedFragment() {
        // Required empty public constructor

    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ourView = inflater.inflate(R.layout.fragment_saved, container, false);

        return ourView;
    }



}
