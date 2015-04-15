package com.example.nightmodel.app.mvp.mode;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by admin on 2015/3/19.
 */
public class ConnectionStatus implements IConnectionStatus {
    @Override
    public boolean isOnline(Context context) {
        boolean success = false;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null) {
            NetworkInfo[] infos = connManager.getAllNetworkInfo();
            if (infos != null) {
                for (int i = 0; i < infos.length; i++) {
                    if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                        success = true;
                        NetworkInfo info = infos[i];
                       /* type = info.getType();
                        typeName = info.getTypeName();*/
                        break;
                    }
                }
            }
        }

        return success;
    }
}
