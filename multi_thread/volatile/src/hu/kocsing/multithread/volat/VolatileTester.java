package hu.kocsing.multithread.volat;


public class VolatileTester {
	
	public static void main(String[] args) {
		UnstoppableDestroyer unstoppableDestroyer = new UnstoppableDestroyer("Unstoppable");
		StoppableDestroyer stoppableDestroyer = new StoppableDestroyer("Stoppable");
		unstoppableDestroyer.start();
		stoppableDestroyer.start();
		new Thread(new SuperHero(unstoppableDestroyer, stoppableDestroyer)).start();
	}
}
