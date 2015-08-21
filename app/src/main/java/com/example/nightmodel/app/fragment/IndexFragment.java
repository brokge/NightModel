package com.example.nightmodel.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.nightmodel.R;
import com.example.nightmodel.app.NightModelApplication;

/**
 * Email:chenlw@dxyer.com
 * Created by User:chenlw on 2014/12/24.
 */
public class IndexFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (NightModelApplication.appConfig.getNightModeSwitch()) {
            getActivity().setTheme(R.style.Theme_setting_night);
        } else {
            getActivity().setTheme(R.style.Theme_setting_day);
        }
        mView = inflater.inflate(R.layout.fragment_index, null);

        return mView;
    }

 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_index,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId= item.getItemId();
        switch (itemId){
            case R.id.action_night:
                Toast.makeText(getActivity(),"夜间模式",Toast.LENGTH_SHORT).show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    public void changeThemeModel(boolean isNight) {
        if (isNight) {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_night));
        } else {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_day));
        }
    }

}
