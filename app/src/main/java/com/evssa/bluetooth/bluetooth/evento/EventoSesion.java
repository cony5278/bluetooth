package com.evssa.bluetooth.bluetooth.evento;

/**
 * Created by Juan Camilo on 23/05/2017.
 */

public enum EventoSesion {
    ERRORSESION(1,""),
    CONFIRMACIONSESION(2,""),
    ERRORREGISTRO(3,""),
    CONFRIMACIONREGISTRO(4,"");

    private String error;
    private int confirmacion;
    EventoSesion(int confirmacion, String error) {
        setConfirmacion(confirmacion);
        setError(error);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }
}
