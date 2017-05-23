package com.evssa.bluetooth.bluetooth.evssa;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.evssa.bluetooth.bluetooth.menu.item.VistaProcesoRadiacion;

/**
 * Created by Juan Camilo on 13/04/2017.
 */

public class EvssaMensaje {
    public EvssaMensaje() {

    }

    public static void mensajeInformativo(String mensaje, View view) {
        Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).show();
    }

    public static void mensajeInformativo(String mensaje, View view, int tiempo) {
        Snackbar.make(view, mensaje, tiempo).show();
    }

    public static void alertaDialogo(FragmentManager fragmentManager, String enCabezadoDialogo, String aplicacion) {
        EvssaDialogo evssaDialogo = new EvssaDialogo();
        Bundle arg = new Bundle();
        arg.putString(GeneralEnum.ENCABEZADODIALOGO.getNombre(), enCabezadoDialogo);
        arg.putString(GeneralEnum.CUERPODIALOGO.getNombre(),"");
        arg.putString(GeneralEnum.APLICACION.getNombre(),aplicacion);
        evssaDialogo.setArguments(arg);
        evssaDialogo.show(fragmentManager, "");
    }


}