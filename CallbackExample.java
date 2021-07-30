package Callback;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
	//CallbackExample�� �ʵ� ����
	private ExecutorService executorService;
	
	//������ ����
	public CallbackExample() {
		executorService = Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()
		);
	}
	
	//Callback ��ü (�����尡 �۾��� ������ �Ǹ� ȣ���� �� �ִ� �ݹ� �޼ҵ带 ���� �ݹ鰴ü)
	private CompletionHandler<Integer, Void> callback =  //<�������� �����, ÷�ΰ�ü(������� ������ Void)>
			new CompletionHandler<Integer, Void>() {	 // �͸� ��ü ����

				@Override
				public void completed(Integer result, Void attachment) {
					System.out.println("completed() ����: " + result);
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					System.out.println("failed() ����: " + exc.toString());
				}
	};
	
	//������Ǯ�� �۾�ť�� �۾��� �ֱ� ���� �޼ҵ�
	public void doWork(String x, String y) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					//�����尡 �۾��� �ڵ�(�Ű���(x,y)�� �޾Ƽ� ����ȭ �ϰ� �ΰ��� ���ؼ� ����)
					int intX = Integer.parseInt(x);
					int intY = Integer.parseInt(y);
					int result = intX + intY;
					callback.completed(result, null);	//����� ȣ��
				} catch(NumberFormatException e) {
					callback.failed(e, null); 			//�Ű���(String x, y)�� ����ȭ�� �Ұ����� ������ ��� ����ó��
				}
			}
		};
		//������Ǯ�� �۾�ť�� �ֱ�
		executorService.submit(task);
	}
		public void finish() {			//������Ǯ �۾��� ������ �����ϱ�
			executorService.shutdown();
		}
	public static void main(String[] args) {
		CallbackExample example = new CallbackExample();
		example.doWork("3", "3"); // �����۵� : completed
		example.doWork("3", "��"); // ����ó�� : failed
		example.finish();
	}

}
