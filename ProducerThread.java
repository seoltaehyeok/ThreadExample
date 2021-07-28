package wait_notify3;

public class ProducerThread extends Thread{
	private DataBox dataBox;
	
	//»ý¼ºÀÚ
	public ProducerThread(DataBox dataBox) {
	this.setName("ProducerThread");
	this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++) {
			String data = "data - " + i;
			dataBox.setData(data);
		}
	}
}
