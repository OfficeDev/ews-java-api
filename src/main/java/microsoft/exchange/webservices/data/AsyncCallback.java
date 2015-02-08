package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

abstract class AsyncCallback extends AbstractAsyncCallback {

  AsyncCallback() {

  }

  void setTask(Future<?> task) {

    this.task = task;
  }

  Future<?> getTask() {
    return this.task;
  }



}
