package kistamas00.HomeService.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Service {

	private Boolean running;

	@XmlAttribute
	private long ID;

	@XmlAttribute
	private String startCommand;

	@XmlAttribute
	private String stopCommand;

	public Service() {
		this.running = false;
	}

	public long getID() {
		return ID;
	}

	public boolean isRunning() {
		return running;
	}

	public String getStartCommand() {
		return startCommand;
	}

	public String getStopCommand() {
		return stopCommand;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setStartCommand(String startCommand) {
		this.startCommand = startCommand;
	}

	public void setStopCommand(String stopCommand) {
		this.stopCommand = stopCommand;
	}
}
