package kistamas00.HomeService.handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class BasicHandler implements HttpHandler {

	private static final String HTMLName = "index.html";

	public void handle(HttpExchange e) throws IOException {

		e.getResponseHeaders().add("Content-type", "text/html");

		OutputStream responseBody = e.getResponseBody();

		byte[] encoded = Files
				.readAllBytes(new File(ClassLoader.getSystemClassLoader()
						.getResource(HTMLName).getFile()).toPath());
		e.sendResponseHeaders(200, encoded.length);

		responseBody.write(encoded);
		responseBody.flush();
		responseBody.close();
	}
}
