package yieldThread;

public class ThreadA extends Thread{
	public boolean stop = false; //������ �������
	public boolean work = true; //yield �޼ҵ� ȣ������� �ľ�
	
	@Override
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println("ThreadA �۾�����");
			} else {
				Thread.yield();
			}
		}
		System.out.println("ThreadA ����");
	}
}
