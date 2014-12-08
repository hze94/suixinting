package net.john.mplayer.viewpager;

import android.app.ActionBar;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

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
//        Animation animation = new TranslateAnimation(mActionBar.getSelectedNavigationIndex(), position, 0, 0);
//        animation.setFillAfter(true);
//        animation.setDuration(300);
        
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
