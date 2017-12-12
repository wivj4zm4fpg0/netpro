package thread;

import java.util.Timer;
import java.util.TimerTask;

class Task1 extends TimerTask {
    private volatile boolean isFirst = true;
    private volatile int taskNum = 1;
    public void run() {
        System.out.println(taskNum + " " + System.currentTimeMillis());
        taskNum++;
        if (isFirst) {
            isFirst = false;
            try {
                Thread.sleep(2000); // 1回目のタスクの実行には2000ミリ秒かかる
            } catch (InterruptedException ignore) {
            }
        }
    }
}
