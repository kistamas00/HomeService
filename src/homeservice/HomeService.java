package homeservice;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import homeservice.handler.RequestHandler;

public class HomeService {

	public static void main(String[] args) throws IOException {

		HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);

		server.createContext("/", new RequestHandler());

		server.start();

		System.out.println("Server is running!");
	}
}
