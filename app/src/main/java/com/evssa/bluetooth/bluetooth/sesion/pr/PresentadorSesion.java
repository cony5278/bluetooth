package com.evssa.bluetooth.bluetooth.sesion.pr;

import com.evssa.bluetooth.bluetooth.evento.EventoSesion;
import com.evssa.bluetooth.bluetooth.lib.EventBus;
import com.evssa.bluetooth.bluetooth.lib.GreenRobotEventBus;
import com.evssa.bluetooth.bluetooth.sesion.in.ImplementInteractuadorSesion;
import com.evssa.bluetooth.bluetooth.sesion.in.InteractuadorSesion;
import com.evssa.bluetooth.bluetooth.sesion.ui.VistaImplementSesion;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Juan Camilo on 21/05/2017.
 */

public class PresentadorSesion implements ImplementPresentadorSesion {
    private ImplementInteractuadorSesion interactuadorSesion;
    private EventBus eventBus;
    private VistaImplementSesion vistaSesion;
    public PresentadorSesion(VistaImplementSesion vistaImplementSesion){
        interactuadorSesion=new InteractuadorSesion();
        eventBus = GreenRobotEventBus.getInstance();
        vistaSesion=vistaImplementSesion;
    }

    @Override
    public void validarSesion(String nombreUsuario, String contrasena) {
            interactuadorSesion.verificarUsuario(nombreUsuario,contrasena);
    }

    @Override
    public void registrarUsuario(String nombreUsuario, String correo, String contrasena) {
        interactuadorSesion.addUsuario(nombreUsuario,correo,contrasena);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        vistaSesion=null;
        eventBus.unregister(this);
    }
    @Override
    @Subscribe
    public void onEventMainThread(int confirmacion) {
        EventoSesion eventoSesion = EventoSesion.values()[confirmacion];
        switch (eventoSesion) {
            case CONFIRMACIONSESION:

                break;
            case ERRORSESION:

                break;
        }
    }
}
