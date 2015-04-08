package hu.kooocsing.multithread.portscanner;

public class PortScannerMain {

	public static void main(String[] args) {
		Controller controller = new Controller(5000, "index.hu");
		controller.startScanners();
	}
}
