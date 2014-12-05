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

public class RPFragment extends Fragment {

    private ArrayList<Audio> audios = new ArrayList<>();
    private ListView         listView;

    public RPFragment(ArrayList<Audio> audios) {
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
        View rootView = inflater.inflate(R.layout.fragment_recent_play, container, false);
        listView = (ListView) rootView.findViewById(R.id.recent_play_list);
        listView.setAdapter(new MyBaseAdapter(getActivity(),audios));
        return rootView;
    }

}