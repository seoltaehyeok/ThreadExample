package wait_notify;

public class WorkObject {
	//ThreadA ThreadB가 각각 methodA, methodB를 한번씩 실행하며 반복하도록
	public synchronized void methodA() { //notify(), wait()등을 사용하기 위해선 동기화 메소드여야함
		System.out.println("ThreadA의 methodA() 작업 실행");
		notify(); //현재 wait된 다른 스레드를 실행대기로 바꿈
		
		//자기 자신은 wait를 통해 일시정지로 바꿈
		try {
			wait();
		} catch (InterruptedException e) {} 
	}
	
	public synchronized void methodB() {
		System.out.println("ThreadB의 methodB() 작업 실행");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {} 
	}
}
