package com.hoaiutc95.fragment_menu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoaiutc95.doan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnphabetGameFragment extends Fragment {


    public AnphabetGameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anphabet_game, container, false);

    }

}
