package ThreadPool_Blocking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() // ���� ��ǻ�� CPU�� �ھ��� ��
		);
		
		//�۾�ó����û
		System.out.println("[�۾� ó�� ��û]");
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				int sum = 0;
				for(int i=1; i<=10; i++) {
					sum += i;
				}
				System.out.println("[ó�����] : " + sum);
			}
			
		};
		
		//ThreadPool�� �۾�ť�� ����
		//Future: �۾� ����� �ƴ϶� ���� �Ϸ�(pending completion) ��ü
		Future future = executorService.submit(runnable); //run()�޼ҵ忡 ���ϰ��� �����Ƿ� submit(runnable) ���
		
		try {
			future.get(); //runnable�� ��� �۾��� ������ ���� ����ߴٰ� ������ ���ϰ��� �����Ƿ� null���� ����
			System.out.println("[�۾� ó�� �Ϸ�]");
		} catch (Exception e) {
			System.out.println("[���ܹ߻���] : " + e.getMessage());
		}
		
		executorService.shutdown();
	}

}
