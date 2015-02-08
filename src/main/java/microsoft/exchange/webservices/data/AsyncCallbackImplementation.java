package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

public class AsyncCallbackImplementation extends AsyncCallback {

  @Override
  public Object processMe(Future<?> task) {
    System.out.println("In Async Callback" + task.isDone());
    return null;
  }

}
