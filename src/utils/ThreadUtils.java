package utils;

public class ThreadUtils {
	
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void allThread() {
		int count = Thread.activeCount();
		Thread[] threads = new Thread[count];
		int i = Thread.currentThread().getThreadGroup().enumerate(threads);
		System.err.println(i);
		for (Thread thread : threads) {
			System.err.println(thread.getName());
		}
	}
	
	public static void main(String[] args) {
		allThread();
	}
	
}
