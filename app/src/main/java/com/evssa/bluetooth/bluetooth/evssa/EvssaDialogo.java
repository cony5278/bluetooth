package com.evssa.bluetooth.bluetooth.evssa;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.DialogFragment;

import com.evssa.bluetooth.bluetooth.R;


/**
 * Created by Juan Camilo on 01/05/2017.
 */


public class EvssaDialogo  extends DialogFragment {
    final CharSequence[] abrir = {"WIFI","GPS"};
    boolean estadoCheck=false;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                // Set Dialog Icon
                .setIcon(R.mipmap.ic_launcher)
                // Set Dialog Title
                .setTitle(getArguments().get(GeneralEnum.ENCABEZADODIALOGO.getNombre()).toString())
                // Set Dialog Message
                  .setSingleChoiceItems(abrir, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                estadoCheck = true;
                                break;
                            case 1:
                                estadoCheck = false;
                                break;
                        }
                    }
                })

                // Positive button
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(estadoCheck){
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }else{
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }
                })
                // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,	int which) {
                        dialog.cancel();
                    }
                }).create();

    }



}
