package net.john.mplayer;

import android.app.Application;
import android.os.Environment;

import java.io.File;

public class SuixintingApp extends Application {

    public static boolean mIsSleepClockSetting = false;
    private static String rootPath = "/suixinting";
    public static String lrcPath = "/lrc";
    
    @Override
    public void onCreate() {
        super.onCreate();
        initPath();
    }
    
    private void initPath() {
        String ROOT = "";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ROOT = Environment.getExternalStorageDirectory().getPath();
        }
        rootPath = ROOT + rootPath;
        lrcPath = rootPath + lrcPath;
        File lrcFile = new File(lrcPath);
        if(lrcFile.exists()) {
            lrcFile.mkdirs();
        }
    }

}
