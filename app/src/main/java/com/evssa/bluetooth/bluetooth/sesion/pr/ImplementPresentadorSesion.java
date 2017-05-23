package com.evssa.bluetooth.bluetooth.sesion.pr;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Juan Camilo on 21/05/2017.
 */

public interface ImplementPresentadorSesion {
    void validarSesion(String nombreUsuario,String contrasena);
    void registrarUsuario(String nombreUsuario,String correo,String contrasena);
    void onCreate();
    void onDestroy();

    @Subscribe
    void onEventMainThread(int confirmacion);
}
