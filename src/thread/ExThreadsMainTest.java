package thread;

public class ExThreadsMainTest  implements  ICallBackListener{
	
	//Step1本クラスからインナーThreadクラスを5つ生成しそれぞれ一秒ごとに
	//1秒毎に1からnまでカウントするプログラムを作成させよ。
	//setThreadName(String tname,int n)でスレッド名とカウント秒数を与えるようにせよ。
	//Step2:ExThreadsMainTestにICallBackListenerインタフェースを実装して 
	//ThreadからのCallBackによりどのスレッドがおわったかを本クラス内で表示させよ。
	//ThreadにsetCallBackListener(listener)を実装すること
	public static void main(String[] args){
		new ExThreadsMainTest();

	}
	
	ExThreadsMainTest(){

		InnerThread in1= new InnerThread((ICallBackListener)this);
		in1.setThreadName("初号機",5);
		in1.start();

		InnerThread in2= new InnerThread((ICallBackListener)this);
		in2.setThreadName("弐号機",10);
		in2.start();
	}


	@Override
	public void printFinish(String tname) {
		System.out.println(tname+" が帰艦しました！");

	}

	class InnerThread extends Thread{
		private int n=10;
		private String tname="";
		ICallBackListener listener;

		InnerThread(ICallBackListener listener){
			this.listener=listener;
		}

		InnerThread(){

		}

		public void setICallBackListener(ICallBackListener listener){
			this.listener=listener;
		}
		public void setThreadName(String tname,int n){
			this.tname=tname;
			this.n=n;
		}

		public void run(){
			for(int i=0;i<this.n;i++){

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(tname+":countdown"+(n-i));
			}
			listener.printFinish(tname);
		}

	}





}//outerclass end
