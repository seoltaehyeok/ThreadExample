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
		System.out.println("[�۾� ó�� ��û]");
		
		
		//CompletionService�� submit�޼ҵ带 ȣ���ؾ���
		for(int i=1; i<=3; i++) {
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					// �����尡 ������ ����
					int sum = 0;
					for(int i =1; i<=10; i++) {
						sum += i;
					}
					return sum;
				}
				
			});
		}
		
		System.out.println("[ó�� �Ϸ�� �۾� Ȯ��]");
		//ó���Ϸ�� �۾��� �� ������Ǯ�� �־ ó�����ϴ���? => take();�� ���ŷ�� �Ǿ ������ �ȵǸ�ui�۾��� �� �� �����Ƿ� �׻� �ٸ� �����忡�� �����ؾ���
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				while(true) {
					try {	//�� �۾��� ������ ������� ����� �������� ���� �ƴϴ�!
						Future<Integer> future = completionService.take(); //�۾��� �Ϸ�� ������� �۾��� ����� ����
						int value = future.get(); //�̶� get�� ���ŷ�� ���� ���� take()���� �۾��� �Ϸ�� �͸� �������⶧��
						System.out.println("[ó�� ���] : " + value);
					} catch (Exception e) {
						break;
					}
				}	
			}
		});
		
		try { // �����尡 �۾�ť�� ���� ���� �۾��� �Ϸ��� �ð��� �ʿ��ϴ�.
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			executorService.shutdownNow(); // 3�� ���Ŀ��� ó���� ���� ������ �˴ٿ�
		} 
	}

}
