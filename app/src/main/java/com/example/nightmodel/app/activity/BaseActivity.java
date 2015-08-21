package com.example.nightmodel.app.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.Toast;


import com.example.nightmodel.R;
import com.example.nightmodel.app.util.SystemBarConfig;

/**
 * Activity基类
 *
 * @author maxl
 * @email maxl@dxyer.com
 * @date 2014-12-18
 */
public class BaseActivity extends ActionBarActivity {

    protected Context mContext;
    protected Activity thisActivity;
    protected ActionBar mActionBar;
    protected SystemBarConfig mSystemBarConfig;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.mContext = getApplicationContext();
        thisActivity = this;
        mSystemBarConfig=new SystemBarConfig(thisActivity,true,true);
    }


    protected void showToastMessage(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    // 按两次返回键退出程序
    private long exitTime = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_DOWN &&
                event.getRepeatCount() == 0) {
            if (thisActivity instanceof MainActivity) {
                if ((System.currentTimeMillis() - exitTime) > 2000) {
                    //AppUtil.showShortMessage(mContext, getString(R.string.click_again_to_exit));
                    exitTime = System.currentTimeMillis();

                    return false;
                } else {
                    thisActivity.finish();
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }
    /**
     * 改变actionbar颜色
     * @param isNight
     */
    public void changeActionbarSkinMode(ActionBar mActionbar,boolean isNight){
        int actionbarColor=0;
        if(isNight) {
            actionbarColor= R.color.actionbar_night;
        }else{
            actionbarColor=R.color.actionbar_day;
        }
        setBackgroundAlpha(mActionbar, getResources().getColor(actionbarColor));
    }
    public void setBackgroundAlpha(ActionBar view, int baseColor) {
        int rgb = baseColor;
        Drawable drawable = new ColorDrawable(rgb);
        if(view!=null)
        view.setBackgroundDrawable(drawable);
    }

}