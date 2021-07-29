package ThreadPool_Blocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() // 현재 컴퓨터 CPU의 코어의 수
		);
		
		//작업처리요청
		System.out.println("[작업 처리 요청]");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				System.out.println("[처리결과] : " + sum);
			}
			
		};
		
		//ThreadPool의 작업큐에 저장
		//Future: 작업 결과가 아니라 지연 완료(pending completion) 객체
		Future future = executorService.submit(runnable); //run()메소드에 리턴값이 없으므로 submit(runnable) 사용
		
		try {
			future.get(); //runnable의 경우 작업이 끝날때 까지 대기했다가 끝나면 리턴값이 없으므로 null값을 받음
			System.out.println("[작업 처리 완료]");
		} catch (Exception e) {
			System.out.println("[예외발생함] : " + e.getMessage());
		}
		
		executorService.shutdown();
	}

}
