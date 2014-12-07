package net.john.mplayer.tabs;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MyTabListener implements TabListener {

    private ViewPager  mViewPager;
    private List<Tab> mTabPagerList = new ArrayList<Tab>();

    public MyTabListener(ViewPager mViewPager, List<Tab> mTabPagerList) {
        this.mViewPager = mViewPager;
        this.mTabPagerList = mTabPagerList;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {

        for (int i = 0; i < mTabPagerList.size(); i++) {
            if (i == tab.getPosition()) {
                mViewPager.setCurrentItem(i);
            }
        }

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

}
