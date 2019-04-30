package com.edgar.twrpg;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();

    public TabPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return (fragments == null ? 0 : fragments.size());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String pageTitle = "NONE";
        if (fragments.get(position) instanceof EquipmentsFragment) {
            pageTitle = "ITEMS";
        } else if (fragments.get(position) instanceof UnitsFragment) {
            pageTitle = "UNITS";
        }
        return pageTitle;
    }
}
