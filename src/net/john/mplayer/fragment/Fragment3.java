package net.john.mplayer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.musicplayer.R;


public class Fragment3 extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflateAndSetupView(inflater, container, savedInstanceState, R.layout.fragment3);
    }

    private View inflateAndSetupView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState, int layoutResourceId) {
        View layout = inflater.inflate(layoutResourceId, container, false);

        return layout;
    }
}
