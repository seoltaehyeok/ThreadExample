package ThreadGroup;

import java.util.Map;
import java.util.Set;

public class ThreadInfoExample {

	public static void main(String[] args) {
		//AutoSaveThread를 메인스레드에서 생성했기 때문에 메인스레드와 동일한 스레드 그룹에 포함
		AutoSaveThread autoSaveThread = new AutoSaveThread(); 
		autoSaveThread.setName("AutoSaveThread");
		autoSaveThread.setDaemon(true);
		autoSaveThread.start();
		
		//모든 스레드에 대한 객체 얻기 (Map과 Set은 컬렉션 프레임에서 배울예정)
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
		Set<Thread> threads = map.keySet(); 
		for(Thread thread : threads) {
			System.out.println("Name: " + thread.getName() + ((thread.isDaemon())?"(데몬)":"(주)"));
			
			//어떤 스레드그룹인지 조사 (이름 얻기)
			System.out.println("\t" + "소속그룹: " + thread.getThreadGroup().getName());
			System.out.println();
		}

	}

}
