package ThreadPool_Blocking;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableExample {

	public static void main(String[] args) {
		//스레드풀 생성 (현재 컴퓨터 CPU의 코어의 수)
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() 
		);
		
		System.out.println("[작업 처리 요청]");		
		/********************하나의 작업 클래스 생성**************************/
		class Task implements Runnable {  // Runnable이 인터페이스
			// 외부 공유객체의 참조를 저장할 수 있도록 Result 타입을 만듦
			Result result;
			
			//result객체를 외부에서 받아서 필드 초기화
			Task(Result result) {
				this.result = result;
			}
			
			@Override
			public void run() {
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				result.addValue(sum);
			}
			
		};
		/****************************************************************/
		
		//실제 작업을 만들어서 스레드풀에 처리하도록
		Result result = new Result(); // 공유객체(외부객체)
		//두 개의 작업을 정의(task에 대한 두개의 인스턴스 생성)
		Runnable task1 = new Task(result);
		Runnable task2 = new Task(result); 
		// =>똑같은 Task클래스로 부터 객체2개를 생성하고 result 하나의 공유객체를 두 task객체가 공유
		
		//task1,2를 작업큐에 넣어주기
		Future<Result> future1 = executorService.submit(task1, result); //submit(작업객체, 공유객체) submit은 result를 리턴
		Future<Result> future2 = executorService.submit(task2, result); //submit(작업객체, 공유객체) submit은 result를 리턴
	
		try {
			result = future1.get(); //task1이 완료될 때까지 기다린 후 완료되면 result 객체로 리턴받음
			result = future2.get(); //task2가 완료될 때까지 기다린 후 완료되면 result 객체로 리턴받음
			System.out.println("[처리 결과] : " + result.accumValue);
			System.out.println("[작업 처리 완료]");
		} catch (Exception e){
			System.out.println("[실행 예외가 발생함] : " + e.getMessage());
		}
	}

}

//공유객체 클래스
class Result {
	int accumValue; // 값을 누적해주기 위한 필드생성
	synchronized void addValue(int value) { // 하나의 스레드가 필드에 누적할 동안 다른 스레드는 사용하지 못하도록 동기화사용
		accumValue += value;
	}
	
}
