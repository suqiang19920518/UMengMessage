package com.thinkive.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.thinkive.umeng.message.notification.DebugNotification;
import com.umeng.message.PushAgent;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_START_ACTION = "com.thinkive.umeng.message.start";
    public static final String TASK_END_ACTION = "com.thinkive.umeng.message.end";

    private PushAgent mPushAgent;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case TASK_START_ACTION:
                    Toast.makeText(MainActivity.this, "任务执行开始", Toast.LENGTH_SHORT).show();
                    break;
                case TASK_END_ACTION:
                    Toast.makeText(MainActivity.this, "任务执行完毕", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();//在所有的Activity或应用的BaseActivity的onCreate方法中务必添加

        IntentFilter filter = new IntentFilter();
        filter.addAction(TASK_START_ACTION);
        filter.addAction(TASK_END_ACTION);
        registerReceiver(mReceiver, filter);

    }


    public void onClick(View view) {
        DebugNotification.transmission(MainActivity.this, new Handler());//透传测试
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
}
