package ThreadJoin;

public class JoinExample {

	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		
		//sumThread�� ����ɶ����� mainThread�� �Ͻ�����
		try {
			sumThread.join();
		} catch (InterruptedException e) {}
		
		System.out.println("1~100 ��: " + sumThread.getSum());

	}

}
