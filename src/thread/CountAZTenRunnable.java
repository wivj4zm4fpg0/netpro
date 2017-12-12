package thread;

public class CountAZTenRunnable implements Runnable {
	// implements Runnable をすることで、スレッドが使える
	// 「Runnableを実装する」という言い方をする

	private char c;
	private int d=1000;

	public static void main(String[] args){
		char c1=97;
		char c2=(char)(c1+1);
		char c3=(char)(c2+1);

		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		/*
		CountAZTenRunnable ct1 = new CountAZTenRunnable(); //CountTenBクラスのインスタンスとして
		ct1.setChar(c1);
		CountAZTenRunnable ct2 = new CountAZTenRunnable(); //CountTenBクラスのインスタンスとして
		ct2.setChar(c2);
		CountAZTenRunnable ct3 = new CountAZTenRunnable(); //CountTenBクラスのインスタンスとして
		ct3.setChar(c3);
*/
		CountAZTenRunnable[] cts = new CountAZTenRunnable[26];
		//int[] x = new int[10];
		char c_a=97;
		for(int i=0;i<cts.length;i++){
			char cx=(char)(c_a+i);
			cts[i] = new CountAZTenRunnable(); //CountTenBクラスのインスタンスとして
			cts[i].setChar(cx);
			cts[i].setDuration(100+i*10);

		}
		for(int i=0;i<cts.length;i++) {
			//Thread th = new Thread(cts[i],"th-"+i);
			//th.start();
			new Thread(cts[i],"th-"+i).start();

		}

		/*
		Thread th1 = new Thread(ct1,"th-1");
		th1.start();

		Thread th2 = new Thread(ct2,"th-2");
		th2.start();

		Thread th3 = new Thread(ct3,"th-3");
		th3.start();
        */


	}

	public void setChar(char c){
		this.c=c;
	}
	public void setDuration(int d){
		this.d=d;
	}
	public void run() {
		try {
			for(int i = 1; i <= 10; i++) {
				System.out.println("runnable thread:"+c+ i);
				Thread.sleep(d);  // ()の中はミリ秒
			}
		}
		catch(InterruptedException e) {
			System.err.println(e);
		}
		
	}

}

