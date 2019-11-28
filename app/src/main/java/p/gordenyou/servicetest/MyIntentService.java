package p.gordenyou.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyIntentService extends IntentService {

    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * 处理服务的逻辑，处理完后自动关闭。
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //打印当前唯一的id
        Log.d(TAG, "Thread id is: " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: executed");
    }
}

