package wait_notify3;

public class WaitNotifyExample {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox(); // ������ü����
		
		ProducerThread producerThread = new ProducerThread(dataBox); //������ ������ ��ü����
		ConsumerThread consumerThread = new ConsumerThread(dataBox); //�Һ��� ������ ��ü����
		
		producerThread.start();
		consumerThread.start();
		
		
	}

}
