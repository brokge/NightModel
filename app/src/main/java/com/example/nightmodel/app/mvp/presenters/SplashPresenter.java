package com.example.nightmodel.app.mvp.presenters;

import android.content.Context;

import com.example.nightmodel.app.mvp.mode.ConnectionStatus;
import com.example.nightmodel.app.mvp.mode.IConnectionStatus;
import com.example.nightmodel.app.mvp.views.ISplashView;

/**
 * Created by admin on 2015/3/19.
 */
public class SplashPresenter {
    private IConnectionStatus mConnectionStatus;
    private ISplashView splashViewView;

    public SplashPresenter() {
        this(new ConnectionStatus());
    }

    public SplashPresenter(IConnectionStatus connectionStatus) {
        mConnectionStatus = connectionStatus;
    }

    public void setSplashViewView(ISplashView view) {
        this.splashViewView = view;
    }

    protected ISplashView getSplashViewView() {
        return splashViewView;
    }


    public void didFinishLoading(Context context) {
        ISplashView view = getSplashViewView();
        if (mConnectionStatus.isOnline(context)) {
            view.hideProgress();
            view.moveToMainView();
        } else {
            view.showProgress();
            view.showNoInternetErrorMsg();
        }

    }


}
