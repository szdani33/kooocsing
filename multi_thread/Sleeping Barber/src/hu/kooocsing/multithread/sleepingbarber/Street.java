package hu.kooocsing.multithread.sleepingbarber;

public class Street extends Thread{
	
	private BarberShop barberShop;
	
	public Street(BarberShop barberShop) {
		this.barberShop = barberShop;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			new Customer("00" + i, barberShop).start();
			tryToSleep(1);
		}
	}

	private void tryToSleep(int sleepTimeInSeconds) {
		try {
			sleep(sleepTimeInSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
