package net.john.mplayer.utils;

import net.john.mplayer.audio.Audio;

import java.util.ArrayList;
import java.util.HashMap;

public class AudioParser {

    private ArrayList<Audio>                  audios      = new ArrayList<>(); // 所有audio
    private ArrayList<String>                 artistNames;
    private HashMap<String, ArrayList<Audio>> artistMap;

    public AudioParser(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    /**
     * 将audio arraylist数据解析成一个map,对应每个artist和它的歌曲
     * @return
     */
    public HashMap<String, ArrayList<Audio>> parseArtist() {
        artistMap = new HashMap<>();
        ArrayList<Audio> artistAudios; // 一个artist的歌曲列表
        for (Audio audio : audios) {
            if (!artistMap.containsKey(audio.getArtist())) {
                artistAudios = new ArrayList<>();
                artistAudios.add(audio);
                artistMap.put(audio.getArtist(), artistAudios);
            } else {
                artistMap.get(audio.getArtist()).add(audio);
            }
        }
        return artistMap;
    }
    
    public ArrayList<String> parseArtistNames(){
        artistNames = new ArrayList<String>();
        for (Audio audio : audios) {
            if (!artistNames.contains(audio.getArtist())) {
                artistNames.add(audio.getArtist());
            }
        }
        return artistNames;
    }

}
