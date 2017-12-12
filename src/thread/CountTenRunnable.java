package thread;

public class CountTenRunnable implements Runnable {
	// implements Runnable をすることで、スレッドが使える
	// 「Runnableを実装する」という言い方をする

	public static void main(String[] args){
		char c1=97;
		char c2=(char)(c1+1);
		System.out.println(c1);
		System.out.println(c2);

		CountTenRunnable ct = new CountTenRunnable(); //CountTenBクラスのインスタンスとして
												  // ctを作る
		Thread th = new Thread(ct);
		th.start();

		try {
			for(int i = 0; i < 10; i++) {
				System.out.println("main:i=" + i);
				Thread.sleep(500);  // ()の中はミリ秒
			}
		}
		catch(InterruptedException e) {
			System.err.println(e);
		}
	}

	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				System.out.println("runnable thread:i=" + i);
				Thread.sleep(1000);  // ()の中はミリ秒
			}
		}
		catch(InterruptedException e) {
			System.err.println(e);
		}
		
	}

}

