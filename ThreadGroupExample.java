package ThreadGroup;

public class ThreadGroupExample {

	public static void main(String[] args) {
		 //main그룹안에 main스레드가 있고 main 스레드가 코드를 실행, myGroup은 main의 하위 그룹에 생성 
		ThreadGroup myGroup = new ThreadGroup("myGroup");
		
		//myGroup안에 workThreadA와 workThreadB 두개의 스레드를 포함
		WorkThread workThreadA = new WorkThread(myGroup, "workThreadA");
		WorkThread workThreadB = new WorkThread(myGroup, "workThreadB");
		//system 그룹>main그룹>main스레드
		//					>myGroup(main그룹의 서브그룹)
		//							>workThreadA,workThreadB(myGroup의 하위 스레드)
		
		workThreadA.start();
		workThreadB.start();
		
		//ThreadGroup의 상태출력
		System.out.println("[main 스레드 그룹의 list() 메소드 출력내용]");
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();//현재 스레드의 그룹을 리턴
		mainGroup.list(); // mainGrop에 포함된 스레드와 하위 그룹에 대한 정보를 출력(12~14라인의 내용)
		System.out.println();
		
		//메인스레드 3초동안 정지
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		
		//myGroup에 포함된 모든 스레드(workThreadA, workThreadB)의 interrupt를 호출
		System.out.println("[myGroup 스레드 그룹의 interrupt() 메소드 호출]");
		myGroup.interrupt(); // 이 그룹에 포함된 모든 스레드가 interrupt가 발생하여 안전하게 종료
	}
}
