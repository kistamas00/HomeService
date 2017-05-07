package kistamas00.HomeService.service;

import java.io.File;
import java.util.ArrayList;
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

	private static final String XMLPath = "services.xml";
	private static ServiceHolder instance;

	@XmlElementWrapper(name = "Services")
	@XmlElement(name = "Service")
	private List<Service> services;

	private ServiceHolder() {
	}

	public List<Service> getServices() {
		return services;
	}

	public static final ServiceHolder getServiceHolder() {

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

			File file = new File(XMLPath);
			if (!file.exists()) {

				System.err.println(XMLPath + " not found! Reset settings!");

				serviceHolder = new ServiceHolder();
				serviceHolder.services = new ArrayList<Service>();

				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				marshaller.marshal(serviceHolder, file);

			} else {

				System.out.println(XMLPath + " loaded!");

				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				serviceHolder = (ServiceHolder) unmarshaller.unmarshal(file);
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return serviceHolder;
	}
}
