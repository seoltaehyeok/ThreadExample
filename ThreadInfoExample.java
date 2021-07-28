package ThreadGroup;

import java.util.Map;
import java.util.Set;

public class ThreadInfoExample {

	public static void main(String[] args) {
		//AutoSaveThread�� ���ν����忡�� �����߱� ������ ���ν������ ������ ������ �׷쿡 ����
		AutoSaveThread autoSaveThread = new AutoSaveThread(); 
		autoSaveThread.setName("AutoSaveThread");
		autoSaveThread.setDaemon(true);
		autoSaveThread.start();
		
		//��� �����忡 ���� ��ü ��� (Map�� Set�� �÷��� �����ӿ��� ��￹��)
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		Set<Thread> threads = map.keySet(); 
		for(Thread thread : threads) {
			System.out.println("Name: " + thread.getName() + ((thread.isDaemon())?"(����)":"(��)"));
			
			//� ������׷����� ���� (�̸� ���)
			System.out.println("\t" + "�Ҽӱ׷�: " + thread.getThreadGroup().getName());
			System.out.println();
		}

	}

}
