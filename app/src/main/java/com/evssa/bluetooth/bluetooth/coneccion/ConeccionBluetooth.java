package com.evssa.bluetooth.bluetooth.coneccion;

import android.os.AsyncTask;
import android.view.View;

import com.evssa.bluetooth.bluetooth.evssa.EvssaFunciones;
import com.evssa.bluetooth.bluetooth.evssa.EvssaMensaje;
import com.evssa.bluetooth.bluetooth.evssa.GeneralEnum;
import com.evssa.bluetooth.bluetooth.evssa.IProcesoRadiacion;


import java.io.IOException;

/**
 * Created by Juan Camilo on 13/04/2017.
 */

public class ConeccionBluetooth extends AsyncTask<Void,String,Void>  {
    private boolean connectSuccess;
    private boolean isBtConnected;
    private Bluetooth bluetooth;
    private IProcesoRadiacion procesoRadiacion;
    private boolean errorConectado;
    public ConeccionBluetooth(IProcesoRadiacion procesoRadiacion, Bluetooth bluetooth){
        connectSuccess=true;
        isBtConnected=false;
        errorConectado=true;
        this.procesoRadiacion=procesoRadiacion;
        this.bluetooth=bluetooth;

    }
    @Override
    protected void onPreExecute()
    {
        procesoRadiacion.progresoDialogo();
    }
    @Override
    protected Void doInBackground(Void... devices) {
            if (EvssaFunciones.ObjetoVacio(bluetooth.getBtSocket()) || !isBtConnected)
            {
                try {
                    bluetooth.conectar();
                    procesoRadiacion.destruiProgreso();
                    publishProgress(GeneralEnum.BOTON.getNombre());
                } catch (IOException e) {
                    connectSuccess = false;
                    e.printStackTrace();
                }
            }
            try {
                while(!EvssaFunciones.ObjetoVacio(bluetooth.getBtSocket())){
                    publishProgress(bluetooth.recibirDato());
                }
            }catch (IOException e){
                errorConectado=false;
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        if(!values[0].toString().equals(GeneralEnum.BOTON.getNombre())) {
            procesoRadiacion.addTestoHumedad(values[0].toString());
        }else if (EvssaFunciones.ObjetoVacio(bluetooth.getBtSocket())){
            EvssaMensaje.mensajeInformativo("NO se puedo establecer la conexion con el bluetooth "+GeneralEnum.BLUETOOTH.getNombre(),procesoRadiacion.getView());
            procesoRadiacion.bloquearDescbloquear(View.GONE);
        }

    }

    @Override
    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
    if(errorConectado) {
        if (!connectSuccess) {
            EvssaMensaje.mensajeInformativo(GeneralEnum.CONEXIONFALLIDA.getNombre(), procesoRadiacion.getView());
            procesoRadiacion.finalizar();
        } else {
            EvssaMensaje.mensajeInformativo(GeneralEnum.CONECTADO.getNombre(), procesoRadiacion.getView());
            isBtConnected = true;
        }
    }else{
        bluetooth.setBtSocket(null);
        bluetooth=null;
        procesoRadiacion.bloquearDescbloquear(View.VISIBLE);
        EvssaMensaje.mensajeInformativo(GeneralEnum.DESCONECTADOBLUETOOTH.getNombre(),procesoRadiacion.getView());
    }
        procesoRadiacion.destruiProgreso();
    }
}
