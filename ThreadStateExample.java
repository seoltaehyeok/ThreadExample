package ThreadControl;

public class ThreadStateExample {

	public static void main(String[] args) {
		//��ü���� TargetThread.State: NEW
		StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
		statePrintThread.start();
		
	}

}
