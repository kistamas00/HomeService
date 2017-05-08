package kistamas00.HomeService.service;

import java.io.IOException;

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

	private boolean executeCommand(String command) {

		try {

			String osName = System.getProperty("os.name");
			String[] commandArray = new String[3];

			if (osName.contains("Windows")) {
				commandArray[0] = "cmd";
				commandArray[1] = "/c";
			} else if (osName.contains("Linux")) {
				commandArray[0] = "bash";
				commandArray[1] = "-c";
			}

			commandArray[2] = command;

			Process p = Runtime.getRuntime().exec(commandArray);
			p.waitFor();

			int exitValue = p.exitValue();

			if (exitValue == 0) {
				return true;
			}

		} catch (IOException e) {

			System.err.println(
					"Command not found! (#" + this.ID + " - " + command + ")");

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		return false;
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

			boolean status = executeCommand(statusCommand);
			System.out.println("Status: " + (status ? "ON" : "OFF"));

			this.running = status;
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
