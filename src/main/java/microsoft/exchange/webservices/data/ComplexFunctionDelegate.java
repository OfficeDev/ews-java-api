package microsoft.exchange.webservices.data;

interface ComplexFunctionDelegate<T1 extends EwsServiceXmlReader> {

  Boolean func(T1 arg1) throws Exception;
}
