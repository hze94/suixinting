package net.john.mplayer.viewpager;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentList) {
        super(fragmentManager);
        this.list = fragmentList;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return list.get(arg0);
    }

}