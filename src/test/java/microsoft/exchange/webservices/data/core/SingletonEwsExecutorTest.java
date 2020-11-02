package microsoft.exchange.webservices.data.core;

import microsoft.exchange.webservices.data.core.request.SingletonEwsExecutor;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SingletonEwsExecutorTest {

  @Test
  public void testSingleton() throws Exception{
    SingletonEwsExecutor singletonEwsExecutor = SingletonEwsExecutor.getInstance();
    Assert.assertNotNull(singletonEwsExecutor);

    ExecutorService executorService = singletonEwsExecutor.getExecutorService();
    Assert.assertNotNull(executorService);

    final int testCallback = 0;
    final int expectedResult = 1;

    Callable<Object> task = new Callable<Object>() {
      @Override public Object call() throws Exception {
        return testCallback + 1;
      }
    };

    Future<Object> future = executorService.submit(task);
    int finalResult = (Integer) future.get();
    Assert.assertEquals(finalResult, expectedResult);

  }
}
