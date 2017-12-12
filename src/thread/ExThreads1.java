package thread;

/*
Thread生成
(1). new演算子によりスレッドオブジェクトthread1、thread2を生成しています。

実行可能
(2). startメソッドを実行し、スレッドを実行可能状態に移行します。

実行
(3). Javaを実行するシステムのスケジュール機能に従い、実行可能状態のスレッドの内の1つが実行されます。実行時はrunメソッド内のコードが実行されます。

実行不可：sleep
(4). sleepメソッドを実行し、メソッドを実行したスレッドが1秒間sleep状態となります。1秒経過後、再び実行可能状態となり、システムのスケジュール機能に従い、実行状態となるのを待ちます。Javaを実行するシステムのスケジュール機能が必ずしもthread1オブジェクトとthread2オブジェクトを交互に実行するとは限りません。同一優先度のスレッドが複数存在する場合において、システムのスケジュール機能がそのうちの1つのスレッドの実行を独断的に決定していた場合、thread1オブジェクトとthread2オブジェクトの実行順序は予想できないものとなります。

終了
(5). runメソッドが終了した時点で、スレッドも終了します。
*/

class ExThread1 extends Thread{
    //(3)スレッドの実行
    public void run() {
        for(int i = 1; i <= 10; i++) {
            System.out.println(getName() + ":" + i);
            try {
                //(4)sleep状態へ移行
                sleep(1000);
                //1000ms=1s
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Thread終わり");
    } //(5)スレッドの終了

    public static void main(String[] args){
        //(1)スレッドの作成
        ExThread1 thread1 = new ExThread1();
        //ExThread1 thread2 = new ExThread1();

        //(2)実行可能状態へ移行
        thread1.start();
        //thread2.start();
    }
}


