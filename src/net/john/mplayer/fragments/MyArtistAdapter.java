package net.john.mplayer.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;
import net.john.mplayer.fragments.MyBaseAdapter.ViewHolder;
import net.john.mplayer.utils.AudioParser;

import java.util.ArrayList;
import java.util.HashMap;

public class MyArtistAdapter extends BaseAdapter {
    private LayoutInflater   mInflater;
    private ArrayList<Audio> audios = new ArrayList<>();
    private HashMap<String, ArrayList<Audio>> artistMap;
    private ArrayList<String> artistNames;
    
    public MyArtistAdapter(Context context, ArrayList<Audio> audios) {
        mInflater = LayoutInflater.from(context);
        this.audios = audios;
        AudioParser audioParser = new AudioParser(audios);
        this.artistMap = audioParser.parseArtist();
        this.artistNames = audioParser.parseArtistNames();
    }

    @Override
    public int getCount() {
        return artistNames.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.artist_list_item, null);
            holder = new ViewHolder();
            holder.artistName = (TextView) convertView.findViewById(R.id.artistName);
            holder.songCount = (TextView) convertView.findViewById(R.id.songCount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.artistName.setText(artistNames.get(position));
        holder.songCount.setText(artistMap.get(artistNames.get(position)).size() + " songs");
        return convertView;
    }

    static class ViewHolder {
        TextView artistName;
        TextView songCount;
    }
    
}
