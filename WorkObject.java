package wait_notify;

public class WorkObject {
	//ThreadA ThreadB�� ���� methodA, methodB�� �ѹ��� �����ϸ� �ݺ��ϵ���
	public synchronized void methodA() { //notify(), wait()���� ����ϱ� ���ؼ� ����ȭ �޼ҵ忩����
		System.out.println("ThreadA�� methodA() �۾� ����");
		notify(); //���� wait�� �ٸ� �����带 ������� �ٲ�
		
		//�ڱ� �ڽ��� wait�� ���� �Ͻ������� �ٲ�
		try {
			wait();
		} catch (InterruptedException e) {} 
	}
	
	public synchronized void methodB() {
		System.out.println("ThreadB�� methodB() �۾� ����");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {} 
	}
}
