package ThreadControl;

public class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=0; i<1000000000; i++) {} // TargetThread.State: RUNNABLE
			
			//TIMED_WITING(�Ͻ�����): �־��� �ð� ���� ��ٸ��� ����(Sleep �޼ҵ� ���)
			try {
				Thread.sleep(1500);	//TargetThread.State: TIMED_WAITING
			} catch (InterruptedException e) {}
			
			for(long i=0; i<1000000000; i++) {}	//TargetThread.State: RUNNABLE
	} //run()�޼ҵ� �������� ������ ����=> TargetThread.State: TERMINATED 
} 
