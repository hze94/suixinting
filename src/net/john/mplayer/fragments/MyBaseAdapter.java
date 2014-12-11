package net.john.mplayer.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.john.mplayer.R;
import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

/**
 * 适配列表项
 * 
 * @author john
 * 
 */
public class MyBaseAdapter extends BaseAdapter {

    private LayoutInflater   mInflater;
    private ArrayList<Audio> audios = new ArrayList<>();

    public MyBaseAdapter(Context context, ArrayList<Audio> songs) {
        mInflater = LayoutInflater.from(context);
        this.audios = songs;
    }

    @Override
    public int getCount() {
        return audios.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 此方法返回的view作为列表项
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.local_music_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title_tv);
            holder.artist = (TextView) convertView.findViewById(R.id.artist_tv);
            holder.duration = (TextView) convertView.findViewById(R.id.duration_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(audios.get(position).getTitle());
        holder.artist.setText(audios.get(position).getArtist());
        holder.duration.setText(audios.get(position).getFormatDuration());
        return convertView;
    }

    /**
     * 存放列表项内容,优化每次都要从xml中解析的开销
     * 
     * @author john
     * 
     */
    static class ViewHolder {
        TextView title;
        TextView artist;
        TextView duration;
    }

}
