package kistamas00.HomeService.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class Service {

	private Boolean running;

	@XmlAttribute
	private long ID;

	@XmlAttribute
	private String name;

	@XmlAttribute
	private String statusCommand;

	@XmlAttribute
	private String startCommand;

	@XmlAttribute
	private String stopCommand;

	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		try {

			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (IOException e) {
			System.err.println(
					"Command not found! (#" + this.ID + " - " + command + ")");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return output.toString();
	}

	public void start() {
		if (startCommand != null && !startCommand.isEmpty()) {
			System.out.println("Execute start command! (#" + this.ID + " - "
					+ startCommand + ")");
			executeCommand(startCommand);
		}
	}

	public void stop() {
		if (stopCommand != null && !stopCommand.isEmpty()) {
			System.out.println("Execute stop command! (#" + this.ID + " - "
					+ stopCommand + ")");
			executeCommand(stopCommand);
		}
	}

	public void updateStatus() {
		if (statusCommand != null && !statusCommand.isEmpty()) {
			System.out.println("Execute status command! (#" + this.ID + " - "
					+ statusCommand + ")");
			executeCommand(statusCommand);
			// TODO set running variable
		}
	}

	public long getID() {
		return ID;
	}

	public boolean isRunning() {
		return running != null ? running : false;
	}

	public String getName() {
		return name;
	}

	public String getStatusCommand() {
		return statusCommand;
	}

	public String getStartCommand() {
		return startCommand;
	}

	public String getStopCommand() {
		return stopCommand;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatusCommand(String statusCommand) {
		this.statusCommand = statusCommand;
	}

	public void setStartCommand(String startCommand) {
		this.startCommand = startCommand;
	}

	public void setStopCommand(String stopCommand) {
		this.stopCommand = stopCommand;
	}
}
