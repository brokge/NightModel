package com.example.nightmodel.app.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.nightmodel.R;
import com.example.nightmodel.app.NightModelApplication;
import com.example.nightmodel.app.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2015/3/18.
 */
public class SettingActivity extends BaseActivity {
    private  View mView;
    private Switch nightSwitch;
    private List<TextView> allTextViewList;
    private ActionBar mActionbar;
    private boolean isNight=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(NightModelApplication.appConfig.getNightModeSwitch()){
            this.setTheme(R.style.Theme_setting_night);
            isNight=true;
        }
        else {
            this.setTheme(R.style.Theme_setting_day);
            isNight=false;
        }
        setContentView(R.layout.setting);
       // mView =this.getWindow().getDecorView();
        mView =findViewById(R.id.setting_root);
        nightSwitch=(Switch)this.findViewById(R.id.night_switch);
        nightSwitch.setChecked(isNight);
        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                NightModelApplication.appConfig.setNightModeSwitch(isChecked);
                changeSkinMode(isChecked);
            }
        });
        mActionbar=getSupportActionBar();
        initActionbar(isNight);
    }
    private void initActionbar(boolean isNight){
        changeActionbarSkinMode(isNight);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    private  void changeSkinMode(boolean isNight){
        changeActionbarSkinMode(isNight);
        allTextViewList= ViewUtil.getAllTextView(ViewUtil.getAllChildView(mView));
        List<View> lineViewList=new ArrayList<View>();
        lineViewList= ViewUtil.getViewList(mView);
        int textColor=0;
        int weightLineColor=0;
        if (isNight) {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_night));
            weightLineColor=R.color.line_night;
            textColor=getResources().getColor(R.color.text_night);
        } else {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_day));
            weightLineColor=R.color.line_day;
            textColor=getResources().getColor(R.color.text_day);
        }
        if (allTextViewList != null&&textColor!=0) {
            for (TextView textView : allTextViewList) {
                textView.setTextColor(textColor);
            }
        }
        for (View view:lineViewList){
            if(view.getTag()!=null&&view.getTag().equals("weight_line")){
                view.setBackground(getResources().getDrawable(weightLineColor));
            }else {
                view.setBackground(getResources().getDrawable(weightLineColor));
            }
        }
    }
    public void changeActionbarSkinMode(boolean isNight){
        int actionbarColor=0;
        if(isNight) {
            actionbarColor=R.color.actionbar_night;
        }else{
            actionbarColor=R.color.actionbar_day;
        }
        setBackgroundAlpha(mActionbar,getResources().getColor(actionbarColor));
    }

}
