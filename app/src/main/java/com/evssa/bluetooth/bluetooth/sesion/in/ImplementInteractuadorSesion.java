package com.evssa.bluetooth.bluetooth.sesion.in;

/**
 * Created by Juan Camilo on 23/05/2017.
 */

public interface ImplementInteractuadorSesion {
     void verificarUsuario(String nombreUsuario, String contrasena);
     void addUsuario(String nombreUsuario, String correo, String contrasena);
}
