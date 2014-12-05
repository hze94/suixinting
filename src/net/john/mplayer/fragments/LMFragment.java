package net.john.mplayer.fragments;

import android.R.integer;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;

import java.io.IOException;
import java.util.ArrayList;

public class LMFragment extends Fragment implements OnItemClickListener {

    private ArrayList<Audio> audios = new ArrayList<>();
    MediaPlayer mediaPlayer = new MediaPlayer();
    private ListView         myListView;

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
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audios.get(position).getPath().substring(4));
            mediaPlayer.prepare();
            mediaPlayer.start();
            final int p = position;
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
                
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.reset();
                    try {
                        mediaPlayer.setDataSource(audios.get(p + 1).getPath().substring(4));
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }

}
