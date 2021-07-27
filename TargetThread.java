package ThreadControl;

public class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=0; i<1000000000; i++) {} // TargetThread.State: RUNNABLE
			
			//TIMED_WITING(일시정지): 주어진 시간 동안 기다리는 상태(Sleep 메소드 사용)
			try {
				Thread.sleep(1500);	//TargetThread.State: TIMED_WAITING
			} catch (InterruptedException e) {}
			
			for(long i=0; i<1000000000; i++) {}	//TargetThread.State: RUNNABLE
	} //run()메소드 종료이후 스레드 종료=> TargetThread.State: TERMINATED 
} 
