package net.john.mplayer.audio;

import java.text.CollationKey;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * 通过ConentResolver从sqlite中获取audio信息
 * @author john
 *
 */
public class AudioProvider implements AbstructProvider {

    private Context context;
    
    public AudioProvider(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Audio> getList() {
        
        ArrayList<Audio> list = null;
        if (context != null) {
            Cursor cursor = context.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                    null, null);
            if (cursor != null) {
                list = new ArrayList<Audio>();
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor
                            .getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    String title = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    String album = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    String artist = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    String path = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String displayName = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    String mimeType = cursor
                            .getString(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.MIME_TYPE));
                    long duration = cursor
                            .getInt(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    long size = cursor
                            .getLong(cursor
                                    .getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                    Audio audio = new Audio(id, title, album, artist, path,
                            displayName, mimeType, duration, size);
                    list.add(audio);
                }
                cursor.close();
            }
        }
        Comparator<Audio> comparator = new Comparator<Audio>() {
            
            @Override
            public int compare(Audio lhs, Audio rhs) {
                Collator collator = Collator.getInstance(Locale.CHINESE);
                if(!lhs.getTitle().equals(rhs.getTitle())){
                    return (collator.compare(lhs.getTitle(), rhs.getTitle()));
                }else if (!lhs.getArtist().equals(rhs.getTitle())) {
                    return (collator.compare(lhs.getArtist(), rhs.getArtist()));
                }else {
                    return (collator.compare(lhs.getPath(), rhs.getPath()));
                }
            }
            
        };
//        Collections.sort(list, comparator);
        return list;
    }

}