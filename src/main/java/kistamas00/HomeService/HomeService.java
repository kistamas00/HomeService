package kistamas00.HomeService;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.bind.JAXBException;

import com.sun.net.httpserver.HttpServer;

import kistamas00.HomeService.handler.APIHandler;
import kistamas00.HomeService.handler.BasicHandler;
import kistamas00.HomeService.service.ServiceHolder;

public class HomeService {

	public static void main(String[] args) throws IOException, JAXBException {

		ServiceHolder.getInstance();

		HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
		server.createContext("/", new BasicHandler());
		server.createContext("/api", new APIHandler());
		server.start();

		System.out.println("HomeService server is running!");
	}
}
