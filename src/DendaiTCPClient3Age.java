/*
 * キーボードから読み込んだ年齢をサーバに送信し、
 * サーバから受信した飲酒可否の判定結果をディスプレイに表示するクライアント
 */

import java.io.BufferedReader; //　入出力関連パッケージを利用する
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket; //ネットワーク関連のパッケージを利用する
//　ユーティリティパッケージを利用する

public class DendaiTCPClient3Age {

	/*
	 * メイン・メソッド 指定されたサーバに対して接続を要求し、 接続されたらキーボードから読み込んだ年齢を送信し、
	 * 受信した飲酒可否の判定結果をディスプレイに表示する
	 */
	public static void main(String arg[]) {

		BufferedReader reader = null;
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			/* 通信の準備をする */
			reader = // キーボードから接続するサーバ名を読み込む
			new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Server name(localhost or 133.27.....)? >");
			String serverName = reader.readLine();
			socket = // 指定されたサーバの5000番ポートに接続を要求する
			new Socket(serverName, 5002);
			System.out.print("クライアントからの接続成功");
		} catch (Exception e) {

			e.printStackTrace();
		}

		String result;
		String result2;
		int inputage;
		Integer data;
		Integer data2;
		try {
			
			oos = new ObjectOutputStream(socket.getOutputStream());

			/* キーボードから年齢を読み込む */
			
			System.out.print("Your age? >");
			inputage = Integer.parseInt(reader.readLine());
			/* サーバに年齢を送信する */
			data = new Integer(inputage);
			// intはobject型でないのでObject型であるIntergerクラスに変換する。

			oos.writeObject(data);
			oos.flush();

			/* サーバから判定結果を受信する */
			ois = new ObjectInputStream(socket.getInputStream());
			result = (String) ois.readObject();// 返事を文字列型でキャストする。
			/* 判定結果をディスプレイに表示する */
			System.out.println(result);

			int counter = 99;
			while (counter-->0) {
				System.out.print(counter+":Your age? >");
				inputage = Integer.parseInt(reader.readLine());
				data2 = new Integer(inputage);
				oos.writeObject(data2);
				oos.flush();
				result2 = (String) ois.readObject();// 返事を文字列型でキャストする。
				System.out.println(result2);
			}

			// oos.close();
			// ois.close();

		}// エラーが発生したらエラーメッセージを表示してプログラムを終了する
		catch (java.net.SocketException soe) {
			soe.printStackTrace();
		} catch (IOException e) {
			System.err.println("エラーが発生したのでプログラムを終了します");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		if (oos != null) {

			try {
				oos.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		if (ois != null) {

			try {
				ois.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		if (socket != null) {

			try {
				socket.close();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}//if socket ! null end

	}//main end
}//class DendaiTCPClientend