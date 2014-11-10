package net.john.mplayer.viewpager;

import android.app.ActionBar;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MyOnPageChangeListener implements OnPageChangeListener {

    private ActionBar mActionBar;
    
    public MyOnPageChangeListener(ActionBar mActionBar){
        this.mActionBar = mActionBar;
    }
    
    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mActionBar.setSelectedNavigationItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
