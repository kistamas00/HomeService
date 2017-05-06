package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler {

	public void handle(HttpExchange e) throws IOException {

		String response = "This is the response";
		e.sendResponseHeaders(200, response.length());
		OutputStream os = e.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}
