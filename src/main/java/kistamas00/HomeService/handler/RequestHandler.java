package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RequestHandler implements HttpHandler {

	public void handle(HttpExchange e) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		// FIXME
		String response = mapper.writeValueAsString(null);

		e.sendResponseHeaders(200, response.length());

		OutputStream os = e.getResponseBody();

		os.write(response.getBytes());
		os.close();
	}
}
