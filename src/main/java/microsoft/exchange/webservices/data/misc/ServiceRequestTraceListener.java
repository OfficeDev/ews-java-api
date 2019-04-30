package microsoft.exchange.webservices.data.misc;

import microsoft.exchange.webservices.data.core.request.*;

public interface ServiceRequestTraceListener {
  void requestStart(ServiceRequestBase<?> serviceRequest, HttpWebRequest request);

  void requestError(ServiceRequestBase<?> serviceRequest, HttpWebRequest request, Exception e);

  void requestFinish(ServiceRequestBase<?> serviceRequest, HttpWebRequest request);
}
