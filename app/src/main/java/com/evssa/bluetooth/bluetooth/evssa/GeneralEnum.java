package com.evssa.bluetooth.bluetooth.evssa;

/**
 * Created by Juan Camilo on 13/04/2017.
 */

public enum GeneralEnum {
    DIRECCION("direccion"),
    BLUETOOTH("HABLADOMO"),
    HUMEDAD("Humedad: "),
    CONECTADO("Conectado"),
    NOEMPAREJADO("El dispositivo no se encuentra emparejado"),
    SIEMPAREJADO("El dispositivo ya se encuentra emparejado"),
    NOBLUETOOTH("Bluetooth no disponible"),
    NOVICULADOS("No se han encontrado dispositivos vinculados"),
    PORCENTAJEHUMEDAD("Digite el porcentaje de humedad"),
    NOCONEXION("Establesca la conección con el dispositivo"),
    ENVIARDATO("1"),
    ESPERAR("Por favor espere!!!"),
    CONECTANDO("Conectando..."),
    DESCONECTADOBLUETOOTH("El dispositivo bluetooth ha sido desconectado"),
    CONEXIONFALLIDA("Conexión Fallida"),
    ENCABEZADODIALOGO("enCabezadoDialogo"),
    CUERPODIALOGO("cuerpoDialogo"),
    BOTON("BOTON"),
    APLICACION("APLICACION");
    private String nombre;
    GeneralEnum(String nombre) {
    setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
