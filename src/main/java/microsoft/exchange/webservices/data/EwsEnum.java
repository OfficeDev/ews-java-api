package microsoft.exchange.webservices.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface EwsEnum.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) @interface EwsEnum {

  /**
   * Schema name.
   *
   * @return the string
   */
  String schemaName();
}
