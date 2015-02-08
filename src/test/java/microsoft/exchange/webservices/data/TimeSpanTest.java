package microsoft.exchange.webservices.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * The Class TimeSpanTest.
 */
@RunWith(JUnit4.class)
public class TimeSpanTest extends BaseTest {

  /**
   * testTimeSpanToXSDuration
   */
  @Test
  public void testTimeSpanToXSDuration() {
    Calendar calendar = new GregorianCalendar(2008, Calendar.OCTOBER, 10);
    timeSpanToXSDuration(calendar);
  }

  /**
   * Time span to xs duration.
   *
   * @param timeSpan the time span
   * @return the string
   */
  public String timeSpanToXSDuration(Calendar timeSpan) {
    String offsetStr = (timeSpan.SECOND < 0) ? "-" : "";
    String obj = String.format("%s %s %s %s %s ", offsetStr, Math
            .abs(timeSpan.DAY_OF_MONTH), Math.abs(timeSpan.HOUR_OF_DAY),
        Math.abs(timeSpan.MINUTE), Math.abs(timeSpan.SECOND) + "." +
            Math.abs(timeSpan.MILLISECOND));

    return obj;
  }
}
