package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteVsSubmitExample {

	public static void main(String[] args) throws Exception { //Thread.sleep(10); 에 대한 예외처리 (28라인)
		ExecutorService executorService = Executors.newFixedThreadPool(2); //총 스레드 수 2
		
		for(int i=0; i<10; i++) { // 열번의 작업객체생성 -> 열번의 작업큐에 넣어줌
			//작업객체 생성
			Runnable runnable = new Runnable() { // Runnable 객체: 작업 처리 완료 후 리턴값x
				@Override
				public void run() { //작업 내용
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService; //강제타입변환
					int poolSize = threadPoolExecutor.getPoolSize(); //현재 스레드풀에있는 스레드 수
					String threadName = Thread.currentThread().getName(); // 현재 스레드 이름 얻기
					System.out.println("[총 스레드 수: " + poolSize + "] 작업 스레드 이름: " + threadName);
					int value = Integer.parseInt("삼"); // 고의적인 오류를 통해
					
				}
			}; 
			//작업큐에 작업객체(runnable)넣기	
			executorService.execute(runnable); //execute의 경우 예외발생시 스레드종료 후 새 스레드로 새 작업실행 
			executorService.submit(runnable); //submit의 경우 예외발생시 스레드종료x 같은 스레드로 다음작업실행
			Thread.sleep(10); // 예외발생 전에 다음for문이 돌기위해 방지
		}
			
		
		executorService.shutdown(); //작업큐에 있는 작업을 모두처리 후 스레드풀 종료
	}	
}
