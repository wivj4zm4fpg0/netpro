package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Calc2Scanner {

	public static void main(String[] args) {
		int i=0;
		while(i<10) {
			Scanner scan = new Scanner(System.in);

			String str = scan.next();
			System.out.println("最初のトークンは: " + str);

			str = scan.next();
			System.out.println("次のトークンは  : " + str);
			i++;
		}


	}
}

//  課題    キーボードから2つの数字を打ち込む
//     その足し算結果を、出力する。
//  できたら、第02回の提出フォルダへ Calc2.java を置く。
//  解答は次回、出す。自力でコンパイルエラーを取る練習をしてください。

