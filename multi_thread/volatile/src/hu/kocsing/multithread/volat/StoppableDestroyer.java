package hu.kocsing.multithread.volat;

public class StoppableDestroyer extends Destroyer {

	private volatile boolean stop = false;
	
	public StoppableDestroyer(String name) {
		super(name);
	}
	
	@Override
	public void run() {
		while (!stop) {
			threat();
//			rest();
		}
		failed();
	}
	
	@Override
	public void arrest() {
		stop = true;
	}
}
