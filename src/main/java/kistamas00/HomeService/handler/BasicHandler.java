package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BasicHandler implements HttpHandler {

	public void handle(HttpExchange e) throws IOException {

		// TODO Auto-generated method stub
		String response = "OK!";

		e.sendResponseHeaders(200, response.length());

		OutputStream os = e.getResponseBody();

		os.write(response.getBytes());
		os.close();
	}
}
