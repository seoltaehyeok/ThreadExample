package wait_notify3;

public class DataBox {
	private String data;

	public synchronized String getData() {
	if(this.data == null) {
		try {
			wait();
		} catch (InterruptedException e) {}
	}
	//null이 아니라면(생성자 스레드가 무엇인가 data에 값을 넣어주었더라면)
	String returnValue = data;
	System.out.println("ConsumerThread가 읽은 데이터: " + returnValue);
	data = null; // 데이터를 읽었으므로 데이터를 다시 null값으로 변경
	notify(); // 생성자 스레드를 실행대기로 바꾸어줌(데이터를 새로 채울 수 있도록)
	
		return returnValue; //ConsumerThread에게 리턴
	}

	public synchronized void setData(String data) {
		if(this.data !=null) {//data가 null이 아닐경우에는 생성자스레드가 아짂까지 데이터를 넣어주면 안됨
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//data필드가 null이라면=> 소비자스레드가 데이터를 읽고 null로 바꾸었다면
		this.data = data;
		System.out.println("ProducerThread가 생성한 데이터: " + data);
		notify(); // 새로운 데이터를 저장했으므로 소비자스레드가 wait()상태에서 실행대기상태로 바꾸어줌
	}
}
