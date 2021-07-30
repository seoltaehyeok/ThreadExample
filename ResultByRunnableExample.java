package ThreadPool_Blocking;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ResultByRunnableExample {

	public static void main(String[] args) {
		//������Ǯ ���� (���� ��ǻ�� CPU�� �ھ��� ��)
		ExecutorService executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors() 
		);
		
		System.out.println("[�۾� ó�� ��û]");		
		/********************�ϳ��� �۾� Ŭ���� ����**************************/
		class Task implements Runnable {  // Runnable�� �������̽�
			// �ܺ� ������ü�� ������ ������ �� �ֵ��� Result Ÿ���� ����
			Result result;
			
			//result��ü�� �ܺο��� �޾Ƽ� �ʵ� �ʱ�ȭ
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
		
		//���� �۾��� ���� ������Ǯ�� ó���ϵ���
		Result result = new Result(); // ������ü(�ܺΰ�ü)
		//�� ���� �۾��� ����(task�� ���� �ΰ��� �ν��Ͻ� ����)
		Runnable task1 = new Task(result);
		Runnable task2 = new Task(result); 
		// =>�Ȱ��� TaskŬ������ ���� ��ü2���� �����ϰ� result �ϳ��� ������ü�� �� task��ü�� ����
		
		//task1,2�� �۾�ť�� �־��ֱ�
		Future<Result> future1 = executorService.submit(task1, result); //submit(�۾���ü, ������ü) submit�� result�� ����
		Future<Result> future2 = executorService.submit(task2, result); //submit(�۾���ü, ������ü) submit�� result�� ����
	
		try {
			result = future1.get(); //task1�� �Ϸ�� ������ ��ٸ� �� �Ϸ�Ǹ� result ��ü�� ���Ϲ���
			result = future2.get(); //task2�� �Ϸ�� ������ ��ٸ� �� �Ϸ�Ǹ� result ��ü�� ���Ϲ���
			System.out.println("[ó�� ���] : " + result.accumValue);
			System.out.println("[�۾� ó�� �Ϸ�]");
		} catch (Exception e){
			System.out.println("[���� ���ܰ� �߻���] : " + e.getMessage());
		}
	}

}

//������ü Ŭ����
class Result {
	int accumValue; // ���� �������ֱ� ���� �ʵ����
	synchronized void addValue(int value) { // �ϳ��� �����尡 �ʵ忡 ������ ���� �ٸ� ������� ������� ���ϵ��� ����ȭ���
		accumValue += value;
	}
	
}
