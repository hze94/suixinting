package net.john.mplayer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.widget.TextView;

import com.example.musicplayer.R;

import net.john.mplayer.fragment.Fragment1;
import net.john.mplayer.fragment.Fragment2;
import net.john.mplayer.fragment.Fragment3;

import java.lang.reflect.Field;

public class MainActivity extends FragmentActivity implements TabListener {

    private ActionBar        actionBar;
//    private TextView         text;
    private ViewPager        mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;

    private Fragment1        mFragment1      = new Fragment1();
    private Fragment2        mFragment2      = new Fragment2();
    private Fragment3        mFragment3      = new Fragment3();

    private static final int TAB_INDEX_COUNT = 3;

    private static final int TAB_INDEX_ONE   = 0;
    private static final int TAB_INDEX_TWO   = 1;
    private static final int TAB_INDEX_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpActionBar();
//        setUpViewPager();
        setUpTabs();

//        text = (TextView) findViewById(R.id.text);
        getOverflowMenu();
    }

    /**
     * 创建动作栏
     */
    private void setUpActionBar() {
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
    }

    /**
     * 创建动作栏的tab
     */
    private void setUpTabs() {
        actionBar = getActionBar();
        Tab[] tabs = new Tab[3];
        String[] tabName = { "本地音乐", "我的最爱", "最近播放" };
        for (int i = 0; i < tabs.length; i++) {
            tabs[i] = actionBar.newTab();
            tabs[i].setText(tabName[i]);
            tabs[i].setTabListener(this);
            actionBar.addTab(tabs[i], false);
        }
    }

    private void setUpViewPager() {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar = getActionBar();
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        // TODO
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        // TODO
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        // TODO
                        break;
                    default:
                        // TODO
                        break;
                }
            }
        });
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

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case TAB_INDEX_ONE:
                    return mFragment1;
                case TAB_INDEX_TWO:
                    return mFragment2;
                case TAB_INDEX_THREE:
                    return mFragment3;
            }
            throw new IllegalStateException("No fragment at position " + position);
        }

        @Override
        public int getCount() {
            return TAB_INDEX_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String tabLabel = null;
            switch (position) {
                case TAB_INDEX_ONE:
                    tabLabel = getString(R.string.tab_local_music);
                    break;
                case TAB_INDEX_TWO:
                    tabLabel = getString(R.string.tab_my_favorite);
                    break;
                case TAB_INDEX_THREE:
                    tabLabel = getString(R.string.tab_recently_play);
                    break;
            }
            return tabLabel;
        }
    }
}
