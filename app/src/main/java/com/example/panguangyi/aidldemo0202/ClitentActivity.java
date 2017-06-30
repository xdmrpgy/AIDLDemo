package com.example.panguangyi.aidldemo0202;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClitentActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "ClitentActivity";
    private IRemoteService mRemoteService;
    private Boolean isBind = false;

    private TextView tvResult;
    private Button btnBind,btnUnBind,btnKill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnBind = (Button) findViewById(R.id.btnBind);
        btnUnBind = (Button) findViewById(R.id.btnUnBind);
        btnKill = (Button) findViewById(R.id.btnKill);
        btnBind.setOnClickListener(this);
        btnUnBind.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBind:
                bindRemoteService();
                break;
            case R.id.btnUnBind:
                unBindRemoteService();
                break;
            case R.id.btnKill:
                break;
            default:
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
            String result = null;
            try{
                MyData myData = mRemoteService.getMyData();
                result = "pid=" + mRemoteService.getPid()
                        + " iData=" + mRemoteService.getMyData().getiData()
                        + " strData=" + mRemoteService.getMyData().getStrData();
            }catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG,"[ClientActivity] " + result);
            tvResult.setText(result);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mRemoteService = null;
        }
    };

    private void bindRemoteService() {
        if (isBind){
            return;
        }
        Intent i = new Intent(ClitentActivity.this,RemoteService.class);
        Log.d(TAG,IRemoteService.class.getSimpleName());
        i.setAction(IRemoteService.class.getSimpleName());
        bindService(i,mConnection, Context.BIND_AUTO_CREATE);
        isBind = true;
    }

    private void unBindRemoteService() {
        if (!isBind){
            return;
        }
        unbindService(mConnection);
        isBind = false;
    }

    @Override
    protected void onDestroy() {
        unBindRemoteService();
        super.onDestroy();
    }
}
