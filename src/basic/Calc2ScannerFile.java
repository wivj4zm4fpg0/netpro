package basic;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class Calc2ScannerFile {

	public static void main(String args[]){
		try{
			File file = new File("targetfile.txt");
			Scanner scan = new Scanner(file);
			scan.useDelimiter("¥¥r¥¥n");

			int line = 1;

			while(scan.hasNext()){
				String str = scan.next();
				System.out.println(line + ":" + str);
				line++;
			}
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
	}
}

//  課題    キーボードから2つの数字を打ち込む
//     その足し算結果を、出力する。
//  できたら、第02回の提出フォルダへ Calc2.java を置く。
//  解答は次回、出す。自力でコンパイルエラーを取る練習をしてください。

