package ThreadControl;
//TargetThread�� ���¸� ���������� ���
public class StatePrintThread extends Thread{
	private Thread targetThread;
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			Thread.State state = targetThread.getState();
			System.out.println("Ÿ�� ������ ����: " + state);
			
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			if(state == Thread.State.TERMINATED) {
				break; //=>TargetThread�� run()�޼ҵ尡 ����ʿ� ���� state���� TERMINATED�� �ǰ�
			}		//���� if(state == Thread.State.TERMINATED){break;}�� �ɸ��Ƿ� while�� Ż��
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			}
	}
} 
