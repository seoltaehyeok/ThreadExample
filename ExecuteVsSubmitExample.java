package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteVsSubmitExample {

	public static void main(String[] args) throws Exception { //Thread.sleep(10); �� ���� ����ó�� (28����)
		ExecutorService executorService = Executors.newFixedThreadPool(2); //�� ������ �� 2
		
		for(int i=0; i<10; i++) { // ������ �۾���ü���� -> ������ �۾�ť�� �־���
			//�۾���ü ����
			Runnable runnable = new Runnable() { // Runnable ��ü: �۾� ó�� �Ϸ� �� ���ϰ�x
				@Override
				public void run() { //�۾� ����
					ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService; //����Ÿ�Ժ�ȯ
					int poolSize = threadPoolExecutor.getPoolSize(); //���� ������Ǯ���ִ� ������ ��
					String threadName = Thread.currentThread().getName(); // ���� ������ �̸� ���
					System.out.println("[�� ������ ��: " + poolSize + "] �۾� ������ �̸�: " + threadName);
					int value = Integer.parseInt("��"); // �������� ������ ����
					
				}
			}; 
			//�۾�ť�� �۾���ü(runnable)�ֱ�	
			executorService.execute(runnable); //execute�� ��� ���ܹ߻��� ���������� �� �� ������� �� �۾����� 
			executorService.submit(runnable); //submit�� ��� ���ܹ߻��� ����������x ���� ������� �����۾�����
			Thread.sleep(10); // ���ܹ߻� ���� ����for���� �������� ����
		}
			
		
		executorService.shutdown(); //�۾�ť�� �ִ� �۾��� ���ó�� �� ������Ǯ ����
	}	
}
