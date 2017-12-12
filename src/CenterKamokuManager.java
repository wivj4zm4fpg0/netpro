public class CenterKamokuManager {

    private CenterKamoku centerKamoku;

    public CenterKamokuManager() {
        centerKamoku = new CenterKamoku();
    }

    public void printAverage(int number) {

        int average = 0;

        for (int i = 0; i < centerKamoku.getScore()[number].length; i++) {
            average += centerKamoku.getScore()[number][i];
            System.out.println(centerKamoku.getKamokuName()[number][i] + "：" + centerKamoku.getScore()[number][i]);
        }

        System.out.println("5科目の平均点" + (average / centerKamoku.getScore()[number].length));
    }

    public void printMaxKamoku(int number) {

        int max = 0;
        String kamoku = "";

        for (int i = 0; i < centerKamoku.getScore()[number].length; i++) {
            if (max < centerKamoku.getScore()[number][i]) {
                max = centerKamoku.getScore()[number][i];
                kamoku = centerKamoku.getKamokuName()[number][i];
            }
        }

        System.out.println("最高点は" + max + "の" + kamoku + "です。");
    }

    public static void main(String[] args) {

        CenterKamokuManager centerKamokuManager = new CenterKamokuManager();

        for (int i = 0; i < 100; i++) {
            centerKamokuManager.printAverage(i);
            centerKamokuManager.printMaxKamoku(i);
            System.out.println();
        }
    }
}
