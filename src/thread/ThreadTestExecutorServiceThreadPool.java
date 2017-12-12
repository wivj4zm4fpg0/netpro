package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestExecutorServiceThreadPool {
	public static void main(String[] args) {
		System.out.println("Entering main");
		final int num=5;
		ExecutorService thexec = Executors.newFixedThreadPool(num);
		try {
			for (int i = 0; i < 30; i++) {
				System.out.println("add to the pool--------------:"+i);
				thexec.execute(new Runnable() {
					public void run() {
						try {
							System.out.println("スレッド開始 : " + Thread.currentThread().getId());
							Thread.sleep(2 * 1000);
							System.out.println("スレッド終了 : " + Thread.currentThread().getId());

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				});

			}
			System.out.println("Main is Sleeping..." + Thread.currentThread().getId());
			Thread.sleep(15 * 1000);
			System.exit(1);//強制終了
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Exit main");
	}

}
