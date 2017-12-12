import thread.ICallBackListener;

class CountTenRunnableImpleC implements Runnable {
    private String tname;
    private int n;
    private int time;
    private ICallBackListener listener;

    CountTenRunnableImpleC(String tname, int n) {
        this.tname = tname;
        this.n = n;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("runnable thread:tname = " + tname + " :i = " + i);
                Thread.sleep(n * 1000);  // ()の中はミリ秒
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.printFinish(this.tname);
    }

    void setCallBackListener(ICallBackListener listener) {
        this.listener = listener;
    }
}
