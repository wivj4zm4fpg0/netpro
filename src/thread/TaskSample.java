package thread;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Timerはバックグラウンドスレッドで将来実行されるタスクをスケジュールする、スレッドのための機能です。
 * タスクは、1 回だけ、または定期的に繰り返し実行されるようにスケジュールされます。

 各 Timer オブジェクトと対応するのは、タイマーのタスクをすべて連続して実行するために使用される、
 単一のバックグラウンドスレッドです。タイマータスクは迅速に実行される必要があります。
 タイマータスクの完了に時間がかかりすぎると、タイマーのタスク実行スレッドが「占有」されます。これにより後続のタスクの実行が遅れ、
 違反したタスクの完了時 (完了した場合) に、立て続けに「まとめて」実行されることになります。

 Timer オブジェクトの最後のライブ参照が終了し、未処理のタスクがすべて実行されると、
 タイマーのタスク実行スレッドも同時に完了し、ガベージコレクトされます。
 ただし、これには限りなく長い時間がかかる場合があります。
 デフォルトでは、タスクの実行スレッドは「デーモンスレッド」としては実行されないため、
 アプリケーションが終了しないようにできます。

 タイマーのタスク実行スレッドをただちに完了させる場合、呼び出し側はタイマーの cancel メソッドを呼び出す必要があります。
 stop メソッドの呼び出しなどによりタイマーのタスク実行スレッドが予想外の時間に終了した場合、
 タイマーのタスクをスケジュールしようとすると、タイマーの cancel メソッドが呼び出された場合と同様に、IllegalStateException が発生します。

 *
 *
 */

public class TaskSample {
    public static void main(String[] args) {
        System.out.println("---schedule()---");
        Timer timer1 = new Timer();
        timer1.schedule(new Task1(), 0, 1000); // タスクの実行間隔は1000ミリ秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        timer1.cancel();

        System.out.println("---scheduleAtFixedRate()---");
        Timer timer2 = new Timer();
        timer2.scheduleAtFixedRate(new Task1(), 0, 1000); // タスクの実行間隔は1000ミリ秒
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
        timer2.cancel();

    }
}
