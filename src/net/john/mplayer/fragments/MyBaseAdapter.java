package net.john.mplayer.fragments;

import android.content.Context;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.john.mplayer.R;
import net.john.mplayer.comps.Song;

import java.util.ArrayList;

/**
 * 适配列表项
 * @author john
 *
 */
public class MyBaseAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Song> songs = new ArrayList<>();
    
    public MyBaseAdapter(Context context,ArrayList<Song> songs) {
        mInflater = LayoutInflater.from(context);
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return songs.size();
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
            holder.songName = (TextView)convertView.findViewById(R.id.songName);
            holder.singer = (TextView)convertView.findViewById(R.id.singer);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.songName.setText(songs.get(position).getSongName());
        holder.singer.setText(songs.get(position).getSinger());
        return convertView;
    }
    
    /**
     * 存放列表项内容,优化每次都要从xml中解析的开销
     * @author john
     *
     */
    static class ViewHolder{
        TextView songName;
        TextView singer;
    }

}
