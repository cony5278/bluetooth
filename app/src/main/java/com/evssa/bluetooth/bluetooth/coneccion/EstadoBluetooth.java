package com.evssa.bluetooth.bluetooth.coneccion;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.evssa.bluetooth.bluetooth.evssa.EvssaFunciones;
import com.evssa.bluetooth.bluetooth.evssa.EvssaMensaje;
import com.evssa.bluetooth.bluetooth.evssa.GeneralEnum;
import com.evssa.bluetooth.bluetooth.evssa.IConeccionBluetooth;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Juan Camilo on 13/04/2017.
 */

public class EstadoBluetooth  {
    private BluetoothAdapter miBluetoothAdapter;

    private Set<BluetoothDevice> dispVinculados;
    private IConeccionBluetooth iConeccionBluetooth;

    public EstadoBluetooth(IConeccionBluetooth iConeccionBluetooth){
        this.iConeccionBluetooth=iConeccionBluetooth;
        miBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        inicializar();
    }

    public EstadoBluetooth(){
        miBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
   }
    public void inicializar(){
        disponible();
        encenderBluetooth();
    }
    public void disponible(){
        if(miBluetoothAdapter == null)
        {
            //Mostramos un mensaje, indicando al usuario que no tiene conexión bluetooth disponible
            EvssaMensaje.mensajeInformativo(GeneralEnum.NOBLUETOOTH.getNombre(),iConeccionBluetooth.getView());

        }

    }
    public void encenderBluetooth(){
        if(!miBluetoothAdapter.isEnabled())
        {
            //Preguntamos al usuario si desea encender el bluetooth
            this.iConeccionBluetooth.encenderBluetooth();
        }else {
            emparejado();
        }
    }


    public String listaDispositivosVinculados() {
        dispVinculados = miBluetoothAdapter.getBondedDevices();
        ArrayList<String> list = new ArrayList<>();

        if (dispVinculados.size() > 0) {
            for (BluetoothDevice bt : dispVinculados) {
                if(GeneralEnum.BLUETOOTH.getNombre().equals(bt.getName()))
                     return bt.getAddress(); //Obtenemos los nombres y direcciones MAC de los disp. vinculados
            }


        } else {
            EvssaMensaje.mensajeInformativo(GeneralEnum.NOVICULADOS.getNombre(),iConeccionBluetooth.getView());
        }
        return null;
    }

    public void emparejado(){
        BluetoothDevice dispositivo=null;
        for (BluetoothDevice device : miBluetoothAdapter.getBondedDevices()) {
            if (device.getName().equalsIgnoreCase(GeneralEnum.BLUETOOTH.getNombre())) {
                dispositivo = device;
            }
        }
        if(EvssaFunciones.ObjetoVacio(dispositivo)){
            EvssaMensaje.mensajeInformativo(GeneralEnum.NOEMPAREJADO.getNombre(),iConeccionBluetooth.getView());
        }else{
            EvssaMensaje.mensajeInformativo(GeneralEnum.SIEMPAREJADO.getNombre(),iConeccionBluetooth.getView());

        }
    }


    public BluetoothAdapter getMiBluetoothAdapter() {
        return miBluetoothAdapter;
    }

    public void setMiBluetoothAdapter(BluetoothAdapter miBluetoothAdapter) {
        this.miBluetoothAdapter = miBluetoothAdapter;
    }


}
