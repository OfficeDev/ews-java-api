package microsoft.exchange.webservices.data.core.request;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PreDestroy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingletonEwsExecutor {
    private static final SingletonEwsExecutor singletonEwsExecutor = new SingletonEwsExecutor();

    private final ExecutorService executorService;

    private static final Log log = LogFactory.getLog(SingletonEwsExecutor.class);

    private SingletonEwsExecutor() {
      executorService = Executors.newCachedThreadPool();
    }

    public static SingletonEwsExecutor getInstance() {
      return singletonEwsExecutor;
    }

    public ExecutorService getExecutorService() {
      return executorService;
    }

    @PreDestroy
    public void stop() {
      try {
        executorService.shutdown();
        if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
          executorService.shutdownNow();
        }
        if (!executorService.awaitTermination(120, TimeUnit.SECONDS)) {
          log.error(" Executor did not terminate in a timely fashion");
        }
      } catch (InterruptedException ex) {
        log.error("Exception shutting down executor", ex);
      }
    }
}
