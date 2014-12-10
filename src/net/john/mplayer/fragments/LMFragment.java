package net.john.mplayer.fragments;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;

import java.io.IOException;
import java.util.ArrayList;

public class LMFragment extends Fragment implements OnItemClickListener {

    private ArrayList<Audio> audios      = new ArrayList<>();
    private MediaPlayer      mediaPlayer = new MediaPlayer();
    private ListView         myListView;
    private Audio            nowAudio;
    private int              nowPostion;

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public LMFragment(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_local_music, container, false);
        myListView = (ListView) rootView.findViewById(R.id.local_music_list);
        myListView.setAdapter(new MyBaseAdapter(getActivity(), audios));
        myListView.setOnItemClickListener(this);

        return rootView;
    }

    /**
     * 点击播放对应item位置上的音乐
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            
            /*
             * 标识当前item位置和当前播放的audio
             */
            nowPostion = position;
            nowAudio = audios.get(position);

            mediaPlayer.reset();
            mediaPlayer.setDataSource(nowAudio.getPath().substring(4));
            mediaPlayer.prepare();
            mediaPlayer.start();
            final int p = position;
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.reset();
                    try {
                        // 此处可能有bug，可能需取模
                        mediaPlayer.setDataSource(audios.get(p + 1).getPath().substring(4));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            setPlayColumnStatus();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {}
    }

    // 以下部分对外暴露了很多方法，可能不安全，以后再优化

    /**
     * 更新播放栏信息
     */
    public void setPlayColumnStatus() {
        ImageButton iButton = (ImageButton) getActivity().findViewById(R.id.playButton);
        ImageButton heartButton = (ImageButton) getActivity().findViewById(R.id.heartButton);
        TextView titleTextView = (TextView) getActivity().findViewById(R.id.title);
        TextView artisTextView = (TextView) getActivity().findViewById(R.id.artist);

        iButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_pause_over_video));
        titleTextView.setText(audios.get(nowPostion).getTitle());
        artisTextView.setText(audios.get(nowPostion).getArtist());
        if(nowAudio.getIsFavourite()){
            heartButton.setImageDrawable(getResources().getDrawable(R.drawable.icon_favorite_on));
        }else {
            heartButton.setImageDrawable(getResources().getDrawable(R.drawable.icon_favorite));
        }
    }

    public void pauseAudio() {
        mediaPlayer.pause();
    }

    public void stopAudio() {
        mediaPlayer.stop();
    }

    public void startAudio() {
        mediaPlayer.start();
    }

    public void resetAudio() {
        mediaPlayer.reset();
    }

    public void prepareAudio() {
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAudioSource(String path) {
        try {
            mediaPlayer.setDataSource(path);
        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    public int getNowPostion() {
        return nowPostion;
    }

    public void setNowPostion(int newPostion) {
        this.nowPostion = newPostion;
    }

    public Audio getNowAudio() {
        return nowAudio;
    }
    
    public void setFavouriteStatus(boolean isFavourite){
        audios.get(nowPostion).setIsFavourite(isFavourite);
    }
    
}
