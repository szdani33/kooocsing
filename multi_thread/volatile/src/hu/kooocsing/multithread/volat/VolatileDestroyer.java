package hu.kooocsing.multithread.volat;

public class VolatileDestroyer extends Destroyer {

	private volatile boolean stop = false;
	
	public VolatileDestroyer(String name) {
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
