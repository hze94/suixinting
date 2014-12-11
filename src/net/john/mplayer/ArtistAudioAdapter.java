package net.john.mplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.john.mplayer.audio.Audio;

import java.util.ArrayList;

/**
 * 
 * 各个歌手(ArtistAudioAcitiviy)音乐列表的适配器
 * 
 * @author john
 * 
 */
public class ArtistAudioAdapter extends BaseAdapter {

    private LayoutInflater   mInflater;
    
    //一个artist的audios
    private ArrayList<Audio> audios = new ArrayList<>();

    public ArtistAudioAdapter(ArrayList<Audio> audios) {
        this.audios = audios;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.music_list_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.artist = (TextView) convertView.findViewById(R.id.artist);
            holder.duration = (TextView) convertView.findViewById(R.id.duration_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(audios.get(position).getTitle());
        holder.artist.setText(audios.get(position).getArtist());
        holder.duration.setText(audios.get(position).getDuration() + "");
        return convertView;
    }
    
    static class ViewHolder {
        TextView title;
        TextView artist;
        TextView duration;
    }

}
