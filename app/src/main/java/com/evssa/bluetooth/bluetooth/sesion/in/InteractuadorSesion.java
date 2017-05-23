package com.evssa.bluetooth.bluetooth.sesion.in;


import com.evssa.bluetooth.bluetooth.lib.EventBus;
import com.evssa.bluetooth.bluetooth.lib.GreenRobotEventBus;

/**
 * Created by Juan Camilo on 21/05/2017.
 */

public class InteractuadorSesion implements  ImplementInteractuadorSesion{

    private void obtenerUsuario(String nombreUsuario, String contrasena){
        //verificar el usuario
        postEvento(2);
    }
    private void registrarUsuario(String nombreUsuario, String correo, String contrasena){
        //agregar al usuario
        postEvento(1);
    }
    private void postEvento(int confirmacion) {
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(confirmacion);
    }
    @Override
    public void verificarUsuario(String nombreUsuario, String contrasena) {
            obtenerUsuario(nombreUsuario,contrasena);
    }

    @Override
    public void addUsuario(String nombreUsuario, String correo, String contrasena) {
        registrarUsuario(nombreUsuario,correo,contrasena);
    }

}
