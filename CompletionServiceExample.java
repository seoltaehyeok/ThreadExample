package CompletionService;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceExample {

	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(
			Runtime.getRuntime().availableProcessors()
		);
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
		System.out.println("[작업 처리 요청]");
		
		
		//CompletionService의 submit메소드를 호출해야함
		for(int i=1; i<=3; i++) {
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// 스레드가 실행할 내용
					int sum = 0;
					for(int i =1; i<=10; i++) {
						sum += i;
					}
					return sum;
				}
				
			});
		}
		
		System.out.println("[처리 완료된 작업 확인]");
		//처리완료된 작업을 왜 스레드풀에 넣어서 처리를하느냐? => take();는 블로킹이 되어서 리턴이 안되면ui작업을 할 수 없으므로 항상 다른 스레드에서 실행해야함
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {	//꼭 작업이 시작한 순서대로 결과를 가져오는 것은 아니다!
						Future<Integer> future = completionService.take(); //작업이 완료된 순서대로 작업의 결과를 리턴
						int value = future.get(); //이때 get은 블로킹이 되지 않음 take()에서 작업이 완료된 것만 가져오기때문
						System.out.println("[처리 결과] : " + value);
					} catch (Exception e) {
						break;
					}
				}	
			}
		});
		
		try { // 스레드가 작업큐에 들어가서 위의 작업을 하려면 시간이 필요하다.
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			executorService.shutdownNow(); // 3초 이후에도 처리가 되지 않으면 셧다운
		} 
	}

}
