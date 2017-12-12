package basic;

import java.io.*;    // C言語では、#include に相当する

public class HowOldAreYou2 {

	public static void main(String[] args) { 

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			// BufferedReader というのは、データ読み込みのクラス(型)
			// クラスの変数を作るには、new を使う。

			// readLine() は、入出力エラーの可能性がある。エラー処理がないとコンパイルできない。
			//  Java では、 try{ XXXXXXXX }  catch(エラーの型 変数) { XXXXXXXXXXXXXXXXXX} 

		int counter=1;
		String gengo="元号";
		int gengoyear=0;
		int birthyear=1900;
		int age=0;

		while(counter<10){
		try {
			System.out.println("========="+(counter++)+"回目の質問");
			System.out.println("あなたは何年生まれですか?");
			String line = reader.readLine();
			if(line.equals("q")||line.equals("0")){
				System.out.println("終了します");
				break;
			}
			
			birthyear = Integer.parseInt(line);
			age=2013-birthyear;

			System.out.println("あなたは現在" + age + "歳ですね。"+gengo+gengoyear+"生まれです。");
			System.out.println("7年後の東京オリンピック2020年には、" + (age) + "歳ですね。(; ;)orz");
		}
		catch(IOException e) {
			System.out.println(e);
		}
		}


	}
}

//  課題    キーボードから数字を打ち込む
//  その結果、 あなたは、???歳ですね、と画面に表示させる。
//  その後、あなたは10年後、????歳ですね、と画面に表示させる。

