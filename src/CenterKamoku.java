import java.util.Random;

public class CenterKamoku {

    private String kamokuName[][];
    private int score[][];

    public CenterKamoku() {

        kamokuName = new String[100][];
        score = new int[100][];

        for (int i = 0; i < kamokuName.length; i++) {

            kamokuName[i] = new String[]{"英語", "国語", "数学", "社会", "理科"};
            score[i] = new int[5];

            for (int j = 0; j < score[i].length; j++) {
                score[i][j] = new Random().nextInt(101);
            }
        }
    }

    public String[][] getKamokuName() {
        return kamokuName;
    }

    public int[][] getScore() {
        return score;
    }
}
