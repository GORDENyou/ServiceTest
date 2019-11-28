package p.gordenyou.servicetest.ServiceBestPractice;


//定义下载接口
public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
