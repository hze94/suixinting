package net.john.mplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import net.john.mplayer.R;
import net.john.mplayer.activity.ArtistAudioAdapter;
import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

public class ArtistAudioFragment extends Fragment implements OnItemClickListener {

    private ArrayList<Audio> artistAudios;
    private ListView         listView;

    public ArtistAudioFragment() {
        
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist_audio, container, false);
        listView = (ListView) rootView.findViewById(R.id.artist_audio_list);
        listView.setAdapter(new ArtistAudioAdapter(getActivity(),artistAudios));
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        
    }

}
