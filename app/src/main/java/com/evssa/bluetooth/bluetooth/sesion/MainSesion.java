package com.evssa.bluetooth.bluetooth.sesion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.evssa.bluetooth.bluetooth.R;
import com.evssa.bluetooth.bluetooth.sesion.pr.PresentadorSesion;
import com.evssa.bluetooth.bluetooth.sesion.ui.VistaImplementSesion;

import butterknife.BindView;
import butterknife.OnClick;

public class MainSesion extends AppCompatActivity implements VistaImplementSesion {
    @BindView(R.id.btnIniciar)
    private  Button btnIniciar;
    private PresentadorSesion presentadorSesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sesion);
        presentadorSesion=new PresentadorSesion();
    }
    @OnClick(R.id.btnIniciar)
    public void clickIniciarSesion() {

    }

    @Override
    public void mostrarProgreso() {

    }

    @Override
    public void ocultarProgreso() {

    }

    @Override
    public void registroUsuario() {

    }

    @Override
    public void sesionUsuario() {

    }
}
