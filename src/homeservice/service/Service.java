package homeservice.service;

public class Service {

	private boolean running;
	private String startCommand;
	private String stopCommand;

	public boolean isRunning() {
		return running;
	}

	public String getStartCommand() {
		return startCommand;
	}

	public String getStopCommand() {
		return stopCommand;
	}
}
