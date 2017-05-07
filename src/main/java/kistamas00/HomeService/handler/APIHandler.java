package kistamas00.HomeService.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import kistamas00.HomeService.service.ServiceHolder;

public class APIHandler implements HttpHandler {

	public static final String URI = "/api";

	public void handle(HttpExchange e) throws IOException {

		String requestMethod = e.getRequestMethod();
		String uri = e.getRequestURI().toString().substring(URI.length());

		String action = "";
		long ID = -1;

		if (!uri.isEmpty()) {

			String command = uri.substring(1);

			if (command.contains("/")) {

				String[] parts = command.split("/");

				action = parts[0];
				ID = Long.parseLong(parts[1]);

			} else {
				ID = Long.parseLong(command);
			}
		}

		if (requestMethod.equals("GET") && action.isEmpty()) {

			ObjectMapper mapper = new ObjectMapper();

			String response = null;

			if (ID == -1) {
				response = mapper.writeValueAsString(
						ServiceHolder.getInstance().getServices());
			} else {

				response = mapper.writeValueAsString(
						ServiceHolder.getInstance().getService(ID));
			}

			e.getResponseHeaders().add("Content-type", "application/json");
			e.sendResponseHeaders(200, response.length());

			OutputStream os = e.getResponseBody();

			os.write(response.getBytes());
			os.close();

		} else if (requestMethod.equals("POST") && action.isEmpty()
				&& ID > -1) {

			ServiceHolder.getInstance().startOrStopService(ID);

			e.getResponseHeaders().add("Content-type", "text/html");
			e.sendResponseHeaders(200, 0);
			e.getResponseBody().close();

		} else {

			e.getResponseHeaders().add("Content-type", "text/html");
			e.sendResponseHeaders(405, 0);
			e.getResponseBody().close();
		}
	}
}
