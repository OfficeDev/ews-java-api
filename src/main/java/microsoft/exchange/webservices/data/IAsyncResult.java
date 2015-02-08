package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

/**
 * Represents the stauts of Asynchronous operation.
 */

public interface IAsyncResult extends Future<Object> {

  public Object getAsyncState();

  public WaitHandle getAsyncWaitHanle();

  public boolean getCompleteSynchronously();

  public boolean getIsCompleted();
}
