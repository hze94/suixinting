package net.john.mplayer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

public class ArtistFragment extends Fragment {

    private ArrayList<Audio> audios = new ArrayList<>();
    private ListView         listView;

    public ArtistFragment(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    /**
     * 为这个ListFragment添加适配器来填充列表项
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);
        listView = (ListView) rootView.findViewById(R.id.artist_list);
        listView.setAdapter(new MyArtistAdapter(getActivity(),audios));
        return rootView;
    }

}
