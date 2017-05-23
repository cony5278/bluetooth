package com.evssa.bluetooth.bluetooth.menu.item;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.evssa.bluetooth.bluetooth.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VistaReporte extends Fragment {


    public VistaReporte() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.vista_reporte, container, false);
        return view;
    }

}
