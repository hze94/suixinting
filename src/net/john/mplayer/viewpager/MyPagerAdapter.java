package net.john.mplayer.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    public List<View> mListViews;

    public MyPagerAdapter(List<View> tlist) {
        this.mListViews = tlist;
    }

    @Override
    public int getCount() {
        return mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mListViews.get(position));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(mListViews.get(position), 0);
        return mListViews.get(position);
    }

}
