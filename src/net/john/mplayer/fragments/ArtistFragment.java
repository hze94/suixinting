package net.john.mplayer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import net.john.mplayer.ArtistAudioAcitvity;
import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

public class ArtistFragment extends Fragment implements OnItemClickListener{

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
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(),ArtistAudioAcitvity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("allAudios", audios);
        bundle.putString("currentArtist", audios.get(position).getArtist());
        startActivity(intent);
    }
    
}
