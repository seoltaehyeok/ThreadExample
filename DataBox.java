package wait_notify3;

public class DataBox {
	private String data;

	public synchronized String getData() {
	if(this.data == null) {
		try {
			wait();
		} catch (InterruptedException e) {}
	}
	//null�� �ƴ϶��(������ �����尡 �����ΰ� data�� ���� �־��־������)
	String returnValue = data;
	System.out.println("ConsumerThread�� ���� ������: " + returnValue);
	data = null; // �����͸� �о����Ƿ� �����͸� �ٽ� null������ ����
	notify(); // ������ �����带 ������� �ٲپ���(�����͸� ���� ä�� �� �ֵ���)
	
		return returnValue; //ConsumerThread���� ����
	}

	public synchronized void setData(String data) {
		if(this.data !=null) {//data�� null�� �ƴҰ�쿡�� �����ڽ����尡 �ƣ����� �����͸� �־��ָ� �ȵ�
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//data�ʵ尡 null�̶��=> �Һ��ڽ����尡 �����͸� �а� null�� �ٲپ��ٸ�
		this.data = data;
		System.out.println("ProducerThread�� ������ ������: " + data);
		notify(); // ���ο� �����͸� ���������Ƿ� �Һ��ڽ����尡 wait()���¿��� ��������·� �ٲپ���
	}
}
