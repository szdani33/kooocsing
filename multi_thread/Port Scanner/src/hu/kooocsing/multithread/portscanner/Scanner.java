package hu.kooocsing.multithread.portscanner;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Scanner extends Thread {
	private Controller controller;
	
	public Scanner(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while(true) {
			HostAndPort hap = controller.getNextHostAndPort();
			if(hap != null) {
				try {
					System.out.println(getName() + " checking: " + hap.getHost() + ":" + hap.getPort());
					new Socket(hap.getHost(), hap.getPort());
					controller.addToOpen(hap.getHost());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					controller.addToClosed(hap.getHost());
				}
			} else {
				break;
			}
		}
		controller.scannerFinished(this);
	}
}
