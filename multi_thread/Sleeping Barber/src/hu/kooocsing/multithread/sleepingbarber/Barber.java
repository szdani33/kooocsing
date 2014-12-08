package hu.kooocsing.multithread.sleepingbarber;

public class Barber extends Thread {
	
	private static final int HAIRCUT_TIME = 5;
	private BarberShop barberShop;
	private boolean sleeping;
	
	public Barber(BarberShop barberShop) {
		this.barberShop = barberShop;
	}

	@Override
	public void run() {
		while (true) {
			if(barberShop.hasWaitingCustomer()) {
				barberShop.callNextCustomer();
				cutHair();
			} else {
				rest();
				cutHair();
			}
		}
	}

	private void cutHair() {
		System.out.println("The barber begins the haircut of " + barberShop.getActualCustomer().getCustomerName());
		for(int i = 0; i < HAIRCUT_TIME; i++) {
			try {
				System.out.println(HAIRCUT_TIME - i + " seconds left");
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		barberShop.finishHairCut();
	}

	private synchronized void rest() {
		try {
			System.out.println("The barber falls asleep");
			sleeping = true;
			wait();
			sleeping = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isSleeping() {
		return sleeping;
	}
}
