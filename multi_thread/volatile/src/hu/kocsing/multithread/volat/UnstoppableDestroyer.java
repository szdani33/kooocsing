package hu.kocsing.multithread.volat;

public class UnstoppableDestroyer extends Destroyer {

	private boolean stop;
	private int counter;
	
	public UnstoppableDestroyer(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (!stop && counter < 10) {
			threat();
//			rest();
			counter++;
		}
		if (stop) {
			failed();
		} else {
			destroyed();
		}
	}
	
	public void arrest() {
		stop = true;
	}
}
