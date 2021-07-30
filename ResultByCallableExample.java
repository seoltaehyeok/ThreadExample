package ThreadPool_Blocking;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByCallableExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() // ���� ��ǻ�� CPU�� �ھ��� ��
		);
		
		//�۾�ó����û
		System.out.println("[�۾� ó�� ��û]");
		Callable<Integer> task = new Callable<Integer>() {
			@Override
			public Integer call() { // call()�� �ݵ�� Callable���� ������ Ÿ�� �Ķ���Ϳ� �����ؾ���
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				return sum;
			}			
		};
		
		//ThreadPool�� �۾�ť�� ����
		//Future ��ü�� ���߿� get() �޼ҵ�� ���� �� �ִ� ������� Integer�̹Ƿ� Future ���� Ÿ�� �Ķ����<Integer> ����
		Future<Integer> future = executorService.submit(task); //submit�� ���ϰ� ���� ���� �� ����
		
		try {
			int sum = future.get();  // future.get()�� ���� call() �޼ҵ忡�� ���� �� sum ���� ����
			System.out.println("[ó�� ���] : " + sum);
			System.out.println("[�۾� ó�� �Ϸ�]");
		} catch (Exception e) {
			System.out.println("[���ܹ߻���] : " + e.getMessage());
		}
		executorService.shutdown();
	}

}
