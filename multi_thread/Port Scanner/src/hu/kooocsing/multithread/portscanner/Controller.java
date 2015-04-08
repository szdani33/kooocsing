package hu.kooocsing.multithread.portscanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Controller {
	private List<HostAndPort> toCheck;
	private List<Scanner> scanners;
	private Map<String, Integer> open = new HashMap<String, Integer>(); // AtomicInt
	private Map<String, Integer> closed = new HashMap<String, Integer>(); // ConcurrentHashMap
	private final int numberOfThreads;
	
	public Controller(int numberOfThreads, String... hosts) {
		this.numberOfThreads = numberOfThreads;
		initializeHostAndPorts(hosts);
	}

	private void initializeHostAndPorts(String... hosts) {
		toCheck = new ArrayList<HostAndPort>();
		for(String h : hosts) {
			for(int i = 1001; i <= 65535; i++) {
				toCheck.add(new HostAndPort(h, i));
			}
			open.put(h, 0);
			closed.put(h, 0);
		}
	} 

	public void startScanners() {
		scanners = new ArrayList<Scanner>();
		for(int i = 0; i < numberOfThreads; i++) {
			Scanner scanner = new Scanner(this);
			scanners.add(scanner);
			scanner.start();
		}
		
		for (Scanner scanner : scanners) {
			try {
				scanner.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				
				Thread.currentThread().interrupt();
			}
		}
	}
	
	// !!!
	public synchronized HostAndPort getNextHostAndPort() {
		if(!toCheck.isEmpty()) {
			return toCheck.remove(0);
		}
		return null;
	}
	
	public synchronized void addToOpen(String host) {
		open.put(host, open.get(host) + 1);
	}
	
	public synchronized void addToClosed(String host) {
		closed.put(host, closed.get(host) + 1);
	}
	
	public synchronized void scannerFinished(Scanner scanner) {
		scanners.remove(scanner);
		if(scanners.isEmpty()) {
			for(Entry<String, Integer> entry : open.entrySet()) {
				System.out.println("OPEN: " +  entry.getKey() + " " + entry.getValue());
			}
			for(Entry<String, Integer> entry : closed.entrySet()) {
				System.out.println("CLOSED: " +  entry.getKey() + " " + entry.getValue());
			}
		}
	}
}
