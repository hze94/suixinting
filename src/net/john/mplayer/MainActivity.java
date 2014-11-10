package net.john.mplayer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;

import net.john.mplayer.tabs.MyTabListener;
import net.john.mplayer.viewpager.MyOnPageChangeListener;
import net.john.mplayer.viewpager.MyPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ActionBar        mActionBar;
    private ViewPager        mViewPager;
    MyPagerAdapter mTabPagerAdapter;
    List<View> mTabPagerList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpActionBar();
        setUpViewPager();
        setUpTabs();
        getOverflowMenu();
    }

    /**
     * 创建动作栏
     */
    private void setUpActionBar() {
        mActionBar = getActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

    /**
     * 创建动作栏的tab
     */
    private void setUpTabs() {
        MyTabListener mtabListener = new MyTabListener(mViewPager,mTabPagerList);
//        mActionBar = getActionBar();
        String[] tabName = { "本地音乐", "我的最爱", "最近播放" };
        for (int i = 0; i < 3; i++) {
            Tab tab = mActionBar.newTab();
            tab.setText(tabName[i]);
            tab.setTabListener(mtabListener);
            mActionBar.addTab(tab, false);
        }
    }

    private void setUpViewPager() {
        //获取ViewPager
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        
        LayoutInflater mInflater = getLayoutInflater();
        mTabPagerList.add(mInflater.inflate(R.layout.lay1, null));
        mTabPagerList.add(mInflater.inflate(R.layout.lay2, null));
        mTabPagerList.add(mInflater.inflate(R.layout.lay3, null));
        
        //初始化mAdapter
        mViewPager.setAdapter(new MyPagerAdapter(mTabPagerList));
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener(mActionBar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                // openSearch();
                return true;
            case R.id.action_settings:
                // openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // force to show overflow menu in actionbar for android 4.4 below
    private void getOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}