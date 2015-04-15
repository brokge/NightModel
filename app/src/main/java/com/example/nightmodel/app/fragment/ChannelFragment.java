package com.example.nightmodel.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nightmodel.R;

/**
 *
 * Email:brokge@gmail.com
 * Created by User:chenlw on 2014/12/24.
 */
public class ChannelFragment extends BaseFragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTheme(R.style.Theme_APP_article);
        view = inflater.inflate(R.layout.fragment_channel, null);
        return view;
    }





}
