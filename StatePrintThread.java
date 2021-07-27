package ThreadControl;
//TargetThread의 상태를 지속적으로 출력
public class StatePrintThread extends Thread{
	private Thread targetThread;
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			Thread.State state = targetThread.getState();
			System.out.println("타켓 스레드 상태: " + state);
			
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED) {
				break; //=>TargetThread의 run()메소드가 종료됨에 따라 state값은 TERMINATED가 되고
			}		//위의 if(state == Thread.State.TERMINATED){break;}에 걸리므로 while문 탈출
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			}
	}
} 
