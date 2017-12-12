package thread;

public class CountTesterTweThreads {

	public static void main(String[] args){
		CounterRunnable ct = new CounterRunnable(50, "A", 500); //CounterRunnableクラスのインスタンスとして
												  // ctを作る
		Thread th = new Thread(ct);  // thというスレッドを作る。thはThreadクラスのインスタンス
		th.start();   // thを起動する

		CounterRunnable ct2 = new CounterRunnable(100, "B", 1000);
		Thread th2 = new Thread(ct2);
		th2.start();

		try {
			for(int i = 0; i < 100; i++) {
				System.out.println("main:i=" + i);
				Thread.sleep(600);  // ()の中はミリ秒
			}
		}
		catch(InterruptedException e) {
			System.err.println(e);
		}
	}
}


class CounterRunnable implements Runnable {
	// implements Runnable をすることで、スレッドが使える
	// 「Runnableを実装する」という言い方をする
	int sleeping;
	String s;
	int loop; 

	CounterRunnable(int sleeping, String s, int loop){
		this.sleeping = sleeping;
		this.s = s;
		this.loop = loop;
	}


	public void run() {
		try {
			for(int i = 0; i < loop; i++) {
				System.out.println(s + ":i=" + i);
				Thread.sleep(sleeping);  // ()の中はミリ秒
			}
		}
		catch(InterruptedException e) {
			System.err.println(e);
		}
		
	}

}

