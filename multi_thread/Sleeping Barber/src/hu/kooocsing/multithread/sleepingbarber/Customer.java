package hu.kooocsing.multithread.sleepingbarber;

public class Customer extends Thread{

	private final String name;
	private final BarberShop barberShop;	
	
	public Customer(String name, BarberShop barberShop) {
		this.name = name;
		this.barberShop = barberShop;
	}
	
	@Override
	public void run() {
		System.out.println(name + " arrives to the barber shop");
		if(barberShop.isBarberSleeping()) {
			barberShop.wakeUpBarber(this);
			tryToWait();
		} else {
			if(barberShop.hasFreeSeat()) {
				barberShop.sitDown(this);
				tryToWait();
			}
		}
		System.out.println(name + " leaves the shop");
	}

	private void tryToWait() {
		try {
			synchronized (this) {					
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getCustomerName() {
		return name;
	}
}
