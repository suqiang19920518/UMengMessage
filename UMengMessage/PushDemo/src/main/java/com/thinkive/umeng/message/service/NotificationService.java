package com.thinkive.umeng.message.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.thinkive.umeng.message.MainActivity;
import com.thinkive.umeng.message.TestActivity;

// 此服务是基于完全自定义消息来开启应用服务进程的示例
// 开发这可以仿照此服务来重写自己的服务，然后在服务中
//做相应的操作，比如打开应用，或者打开应用的主进程服务等
//这样可以唤醒应用的主进程服务，重启应用的服务
public class NotificationService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		// code to handle to create service
		// ......
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// code to handler to start service
		// ......

		new Thread(){
			public void run() {

				sendBroadcast(new Intent(MainActivity.TASK_START_ACTION));

				for(int i = 0; i < 20; i++) {
					try {
						Log.e("danny", "任务执行到 " + i);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				/**
				 * 当服务执行完毕之后，跳转界面
				 */
				Intent newIntent = new Intent(NotificationService.this, TestActivity.class);
				newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须加上
				startActivity(newIntent);

				sendBroadcast(new Intent(MainActivity.TASK_END_ACTION));
			}
		}.start();
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}



}
