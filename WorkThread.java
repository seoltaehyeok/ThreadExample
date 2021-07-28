package ThreadGroup;

public class WorkThread extends Thread{
	//외부로부터 스레드그룹을 받고 스레드이름을 받음
	public WorkThread(ThreadGroup threadGroup, String threadName) {
		super(threadGroup, threadName); //상위 스레드 생성자 호출 =>WorkThread는 ThreadGroup에 포함되고 스레드 이름은 threadName이 됨
		
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
		System.out.println(getName() + " 종료됨");
	}
}
