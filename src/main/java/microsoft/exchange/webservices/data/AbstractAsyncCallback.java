package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

abstract class AbstractAsyncCallback implements Runnable, Callback<Object> {
  Future<?> task;
  static boolean callbackProcessed = false;

  AbstractAsyncCallback() {
  }

  AbstractAsyncCallback(Future<?> t) {
    this.task = t;
  }

  public void run() {
    while (!callbackProcessed) {

      if (task.isDone()) {
        processMe(task);
        callbackProcessed = true;
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        break;
      }

    }
  }
}
