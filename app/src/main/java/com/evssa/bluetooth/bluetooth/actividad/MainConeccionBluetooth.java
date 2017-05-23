package com.evssa.bluetooth.bluetooth.actividad;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.evssa.bluetooth.bluetooth.R;
import com.evssa.bluetooth.bluetooth.coneccion.EstadoBluetooth;
import com.evssa.bluetooth.bluetooth.evssa.IConeccionBluetooth;

import butterknife.BindView;

public class MainConeccionBluetooth extends AppCompatActivity implements IConeccionBluetooth {
    private EstadoBluetooth estadoBluetooth;
    @BindView(R.id.btnConexionBLuetooth) Button btnConeecionBluetooth;
    @BindView(R.id.imgBluetoothConeecion) ImageView imgBluetoothConeecion;
    @BindView(R.id.imgBluetoothConeecionNotVinculado) ImageView imgBluetoothConeecionNotVinculado;
    private String direccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        estadoBluetooth =new EstadoBluetooth(this);
        setContentView(R.layout.activity_main_coneccion_bluetooth);
    }

    @Override
    public View getView() {
        return null;
    }
    @Override
    public void encenderBluetooth() {
        Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnBTon,1);
    }
}
