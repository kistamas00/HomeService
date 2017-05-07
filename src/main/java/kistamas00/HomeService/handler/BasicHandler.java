package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BasicHandler implements HttpHandler {

	private static final String HTMLName = "index.html";

	public void handle(HttpExchange e) throws IOException {

		e.getResponseHeaders().add("Content-type", "text/html");

		OutputStream responseBody = e.getResponseBody();

		byte[] encoded = IOUtils.toByteArray(ClassLoader.getSystemClassLoader()
				.getResourceAsStream(HTMLName));

		e.sendResponseHeaders(200, encoded.length);

		responseBody.write(encoded);
		responseBody.flush();
		responseBody.close();
	}
}
