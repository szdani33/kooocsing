package hu.kooocsing.multithread.sleepingbarber;

import java.util.ArrayList;
import java.util.List;

public class BarberShop {

	private final Barber barber;
	private final List<Customer> waitingSeats;
	private final int numberOfWaitingSeats;
	private Customer actualCustomer;
	
	public BarberShop(int numberOfWaitingSeats) {
		this.numberOfWaitingSeats = numberOfWaitingSeats;
		waitingSeats = new ArrayList<Customer>(numberOfWaitingSeats);
		barber = new Barber(this);
		barber.start();
	}
	
	public void sitDown(Customer customer) {
		System.out.println(customer.getCustomerName() + " sits down and wait for the barber");
		waitingSeats.add(customer);
	}
	
	public boolean hasFreeSeat() {
		return waitingSeats.size() < numberOfWaitingSeats;
	}
	
	public boolean hasWaitingCustomer() {
		return !waitingSeats.isEmpty();
	}

	public boolean isBarberSleeping() {
		return barber.isSleeping();
	}

	public void wakeUpBarber(Customer customer) {
		actualCustomer = customer;
		synchronized (barber) {
			System.out.println(customer.getCustomerName() + " wakes up the barber.");
			barber.notify();
		}
	}

	public void finishHairCut() {
		synchronized (actualCustomer) {
			System.out.println("Barber finished the haircut of " + actualCustomer.getCustomerName());
			actualCustomer.notify();
			actualCustomer = null;
		}
	}

	public void callNextCustomer() {
		actualCustomer = waitingSeats.remove(0);
	}

	public Customer getActualCustomer() {
		return actualCustomer;
	}
}
