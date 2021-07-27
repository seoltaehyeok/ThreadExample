package ThreadJoin;

public class JoinExample {

	public static void main(String[] args) {
		SumThread sumThread = new SumThread();
		sumThread.start();
		
		//sumThread가 종료될때까지 mainThread는 일시정지
		try {
			sumThread.join();
		} catch (InterruptedException e) {}
		
		System.out.println("1~100 합: " + sumThread.getSum());

	}

}
