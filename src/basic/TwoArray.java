package basic;// 点数の低いほうを表示するプログラムを作成せよ。
// 例：  0番さんは英語の方が点が低い
//       1番さんは数学の方が点が低い
//  if 文の比較と、forループで作る
// ヒント  String[] kamoku = {"数学", "英語"}; とすると、kamoku[0] とか kamoku[1] が使える
// 数学と英語の比較は for文を使わなくても  if(score[i][0] > score[i][1]) といった感じで
// 比較が終わるTwoArrayMin.java

//1番目を一郎、2番目を二郎（ロッター）、３番目を三番艦、４番目をスシロー、５番目を吾郎メンバーとして名前を配列の最初に記録し、名前も出力させよ。TreeArray.java


public class TwoArray {

	public static void main(String[] args){

		// 各々の人の英語の点数は、70, 80, 65, 40, 90
		int[][] score = {        // 1つ目の添え字が人、2つ目の添え字が科目
			{82, 70},   //1番目が数学, 2番目が英語
			{55, 80},
			{77, 65},
			{91, 40},
			{62, 90}
		};

		// 数学の平均点
		int sum = 0;
		for(int i = 0; i < score.length; i++) {
			sum += score[i][0];
		}
		System.out.println("数学の平均点は" + sum/score.length);

		// 英語の平均点	
		sum = 0;
		for(int i = 0; i < score.length; i++) {
			sum += score[i][1];
		}
		System.out.println("英語の平均点は" + sum/score.length);

		// 英語の最低点
		int minV = score[0][1];
		for(int i = 0; i < score.length; i++) {
			if (minV > score[i][1]) {
				minV = score[i][1];
			}
		}
		System.out.println("英語の最低点は" + minV);


	}

}