package com.thinkive.umeng.message;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.umeng.message.PushAgent;

public class TestActivity extends AppCompatActivity {

    private PushAgent mPushAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.onAppStart();//在所有的Activity或应用的BaseActivity的onCreate方法中务必添加
    }
}
