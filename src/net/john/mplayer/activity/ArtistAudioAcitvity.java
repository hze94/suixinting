package net.john.mplayer.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import net.john.mplayer.R;
import net.john.mplayer.R.id;
import net.john.mplayer.R.layout;
import net.john.mplayer.R.menu;
import net.john.mplayer.audio.Audio;
import net.john.mplayer.utils.AudioParser;

import java.util.ArrayList;
import java.util.HashMap;

public class ArtistAudioAcitvity extends Activity implements OnItemClickListener {

    private ActionBar                         mActionBar;
    private ListView                          listView;
    private String                            artistName;
    private HashMap<String, ArrayList<Audio>> artistMap;
    private ArrayList<Audio>                  artistAudios;
    private ImageButton         playButton, previousButton, nextButton, heartButton;

    public ArtistAudioAcitvity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar();
        setContentView(R.layout.activity_artist_audio);

        dataInit();
        
//         TextView textView = (TextView)findViewById(R.id.test);
//         textView.setText("artistName: " + artistName + "songs: " +
//         artistAudios.size());
        listView = (ListView) findViewById(R.id.artist_audio_list);
        listView.setAdapter(new ArtistAudioAdapter(this, artistAudios));
        listView.setOnItemClickListener(this);
    }

    private void dataInit() {
        Bundle bundle = getIntent().getExtras();
        artistName = bundle.getString("currentArtist");
        System.out.println("artistname: " + artistName);

        @SuppressWarnings("unchecked") ArrayList<Audio> audios = (ArrayList<Audio>) bundle.getSerializable("allAudios");

        AudioParser audioParser = new AudioParser(audios);
        artistMap = audioParser.parseArtist();

        artistAudios = artistMap.get(artistName);
        System.out.println(artistAudios.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.artist_audio_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 处理ActionBar的条目按键。actionbar会自动处理Home/Up按钮上的事件，
        // 你只要在AndroidManifest.xml指定一个父Activity即可。
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpActionBar() {
        mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }
    

}
