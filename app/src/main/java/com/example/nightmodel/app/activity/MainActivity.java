package com.example.nightmodel.app.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.*;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nightmodel.R;
import com.example.nightmodel.app.NightModelApplication;
import com.example.nightmodel.app.fragment.ColumnFragment;
import com.example.nightmodel.app.fragment.ChannelFragment;
import com.example.nightmodel.app.fragment.IndexFragment;
import com.example.nightmodel.app.fragment.NavigationDrawerFragment;
import com.example.nightmodel.app.util.SystemBarConfig;
import com.example.nightmodel.app.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 主activity
 * Email:chenlw@dxyer.com
 * Created by User:chenlw on 2014/12/23.
 */
public class MainActivity extends BaseActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private long exitTime;
    private DrawerLayout drawerLayout;
    public static ActionBar mActionBar;
    public Fragment mCurrentFragment;
    public int mCurrentItemId = 0;
    public View mCurrentView;
    public static List<View> mNavDrawerItemViews = new ArrayList<View>();
    public static List<Integer> mNavDrawerItemIds = new ArrayList<Integer>();
    private SystemBarConfig mConfig;

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        if (arg0 == 1000) {
            mNavigationDrawerFragment.updateUserInfo();
            invalidateOptionsMenu();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aspirin_main);
        moveDrawerToTop();
        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
        setTitle(R.string.main_menu_home_title);
        initActionBar();
    }

    private void moveDrawerToTop() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DrawerLayout drawer = (DrawerLayout) inflater.inflate(R.layout.decor, null); // "null" is important.
        // HACK: "steal" the first child of decor view
        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        View child = decor.getChildAt(0);
        decor.removeView(child);
        FrameLayout container = (FrameLayout) drawer.findViewById(R.id.container); // This is the container we defined just now.
        container.addView(child, 0);
        drawer.findViewById(R.id.navigation_drawer).setPadding(0, getStatusBarHeight(), 0, mSystemBarConfig.getNavigationBarHeight());
        // Make the drawer replace the first child
        decor.addView(drawer);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void initActionBar() {
        mActionBar = getSupportActionBar();
    }

    @Override
    public void setTitle(int titleId) {
        this.setTitle(getText(titleId));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        switch (itemId){
            case R.id.action_night:
                Toast.makeText(thisActivity,"夜间模式",Toast.LENGTH_SHORT).show();
                boolean isNightMode= NightModelApplication.appConfig.getNightModeSwitch();
                changeSkinMode(isNightMode);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            boolean isFragmentComplete = true;
            if (isFragmentComplete) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else if ((System.currentTimeMillis() - exitTime) > 2000) {
                    //AppUtil.showShortMessage(this, getString(R.string.click_again_to_exit));
                    exitTime = System.currentTimeMillis();
                } else {
                    //MyApplication.mSSOManager.sync();
                    finish();

                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNavigationDrawerItemSelected(View view, int position) {
        if (position != R.id.home_drawer_login_layout) {
            if (mCurrentItemId != 0 && mCurrentItemId != position) {
                formatNavDrawerItem(mCurrentView, false, mCurrentItemId);
            }
            mCurrentItemId = position;
            mCurrentView = view;
            formatNavDrawerItem(view, true, mCurrentItemId);
        }
        switch (position) {
            case R.id.main_menu_home:
                if (!(mCurrentFragment instanceof IndexFragment)) {
                    IndexFragment homeFragment = new IndexFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment).commit();
                    setTitle(R.string.main_menu_home_title);
                    mCurrentFragment = homeFragment;
                    changeActionbarAndStatusBarBackground(R.color.index_colorPrimary, R.color.index_colorPrimaryDark);
                }
                break;
            case R.id.main_menu_health_article:
                if (!(mCurrentFragment instanceof ChannelFragment)) {
                    ChannelFragment healthArticleFragment = new ChannelFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_content, healthArticleFragment).commit();
                    setTitle(R.string.main_menu_two);
                    mCurrentFragment = healthArticleFragment;
                    changeActionbarAndStatusBarBackground(R.color.article_colorPrimary, R.color.article_colorPrimaryDark);
                }
                break;
            case R.id.main_menu_family_health:
                if (!(mCurrentFragment instanceof ColumnFragment)) {
                    ColumnFragment familyHealthFragment = new ColumnFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_content, familyHealthFragment).commit();
                    setTitle(R.string.main_menu_thre);
                    mCurrentFragment = familyHealthFragment;
                    changeActionbarAndStatusBarBackground(R.color.chanel_colorPrimary, R.color.chanel_colorPrimaryDark);
                }
                break;
            case R.id.main_menu_recommend:

                break;
            case R.id.home_drawer_login_layout:

                break;
            case R.id.main_menu_about:

                break;
            case R.id.main_menu_feedback:

                break;
            case R.id.main_menu_setting:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;

        }
    }

    @SuppressLint("NewApi")
    private void changeActionbarAndStatusBarBackground(int primarycolorId, int darkColorId) {
        setBackgroundAlpha(mActionBar, getResources().getColor(primarycolorId));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(darkColorId));
        }

    }

    /**
     * 保存选中状态
     *
     * @param view
     * @param selected
     */
    private void formatNavDrawerItem(View view, boolean selected, int id) {
    /*    ColorStateList colorStateList = null;
        List<TextView> textViews = ViewUtil.getAllTextView(ViewUtil.getAllChildView(view));
        int selectedColor = getResources().getColor(R.color.index_colorPrimary);
        switch (id) {
            case R.id.main_menu_family_health:
                colorStateList = getResources().getColorStateList(R.drawable.main_menu_family_text_selector);
                selectedColor = getResources().getColor(R.color.menu_text_family_selected_color);
                break;
            case R.id.main_menu_health_article:
                colorStateList = getResources().getColorStateList(R.drawable.main_menu_wiki_text_selector);
                selectedColor = getResources().getColor(R.color.menu_text_wiki_selected_color);
                break;
            case R.id.main_menu_home:
                colorStateList = getResources().getColorStateList(R.drawable.main_menu_text_selector);
                selectedColor = getResources().getColor(R.color.menu_text_index_selected_color);
                break;
            default:
                colorStateList = getResources().getColorStateList(R.drawable.main_menu_text_selector);
        }*/
     /*   if (textViews.size() > 0) {
            TextView titleView = textViews.get(0);
            // configure its appearance according to whether or not it's selected
            if (selected) {
                titleView.setTextColor(selectedColor);
            } else {
                if (colorStateList != null)
                    titleView.setTextColor(colorStateList);
            }
        } else {
            if (selected) {
                ((TextView) view).setTextColor(selectedColor);
            } else {
                if (colorStateList != null)
                    ((TextView) view).setTextColor(colorStateList);
            }
        }*/
        view.setSelected(selected);
        List<ImageView> imageViews = ViewUtil.getallImageView(ViewUtil.getAllChildView(view));
        if (imageViews.size() > 0) {
            ImageView iconView = imageViews.get(0);
            iconView.setSelected(selected);
        }
        List<TextView> textViews = ViewUtil.getAllTextView(ViewUtil.getAllChildView(view));
        if (textViews.size() > 0) {
            TextView titleView = textViews.get(0);
            if (selected) {
                titleView.setSelected(selected);
            }
        } else {
            if (selected) {
                ((TextView) view).setSelected(selected);
            }
        }
    }

    private void changeSkinMode(boolean isNight){
        NightModelApplication.appConfig.setNightModeSwitch(!isNight);


    }
}
