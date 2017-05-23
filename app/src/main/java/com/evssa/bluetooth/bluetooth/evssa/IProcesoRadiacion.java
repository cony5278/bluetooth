package com.evssa.bluetooth.bluetooth.evssa;

import android.content.Context;
import android.view.View;

/**
 * Created by Juan Camilo on 04/05/2017.
 */

public interface IProcesoRadiacion {
    public void destruiProgreso();
    public void progresoDialogo();
    public void visibleObjetos(int visibleOcultar, int ocultarVisible) ;
    public void encenderBluetooth() ;
    public void bloquearDescbloquear(int activar);
    public View getView();
    public void addTestoHumedad(String dato) ;
    public void finalizar();

}
