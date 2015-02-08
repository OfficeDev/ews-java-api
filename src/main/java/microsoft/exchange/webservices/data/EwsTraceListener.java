package microsoft.exchange.webservices.data;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * EwsTraceListener logs request/responses.
 */
class EwsTraceListener implements ITraceListener {

  private Log log = LogFactory.getLog(EwsTraceListener.class);


  protected EwsTraceListener() {
  }

  /**
   * Handles a trace message.
   *
   * @param traceType    The trace type
   * @param traceMessage The trace message
   */
  @Override
  public void trace(String traceType, String traceMessage) {
    log.trace(traceType + " - " + traceMessage);
  }
}
