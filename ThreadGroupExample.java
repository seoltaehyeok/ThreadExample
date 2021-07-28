package ThreadGroup;

public class ThreadGroupExample {

	public static void main(String[] args) {
		 //main�׷�ȿ� main�����尡 �ְ� main �����尡 �ڵ带 ����, myGroup�� main�� ���� �׷쿡 ���� 
		ThreadGroup myGroup = new ThreadGroup("myGroup");
		
		//myGroup�ȿ� workThreadA�� workThreadB �ΰ��� �����带 ����
		WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
		WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");
		//system �׷�>main�׷�>main������
		//					>myGroup(main�׷��� ����׷�)
		//							>workThreadA,workThreadB(myGroup�� ���� ������)
		
		workThreadA.start();
		workThreadB.start();
		
		//ThreadGroup�� �������
		System.out.println("[main ������ �׷��� list() �޼ҵ� ��³���]");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();//���� �������� �׷��� ����
		mainGroup.list(); // mainGrop�� ���Ե� ������� ���� �׷쿡 ���� ������ ���(12~14������ ����)
		System.out.println();
		
		//���ν����� 3�ʵ��� ����
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		
		//myGroup�� ���Ե� ��� ������(workThreadA, workThreadB)�� interrupt�� ȣ��
		System.out.println("[myGroup ������ �׷��� interrupt() �޼ҵ� ȣ��]");
		myGroup.interrupt(); // �� �׷쿡 ���Ե� ��� �����尡 interrupt�� �߻��Ͽ� �����ϰ� ����
	}
}
