package com.example.nightmodel.app.fragment;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.*;
import android.widget.*;

import com.example.nightmodel.R;
import com.example.nightmodel.app.activity.MainActivity;
import com.example.nightmodel.app.util.ViewUtil;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * 侧边栏
 * Email:chenlw@dxyer.com
 * Created by User:chenlw on 2014/11/24.
 */
public class NavigationDrawerFragment extends Fragment {
    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private View mFragmentContainerView;
    private ScrollView mScrollView;


    private boolean mUserLearnedDrawer = true;


    private TextView userNameView, userInfoView;

    private RelativeLayout loginLayout;
    private LinearLayout logoutLayout;
    private View mView;

    private View homeView, articleView, familyView, feedBackView, recommendView, aboutView, settingView;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLogin();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        mScrollView = (ScrollView) mView.findViewById(R.id.scroll_view);

        loginLayout = (RelativeLayout) mView.findViewById(R.id.home_drawer_login_layout);
       /* logoutLayout = (LinearLayout) view.findViewById(R.id.home_drawer_logout_layout);*/
       /* userNameView = (TextView) view.findViewById(R.id.home_drawer_user_name);*/
        loginLayout.setOnClickListener(onClickListener);

        homeView = mView.findViewById(R.id.main_menu_home);
        homeView.setOnClickListener(onClickListener);
        MainActivity.mNavDrawerItemViews.add(homeView);
        MainActivity.mNavDrawerItemIds.add(R.id.main_menu_home);

        articleView = mView.findViewById(R.id.main_menu_health_article);
        articleView.setOnClickListener(onClickListener);
        MainActivity.mNavDrawerItemViews.add(articleView);
        MainActivity.mNavDrawerItemIds.add(R.id.main_menu_health_article);

        familyView = mView.findViewById(R.id.main_menu_family_health);
        familyView.setOnClickListener(onClickListener);
        MainActivity.mNavDrawerItemViews.add(familyView);
        MainActivity.mNavDrawerItemIds.add(R.id.main_menu_family_health);

        feedBackView = mView.findViewById(R.id.main_menu_feedback);
        feedBackView.setOnClickListener(onClickListener);

        recommendView = mView.findViewById(R.id.main_menu_recommend);
        recommendView.setOnClickListener(onClickListener);

        aboutView = mView.findViewById(R.id.main_menu_about);
        aboutView.setOnClickListener(onClickListener);

        settingView = mView.findViewById(R.id.main_menu_setting);
        settingView.setOnClickListener(onClickListener);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectItem(view.findViewById(R.id.main_menu_home), R.id.main_menu_home);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            selectItem(v, v.getId());
        }
    };


    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                controlActionBar();
            }
        };

        if (!mUserLearnedDrawer) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void controlActionBar() {
        ActionBar actionbar = MainActivity.mActionBar;
        if (!actionbar.isShowing()) {
            actionbar.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // initLogin();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void selectItem(View v, int position) {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(v, position);
        }
    }

    private void initLogin() {
    }

    public void updateUserInfo() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callbacks interface that all activities using this fragment must
     * implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(View v, int position);
    }

    public void changeSkinMode(boolean isNight) {
        changeTextColor(isNight);
        // homeView,articleView,familyView,feedBackView,recommendView,aboutView;
        if (isNight) {
            mView.setBackgroundColor(getResources().getColor(R.color.navigaton_drawer_bg_night));
            homeView.setBackgroundResource(R.drawable.main_menu_selector_night);
            articleView.setBackgroundResource(R.drawable.main_menu_selector_night);
            familyView.setBackgroundResource(R.drawable.main_menu_selector_night);
            feedBackView.setBackgroundResource(R.drawable.main_menu_selector_night);
            recommendView.setBackgroundResource(R.drawable.main_menu_selector_night);
            aboutView.setBackgroundResource(R.drawable.main_menu_selector_night);
            settingView.setBackgroundResource(R.drawable.main_menu_selector_night);
        } else {
            mView.setBackgroundResource(R.color.navigaton_drawer_bg);
            homeView.setBackgroundResource(R.drawable.main_menu_selector);
            articleView.setBackgroundResource(R.drawable.main_menu_selector);
            familyView.setBackgroundResource(R.drawable.main_menu_selector);
            feedBackView.setBackgroundResource(R.drawable.main_menu_selector);
            recommendView.setBackgroundResource(R.drawable.main_menu_selector);
            aboutView.setBackgroundResource(R.drawable.main_menu_selector);
            settingView.setBackgroundResource(R.drawable.main_menu_selector);
        }
    }

    public void changeTextColor(boolean isNight) {
        List<TextView> allTextViewList = ViewUtil.getAllTextView(ViewUtil.getAllChildView(mView));
        List<View> lineViewList = new ArrayList<View>();
        lineViewList = ViewUtil.getViewList(mView);
        int textColor = 0;
        int singleLineColor = 0;
        if (isNight) {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_night));
            textColor = getResources().getColor(R.color.text_night);
            singleLineColor = R.color.single_line_night;
        } else {
            mView.setBackgroundColor(getResources().getColor(R.color.bg_day));
            textColor = getResources().getColor(R.color.text_day);
            singleLineColor = R.color.single_line_day;
        }
        if (allTextViewList != null && textColor != 0) {
            for (TextView textView : allTextViewList) {
                textView.setTextColor(textColor);
            }
        }
        for (View view : lineViewList) {
            if (view.getTag() != null && view.getTag().equals("line")) {
                view.setBackgroundColor(getResources().getColor(singleLineColor));
            }
        }
    }
}

