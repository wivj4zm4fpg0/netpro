import java.io.IOException;
import java.io.ObjectInputStream; //　入出力関連パッケージを利用する
import java.io.ObjectOutputStream;
import java.net.ServerSocket; //ネットワーク関連のパッケージを利用する
import java.net.Socket;

//　ユーティリティパッケージを利用する

public class DendaiTCPServ3Age {

	/*
	 * メイン・メソッド 接続要求のあったクライアントに対して接続を行い クライアントから送られる年齢を受信し、その年齢を元に
	 * 飲酒の可否を判定した結果をクライアントに対して送信する
	 */
	public static void main(String arg[]) {

		ServerSocket server = null;
		Socket socket = null;
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;

		try {
			/* 通信の準備をする */
			System.out.println("ServerSocketを準備します。");
			server = new ServerSocket(5002); // ポート番号を指定し、クライアントとの接続の準備を行う
			socket = server.accept(); // クライアントからの接続要求を待ち、
			System.out.println("サーバ側での接続完了");
			// 要求があればソケットを取得し接続を行う
		} catch (Exception e) {
			System.err.println("接続時にエラーが発生したのでプログラムを終了します");
			e.printStackTrace();
		}

		Integer data=null;
		Integer data2=null;
		int age;
		int age2;
		String resultmessage;
		String resultmessage2;
		try {

			/* 年齢を受信する */
			if (ois == null)
				ois = new ObjectInputStream(socket.getInputStream());

			int counter = 100;
			while (counter-- > 0) {
				// ///////////////////////////////////
				try{
				data = (Integer) ois.readObject();// Integerクラスでキャスト。
				}catch(java.io.EOFException eof){
					eof.printStackTrace();
				}
				if(data==null)
					return;
				
				age = data.intValue();
				System.out.println("from client" + age);

				/* 飲酒の可否を判定する */
				if (age >= 20) {
					resultmessage = age + ":Drink OK";
				} else {
					resultmessage = age + ":Drink NO";
				}
				/* 判定結果をクライアントに送信する */
				if (oos == null)
					oos = new ObjectOutputStream(socket.getOutputStream());
				if (oos != null){
				oos.writeObject(resultmessage);
				oos.flush();
				}
				// ///////////////////////////////////
				/*
				data2 = (Integer) ois.readObject();// Integerクラスでキャスト。
				age2 = data2.intValue();
				System.out.println("from client" + age2);

				if (age2 >= 20) {
					resultmessage2 = age2 + ":Drink OK";
				} else {
					resultmessage2 = age2 + ":Drink NO";
				}

				oos.writeObject(resultmessage2);
				oos.flush();
				*/
			}
			// ///////////////////////////////////

			// ois.close();
			// /oos.close();

		}// エラーが発生したらエラーメッセージを表示してプログラムを終了する
		catch (java.io.EOFException eof) {
			eof.printStackTrace();
		} catch (java.net.SocketException e) {
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("通信時エラーが発生したのでプログラムを終了します");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// 修了処理
		if (ois != null) {

			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}