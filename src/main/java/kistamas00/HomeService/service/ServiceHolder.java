package kistamas00.HomeService.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ServiceHolder")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceHolder {

	private static final String XMLName = "services.xml";
	private static ServiceHolder instance;
	private static long nextServiceID;

	@XmlElementWrapper(name = "Services")
	@XmlElement(name = "Service")
	private List<Service> services;

	private ServiceHolder() {
	}

	public List<Service> getServices() {

		updateStatuses();

		return services;
	}

	public Service getService(long id) {

		Service service = services.stream().filter(s -> s.getID() == id)
				.findFirst().get();

		service.updateStatus();

		return service;
	}

	public void startOrStopService(long id) {

		Service service = services.stream().filter(s -> s.getID() == id)
				.findFirst().get();

		if (service.getStatusCommand() == null
				|| service.getStatusCommand().isEmpty()) {

			service.start();

		} else {

			if (service.isRunning()) {
				service.stop();
			} else {
				service.start();
			}
		}
	}

	private void updateStatuses() {
		services.forEach(s -> s.updateStatus());
	}

	public static final ServiceHolder getInstance() {

		if (instance == null) {

			instance = create();
		}

		return instance;
	}

	private static final ServiceHolder create() {

		ServiceHolder serviceHolder = null;

		try {

			JAXBContext jaxbContext = JAXBContext
					.newInstance(ServiceHolder.class);

			File file = new File(ClassLoader.getSystemClassLoader()
					.getResource(XMLName).getFile());

			if (!file.exists()) {

				System.err.println(XMLName + " not found! Reset settings!");

				serviceHolder = new ServiceHolder();
				serviceHolder.services = new ArrayList<Service>();

				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(serviceHolder, file);

				nextServiceID = 0;

			} else {

				System.out.println(XMLName + " loaded!");

				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				serviceHolder = (ServiceHolder) unmarshaller.unmarshal(file);

				nextServiceID = Collections
						.max(serviceHolder.services,
								Comparator.comparing(s -> s.getID()))
						.getID() + 1;

				serviceHolder.services.forEach(s -> s
						.setID(s.getID() < 0 ? nextServiceID++ : s.getID()));
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return serviceHolder;
	}
}
