package com.evssa.bluetooth.bluetooth.menu.item;


import android.Manifest;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.evssa.bluetooth.bluetooth.R;
import com.evssa.bluetooth.bluetooth.coneccion.Bluetooth;
import com.evssa.bluetooth.bluetooth.coneccion.ConeccionBluetooth;
import com.evssa.bluetooth.bluetooth.coneccion.EstadoBluetooth;
import com.evssa.bluetooth.bluetooth.evssa.EvssaFunciones;
import com.evssa.bluetooth.bluetooth.evssa.EvssaMensaje;
import com.evssa.bluetooth.bluetooth.evssa.GeneralEnum;
import com.evssa.bluetooth.bluetooth.evssa.IProcesoRadiacion;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.github.clans.fab.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class VistaProcesoRadiacion extends Fragment implements IProcesoRadiacion, OnMapReadyCallback, LocationListener {

    @BindView(R.id.menuFlotante)
    FloatingActionMenu menuFlotante;
    @BindView(R.id.itemBtnMenuUbicacion)
    FloatingActionButton btnMenuUbicacion;
    @BindView(R.id.itemBtnMenuConexion)
    FloatingActionButton btnMenuConexion;
    @BindView(R.id.itemBtnMenuCalcular)
    FloatingActionButton btnMenuCalcular;
    @BindView(R.id.txtHumedad)
    TextView testoHumedad;
    @BindView(R.id.checkDatos)
    CheckBox checkDatos;
    @BindView(R.id.textInputHumedad)
    TextInputLayout textInputHumedad;
    @BindView(R.id.editTxtHumedad)
    EditText editTextHuemdad;
    private GoogleMap googleMap;
    private MapView mapView;
    private ProgressDialog progress;
    private EstadoBluetooth estadoBluetooth;
    private ConeccionBluetooth coneccionBluetooth;
    private Bluetooth bluetooth;
    private View view;
    private Location location;
    private LocationManager locationManager;


    public VistaProcesoRadiacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.vista_proceso_radiacion, container, false);
        ButterKnife.bind(this, view);

        visibleObjetos(View.VISIBLE, View.GONE);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        abrirAplicacionGPSWIFI();

        Bundle bundle = getArguments();
        if (!EvssaFunciones.ObjetoVacio(bundle)) {
            estadoBluetooth = new EstadoBluetooth();
            bluetooth = new Bluetooth(estadoBluetooth, bundle.get(GeneralEnum.DIRECCION.getNombre()).toString());
            coneccionBluetooth = new ConeccionBluetooth(this, bluetooth);
            coneccionBluetooth.execute();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap();

    }
    private void initMap(){
        mapView = (MapView) view.findViewById(R.id.map);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    /**
     * metodo que se llama para que aparesca un dialogo con un refresh
     */
    @Override
    public void progresoDialogo() {
        progress = ProgressDialog.show(getActivity(), GeneralEnum.CONECTANDO.getNombre(), GeneralEnum.ESPERAR.getNombre());
    }

    /**
     * metodo que se llama para destruir el progreso     *
     */
    @Override
    public void destruiProgreso() {
        progress.dismiss();
    }

    @Override
    public void visibleObjetos(int visibleOcultar, int ocultarVisible) {
        testoHumedad.setVisibility(visibleOcultar);
        textInputHumedad.setVisibility(ocultarVisible);
    }

    @Override
    public void encenderBluetooth() {
        Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(turnBTon, 1);
    }

    @Override
    public void bloquearDescbloquear(int activar) {
        //    btnBluetooth.setVisibility(activar);
    }

    @Override
    public void addTestoHumedad(String dato) {
        testoHumedad.setText(GeneralEnum.HUMEDAD.getNombre() + dato);
    }

    @Override
    public void finalizar() {
        getActivity().finish();
    }

    @OnCheckedChanged(R.id.checkDatos)
    public void checkboxToggled(boolean isChecked) {
        if (isChecked) {
            visibleObjetos(View.GONE, View.VISIBLE);
        } else {
            visibleObjetos(View.VISIBLE, View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        this.googleMap = googleMap;

        addLocalizacion();
        if (!EvssaFunciones.ObjetoVacio(location)) {
            this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            this.googleMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("").snippet(""));
            CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(location.getLatitude(), location.getLongitude())).zoom(16).bearing(0).tilt(90).build();
            this.googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }

    private void addLocalizacion() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                return;
            }
        } else {

            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
        }
    }

    private void abrirAplicacionGPSWIFI() {
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                !locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            EvssaMensaje.alertaDialogo(getFragmentManager(), "Activar la ubicación por WIFI o GPS", Settings.ACTION_APPLICATION_SETTINGS);
        }
    }
    private void actualizarLocalizacion(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, getInstance());
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, getInstance());
                }
                return;
            }
        } else {

            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, getInstance());
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, getInstance());
            }
        }
    }
    @OnClick(R.id.itemBtnMenuConexion)
    public void clickConexion() {
          encenderBluetooth();

    }
    private VistaProcesoRadiacion getInstance(){
        return this;
    }
    @OnClick(R.id.itemBtnMenuUbicacion)
    public void clickUbicacion() {
        actualizarLocalizacion();
        initMap();
    }
    @OnClick(R.id.itemBtnMenuCalcular)
    public void clickCalcular() {
        //llamar al servicio de la formula
    }

    @Override
    public void onLocationChanged(Location location) {
        //this.location=location;
       // EvssaMensaje.mensajeInformativo(location.getLatitude()+" : "+location.getLongitude(),getView(),Snackbar.LENGTH_LONG);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
