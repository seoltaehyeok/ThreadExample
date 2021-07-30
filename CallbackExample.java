package Callback;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
	//CallbackExample의 필드 정의
	private ExecutorService executorService;
	
	//생성자 생성
	public CallbackExample() {
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
	}
	
	//Callback 객체 (스레드가 작업이 끝나게 되면 호출할 수 있는 콜백 메소드를 가진 콜백객체)
	private CompletionHandler<Integer, Void> callback =  //<스레드의 결과값, 첨부객체(사용하지 않으면 Void)>
			new CompletionHandler<Integer, Void>() {	 // 익명 객체 생성

				@Override
				public void completed(Integer result, Void attachment) {
					System.out.println("completed() 실행: " + result);
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					System.out.println("failed() 실행: " + exc.toString());
				}
	};
	
	//스레드풀의 작업큐에 작업을 넣기 위한 메소드
	public void doWork(String x, String y) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					//스레드가 작업할 코드(매개값(x,y)을 받아서 정수화 하고 두개를 더해서 산출)
					int intX = Integer.parseInt(x);
					int intY = Integer.parseInt(y);
					int result = intX + intY;
					callback.completed(result, null);	//결과값 호출
				} catch(NumberFormatException e) {
					callback.failed(e, null); 			//매개값(String x, y)이 정수화가 불가능한 문자인 경우 예외처리
				}
			}
		};
		//스레드풀의 작업큐에 넣기
		executorService.submit(task);
	}
		public void finish() {			//스레드풀 작업이 끝나면 종료하기
			executorService.shutdown();
		}
	public static void main(String[] args) {
		CallbackExample example = new CallbackExample();
		example.doWork("3", "3"); // 정상작동 : completed
		example.doWork("3", "삼"); // 예외처리 : failed
		example.finish();
	}

}
