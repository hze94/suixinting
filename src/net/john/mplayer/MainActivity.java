package net.john.mplayer;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.ImageButton;
import android.widget.TextView;

import net.john.mplayer.audio.Audio;
import net.john.mplayer.audio.AudioProvider;
import net.john.mplayer.fragments.LMFragment;
import net.john.mplayer.fragments.LMFragment.OnAudioItemClickListener;
import net.john.mplayer.fragments.MFFragment;
import net.john.mplayer.fragments.RPFragment;
import net.john.mplayer.tabs.MyTabListener;
import net.john.mplayer.viewpager.MyFragmentPagerAdapter;
import net.john.mplayer.viewpager.MyOnPageChangeListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnAudioItemClickListener{

    private ActionBar           mActionBar;
    private ViewPager           mViewPager;
    private List<View>          mTabPagerList = new ArrayList<View>();
    private LMFragment          lmFragment    = null;
    private RPFragment          rpFragment    = null;
    private MFFragment          mfFragment    = null;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<Audio>    audios;

    private ImageButton         playButton, previousButton, nextButton;
    private TextView            titleTextView, artistTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudioInit();
        setUpActionBar();
        setUpViewPager();
        setUpTabs();
        getOverflowMenu();

        setButtonListeners();
    }

    private void AudioInit() {
        AudioProvider audioProvider = new AudioProvider(this);
        audios = audioProvider.getList();
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
        MyTabListener mtabListener = new MyTabListener(mViewPager, mTabPagerList);
        // mActionBar = getActionBar();
        String[] tabName = { "本地音乐", "我的最爱", "最近播放" };
        for (int i = 0; i < 3; i++) {
            Tab tab = mActionBar.newTab();
            tab.setText(tabName[i]);
            tab.setTabListener(mtabListener);
            mActionBar.addTab(tab, false);
        }
    }

    private void setUpViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        lmFragment = new LMFragment(audios);
        rpFragment = new RPFragment(audios);
        mfFragment = new MFFragment(audios);
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(lmFragment);
        fragmentList.add(mfFragment);
        fragmentList.add(rpFragment);
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
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
                finish();
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

    private void setButtonListeners() {
        playButton = (ImageButton) findViewById(R.id.playButton);
        previousButton = (ImageButton) findViewById(R.id.previousButton);
        nextButton = (ImageButton) findViewById(R.id.nextButton);
        
        playButton.setOnClickListener(new MyClickListener());
        
    }
    
    class MyClickListener implements OnClickListener{

        private boolean isPlaying = false;
        public MyClickListener() {}
        
        @Override
        public void onClick(View v) {
            if (!isPlaying) {
                playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_pause_over_video));
                isPlaying = true;
            } else {
                playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_play_over_video));
                isPlaying = false;
            }
        }
        
    }

    @Override
    public void onAudioItemClick() {
        playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_pause_over_video));
    }

}
