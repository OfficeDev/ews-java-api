package microsoft.exchange.webservices.data;
import java.io.IOException;
import java.util.concurrent.*;
public class CallableMethod implements Callable {
	HttpWebRequest request;
	CallableMethod(HttpWebRequest request){
		this.request= request;
	}

	protected HttpClientWebRequest executeMethod() throws EWSHttpException, HttpErrorException, IOException{
		
		 request.executeRequest();
		 return (HttpClientWebRequest)request;
	}
	
	public  HttpWebRequest call(){
		
		try {
			return executeMethod();
		} catch (EWSHttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HttpErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return request;
	}
}
