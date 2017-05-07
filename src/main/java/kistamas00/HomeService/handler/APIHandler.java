package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import kistamas00.HomeService.service.ServiceHolder;

public class APIHandler implements HttpHandler {

	public void handle(HttpExchange e) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		String response = mapper.writeValueAsString(
				ServiceHolder.getServiceHolder().getServices());

		e.sendResponseHeaders(200, response.length());

		OutputStream os = e.getResponseBody();

		os.write(response.getBytes());
		os.close();
	}
}
