package hu.kocsing.multithread.volat;


public class VolatileMain {
	
	public static void main(String[] args) {
		NotVolatileDestroyer unstoppableDestroyer = new NotVolatileDestroyer("Unstoppable");
		VolatileDestroyer stoppableDestroyer = new VolatileDestroyer("Stoppable");
		unstoppableDestroyer.start();
		stoppableDestroyer.start();
		new Thread(new SuperHero(unstoppableDestroyer, stoppableDestroyer)).start();
	}
}
