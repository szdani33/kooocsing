package hu.kooocsing.multithread.sleepingbarber;

public class Main {

	public static void main(String[] args) {
		BarberShop barberShop = new BarberShop(5);
		new Street(barberShop).start();
	}

}
