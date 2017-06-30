package com.example.panguangyi.aidldemo0202;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    MyData myData;

    @Override
    public void onCreate() {
        super.onCreate();
        initMyData();
        Log.d(TAG,"[RemoteService] onCreate");
    }

    private void initMyData() {
        myData = new MyData();
        myData.setiData(26);
        myData.setStrData("pgy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG,"[RemoteService] onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"[RemoteService] onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"[RemoteService] onDestroy");
        super.onDestroy();
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub(){
        @Override
        public int getPid() throws RemoteException {
            Log.d(TAG,"[RemoteService] getPid()=" + android.os.Process.myPid());
            return android.os.Process.myPid();
        }

        @Override
        public MyData getMyData() throws RemoteException {
            Log.d(TAG,"[RemoteService] getMyData()=" + myData.toString());
            return myData;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };
}
