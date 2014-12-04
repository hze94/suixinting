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

public class LMFragment extends Fragment {

    private ArrayList<Audio> audios = new ArrayList<>();
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
        myListView = (ListView)rootView.findViewById(R.id.local_music_list);
        myListView.setAdapter(new MyBaseAdapter(getActivity(), audios));
        return rootView;
    }

}
