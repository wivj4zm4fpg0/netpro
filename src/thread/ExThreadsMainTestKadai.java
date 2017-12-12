package thread;

public class ExThreadsMainTestKadai implements  ICallBackListener{

	/*
	Step1本クラスからインナーThreadクラスのインスタンスを5つ生成しそれぞれ一秒ごとに
	1秒毎に1からnまでカウントするプログラムを作成させよ。
	setThreadName(String tname,int n)でスレッド名とカウント秒数を与えるようにせよ。
	Step2:ExThreadsMainTestにICallBackListenerインタフェースを実装して
	ThreadからのCallBackによりどのスレッドがおわったかを本クラス内にコールバックさせ表示させよ。
	ThreadにsetCallBackListener(listener)を実装すること
	*/
	public static void main(String[] args){
		new ExThreadsMainTestKadai();

	}

	ExThreadsMainTestKadai(){

	}


	@Override
	public void printFinish(String tname) {

	}

	class InnerThread extends Thread{

	}





}//outerclass end
