package net.john.mplayer.fragments;

import android.app.ListFragment;
import android.os.Bundle;

import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

public class LMFragment extends ListFragment {

    private ArrayList<Audio> audios = new ArrayList<>();

    public LMFragment(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    /**
     * 为这个ListFragment添加适配器来填充列表项
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new MyBaseAdapter(getActivity(), audios));
    }

}
