package p.gordenyou.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d(TAG, "StartDownload: startDownload 执行");
        }

        public int getProgress(){
            Log.d(TAG, "getProgress: getProgress 执行" );
            return 0;
        }
    }

    //当一个Activity绑定了服务之后，就可以调用Binder中提供的方法了。
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    //注意onCreate和onStartCommand的区别，onCreate是只在第一次创建时调用。
    //而onStartCommand则是每次启动服务时都会调用。
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate:  服务开启！"); //我们一般会在service中假如日志。

        //下面我们来构造前台服务
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("这是标题")
                .setContentText("这是内容")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);//foreground:前台程序。
    }

    /**
     * 启动服务时候创建
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "服务OnStartCommand:  服务OnStartCommand()调用！");
        new Thread(new Runnable() {
            @Override
            public void run() {
               //为了方便处理耗时业务，使用多线程。因为Service是依附在主线程上的。

                stopSelf();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 服务销毁，用来后续处理，回收资源
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy:  服务结束！");
    }
}
