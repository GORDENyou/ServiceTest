#### MyService.Activity

主要是通过日志来观察Service的生命周期

#### MyIntetService.Activity

注意IntentService与Service的区别:

由于Service执行在UI线程中，所以不能执行耗时操作，所以我们需要在Service中开启子线程执行耗时操作。Google也考虑到了这一点，提供了一个IntentService给我们执行耗时操作；当然，IntentService也是开启了一个子线程进行耗时操作；IntentService当执行完操作时， 还会自动关闭服务。





### ServiceBestPractice

这个包里实现了文件断点续传

#### DownloadListener.Interface

这个接口定义了下载的操作：开始，暂停，取消，下载成功。

#### DownloadTask

继承了AsyncTask，定义了四种完成状态：成功，失败，暂停，取消。在后台 doInBackground() 处理下载任务，下载前查看下载文件的内容，通过文件的内容大小来实现断点续传：只下载没有下载的文件，若下载暂停便记录断点，下次下载从断点开始下载任务。

后台处理完成后在 onPostExecute() 中根据下载状态处理回调。

#### DownloadService.Activity

在Activity中实现了DownloadListener 接口，通过按钮监听开始、暂停和取消的操作。下载的状态会通过通知的形式显示在通知栏中，只有下载状态改变时才会改变更新下载进度。



本次实践参考的是郭神的《第一行代码》第二版











