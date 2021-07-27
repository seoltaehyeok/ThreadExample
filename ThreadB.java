package yieldThread;

public class ThreadB extends Thread{
	public boolean stop = false; //스레드 종료목적
	public boolean work = true; //yield 메소드 호출시점을 파악
	
	@Override
	public void run() {
		while(!stop) {
			if(work) {
				System.out.println("ThreadB 작업내용");
			} else {
				Thread.yield();
			}
		}
		System.out.println("ThreadB 종료");
	}
}
