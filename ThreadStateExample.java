package ThreadControl;

public class ThreadStateExample {

	public static void main(String[] args) {
		//°´Ã¼»ý¼º TargetThread.State: NEW
		StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
		statePrintThread.start();
		
	}

}
