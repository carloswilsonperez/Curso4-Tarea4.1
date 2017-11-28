package com.example.administrador.curso4_tarea4_1.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by administrador on 17/05/17.
 */
// Clase para manjera los Fragments en el MainActivity
public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;

    // Constructor
    public PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
