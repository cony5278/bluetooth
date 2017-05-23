package com.evssa.bluetooth.bluetooth.menu;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.evssa.bluetooth.bluetooth.R;
import com.evssa.bluetooth.bluetooth.menu.VistaAdaptadorPagina;
import com.evssa.bluetooth.bluetooth.menu.item.VistaProcesoRadiacion;
import com.evssa.bluetooth.bluetooth.menu.item.VistaReporte;

public class MainEvssa extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager vistaPaginado;
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private VistaAdaptadorPagina vistaAdaptadorPagina;
    private String[] pageTitle = {"Fragment 1", "Fragment 2", "Fragment 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vistaPaginado = (ViewPager)findViewById(R.id.view_pager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        setSupportActionBar(toolbar);

        //create default navigation drawer toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        for (int i = 0; i < 3; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(pageTitle[i]));
        }

        //set gravity for tab bar
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //handling navigation view item event
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

        vistaPaginado.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        vistaAdaptadorPagina=new VistaAdaptadorPagina(getSupportFragmentManager());
        Bundle bundle = getIntent().getExtras();
        VistaProcesoRadiacion vistaProcesoRadiacion=new VistaProcesoRadiacion();
        VistaReporte vistaReporte=new VistaReporte();
        VistaReporte vistaReporte2=new VistaReporte();
        vistaProcesoRadiacion.setArguments(bundle);
        vistaAdaptadorPagina.addFragmentMenuItem(vistaProcesoRadiacion,"Radiacion");
        vistaAdaptadorPagina.addFragmentMenuItem(vistaReporte,"Reportes");
        vistaAdaptadorPagina.addFragmentMenuItem(vistaReporte2,"Acerca de");
        vistaPaginado.setAdapter(vistaAdaptadorPagina);
        tabLayout.setupWithViewPager(vistaPaginado);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
            switch (id){
                case R.id.calculo:
                    vistaPaginado.setCurrentItem(0);
                    break;
                case R.id.reporte:
                    vistaPaginado.setCurrentItem(1);
                    break;
                case R.id.acerca:
                    vistaPaginado.setCurrentItem(2);
                    break;
                case R.id.close:
                    finish();
                    break;
            }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

