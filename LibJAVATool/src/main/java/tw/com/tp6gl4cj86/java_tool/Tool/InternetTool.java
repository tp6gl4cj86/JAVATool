package tw.com.tp6gl4cj86.java_tool.Tool;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

/**
 * Created by tp6gl4cj86 on 2014/12/11.
 */
public class InternetTool
{
    private Context context;

    /**
     * @author <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     */
    @RequiresPermission(allOf = {Manifest.permission.ACCESS_NETWORK_STATE})
    public InternetTool(Context context)
    {
        this.context = context;
    }

    public boolean isInternetConnection()
    {
        final ConnectivityManager cm = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected())
        {
            return networkInfo.isConnected();
        }
        else
        {
            return false;
        }
    }

}
