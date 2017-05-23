package com.evssa.bluetooth.bluetooth.coneccion;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by Juan Camilo on 04/05/2017.
 */

public class Bluetooth {
    private EstadoBluetooth estadoBluetooth;
    private String direccion;
    private BluetoothSocket btSocket;
    public static final UUID UUIDBLUETOOTH=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public Bluetooth(EstadoBluetooth estadoBluetooth,String direccion){
        this.estadoBluetooth=estadoBluetooth;
        this.direccion=direccion;
    }
    public void conectar() throws IOException {
        BluetoothDevice dispositivo = estadoBluetooth.getMiBluetoothAdapter().getRemoteDevice(direccion);//conectamos al dispositivo y chequeamos si esta disponible

        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(UUIDBLUETOOTH);
        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
        btSocket.connect();

    }
    public void enviarDato(String dato){
        try {
            btSocket.getOutputStream().write(dato.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String recibirDato() throws IOException {

        InputStream inputStream=btSocket.getInputStream();
        int bytes;
        byte[] buffer = new byte[353];
        bytes = inputStream.read(buffer);
        return new String(buffer, 0, bytes);

    }

    public EstadoBluetooth getEstadoBluetooth() {
        return estadoBluetooth;
    }

    public void setEstadoBluetooth(EstadoBluetooth estadoBluetooth) {
        this.estadoBluetooth = estadoBluetooth;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }

    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }
}
