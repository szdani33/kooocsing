package hu.kocsing.multithread.volat;

public abstract class Destroyer extends Thread {
	
	private static final int REST_TIME = 2;
	
	protected String name;

	public Destroyer(String name) {
		super();
		this.name = name;
	}

	protected void failed() {
		yell("Shit, I was wrong!");
	}

	protected void threat() {
		yell("You cannot stop ME! I will destroy everything! HAHAHA!");
	}

	protected void destroyed() {
		yell("HAHAHA I destroyed the universe!");
	}

	protected void rest() {
		try {
			sleep(REST_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void yell(String message) {
		System.out.println(name + ": " + message);
	}

	public abstract void arrest();
}
