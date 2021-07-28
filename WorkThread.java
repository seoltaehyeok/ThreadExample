package ThreadGroup;

public class WorkThread extends Thread{
	//�ܺηκ��� ������׷��� �ް� �������̸��� ����
	public WorkThread(ThreadGroup threadGroup, String threadName) {
		super(threadGroup, threadName); //���� ������ ������ ȣ�� =>WorkThread�� ThreadGroup�� ���Եǰ� ������ �̸��� threadName�� ��
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(getName() + " interrupted");
				break;
			}
		}
		System.out.println(getName() + " �����");
	}
}
