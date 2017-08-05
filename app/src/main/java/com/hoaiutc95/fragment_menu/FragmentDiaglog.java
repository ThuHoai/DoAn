package com.hoaiutc95.fragment_menu;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.hoaiutc95.doan.CustomGridView;
import com.hoaiutc95.doan.Item_grid;
import com.hoaiutc95.doan.R;
import com.hoaiutc95.doan.loaddata;

import java.util.ArrayList;

/**
 * Created by Thu Hoai on 5/20/2017.
 */

public class FragmentDiaglog extends DialogFragment {
    View rootView;
    ImageView imgName;
    ArrayList<Item_grid> lstCatagory;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.dialgo_catagory,null);

        GridView gr = (GridView) rootView.findViewById(R.id.grv_dialog);
        //imgName = (ImageView) rootView.findViewById(R.id.imgView);
        CustomGridView customGridView = new CustomGridView(getActivity(),R.layout.row_dialgo,getDataCatagory());
        gr.setAdapter(customGridView);

        gr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       /*  Intent i = new Intent(getActivity(),ACoupleFragment.class);
                startActivity(i);*/
            }
        });


        return rootView;
    }

    private ArrayList<Item_grid> getDataCatagory() {
        loaddata picture = new loaddata(getActivity());
        lstCatagory = new ArrayList<>();
        lstCatagory = picture.getCatagory();
        return lstCatagory;

    }


}
