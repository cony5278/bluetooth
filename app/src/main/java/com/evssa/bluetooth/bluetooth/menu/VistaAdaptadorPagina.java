package com.evssa.bluetooth.bluetooth.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Juan Camilo on 30/04/2017.
 */

public class VistaAdaptadorPagina extends FragmentPagerAdapter {
    ArrayList<Fragment> listFragment;
    ArrayList<String>listItemMenu;
    public VistaAdaptadorPagina(FragmentManager pager){
        super(pager);
        init();
    }
    public void init(){
        listFragment=new ArrayList<>();
        listItemMenu=new ArrayList<>();
    }
    public void addFragmentMenuItem(Fragment fragment,String title){
        listFragment.add(fragment);
        listItemMenu.add(title);
    }
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listItemMenu.get(position);
    }
}
