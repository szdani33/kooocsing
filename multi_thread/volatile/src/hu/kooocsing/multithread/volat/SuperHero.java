package hu.kooocsing.multithread.volat;

public class SuperHero extends Thread {
	
	private Destroyer[] destroyers;
	
	public SuperHero(Destroyer... destroyers) {
		super();
		this.destroyers = destroyers;
	}

	@Override
	public void run() {
//		try {
//			sleep(20);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		for(Destroyer destr : destroyers) {
			destr.arrest();
		}
	}

}
