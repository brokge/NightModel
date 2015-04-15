package com.example.nightmodel.app.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nightmodel.R;
import com.example.nightmodel.app.mvp.presenters.SplashPresenter;
import com.example.nightmodel.app.mvp.views.ISplashView;


/**
 * launcher pager
 * Created by admin on 2015/3/19.
 */
public class SplashActivity extends BaseActivity implements ISplashView {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private SplashPresenter mPresenter = new SplashPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.splash);
        mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.hide();

        mPresenter.setSplashViewView(this);
        mTextView = (TextView) findViewById(R.id.splash_text);
        mProgressBar = (ProgressBar) findViewById(R.id.splash_progressbar);
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "Lobster-Regular.ttf");
        //使用字体
        mTextView.setTypeface(typeFace);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.didFinishLoading(this);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoInternetErrorMsg() {
        mTextView.setText("No internet");
    }

    @Override
    public void moveToMainView() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
