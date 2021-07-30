package ThreadPool_Blocking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() // 현재 컴퓨터 CPU의 코어의 수
		);
		
		//작업처리요청
		System.out.println("[작업 처리 요청]");
		Callable<Integer> task = new Callable<Integer>() {
			@Override
			public Integer call() { // call()은 반드시 Callable에서 지정한 타입 파라미터와 동일해야함
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				return sum;
			}			
		};
		
		//ThreadPool의 작업큐에 저장
		//Future 객체는 나중에 get() 메소드로 얻을 수 있는 결과값이 Integer이므로 Future 또한 타입 파라미터<Integer> 지정
		Future<Integer> future = executorService.submit(task); //submit은 리턴값 또한 받을 수 있음
		
		try {
			int sum = future.get();  // future.get()은 위의 call() 메소드에서 리턴 된 sum 값을 받음
			System.out.println("[처리 결과] : " + sum);
			System.out.println("[작업 처리 완료]");
		} catch (Exception e) {
			System.out.println("[예외발생함] : " + e.getMessage());
		}
		executorService.shutdown();
	}

}
