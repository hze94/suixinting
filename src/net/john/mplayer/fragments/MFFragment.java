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

public class MFFragment extends Fragment {

    private ArrayList<Audio> mfAudios = new ArrayList<>();
    private ListView listView;

    public MFFragment(ArrayList<Audio> audios) {
        this.mfAudios = audios;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourite_music, container, false);
        listView = (ListView)rootView.findViewById(R.id.favourite_music_list);
        listView.setAdapter(new MyBaseAdapter(getActivity(),mfAudios));
        return rootView;
    }

}
