package wait_notify3;

public class WaitNotifyExample {

	public static void main(String[] args) {
		DataBox dataBox = new DataBox(); // 공유객체생성
		
		ProducerThread producerThread = new ProducerThread(dataBox); //생성자 스레드 객체생성
		ConsumerThread consumerThread = new ConsumerThread(dataBox); //소비자 스레드 객체생성
		
		producerThread.start();
		consumerThread.start();
		
		
	}

}
