package microsoft.exchange.webservices.data;

import java.util.concurrent.Future;

interface Callback<T> {
  T processMe(Future<?> task);

}
