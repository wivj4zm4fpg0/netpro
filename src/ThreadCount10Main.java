import thread.ICallBackListener;

public class ThreadCount10Main implements ICallBackListener {
    //Step1本クラスからインナーThreadクラスを5つ生成しそれぞれ一秒ごとに
    //1秒毎に1からnまでカウントするプログラムを作成さよ。
    //setThreadName(String tname,int n)でスレッド名とカウント秒数を与えるようにせよ。
    //Step2:ExThreadsMainTestにICallBackListenerインタフェースを実装して
    //ThreadからのCallBackによりどのスレッドがおわったかを本クラス内で表示させよ。
    //ThreadにsetCallBackListener(ICallBackListener listener)を実装すること

    public static void main(String[] args) {
        new ThreadCount10Main();
    }

    private ThreadCount10Main() {
        CountTenRunnableImpleC ct[] = new CountTenRunnableImpleC[10];
        for (int i = 0; i < ct.length; i++) {
            ct[i] = new CountTenRunnableImpleC("th-" + (i + 1), (i + 1));
            ct[i].setCallBackListener(this);
            Thread th = new Thread(ct[i]);
            th.start();
        }
    }

    @Override
    public void printFinish(String tname) {
        System.out.println("Finish->" + tname);
    }
}

