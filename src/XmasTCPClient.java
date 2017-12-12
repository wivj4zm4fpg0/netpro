/*
  * キーボードから読み込んだ年齢をサーバに送信し、
  * サーバから受信した飲酒可否の判定結果をディスプレイに表示するクライアント
*/

import java.io.BufferedReader;    //　入出力関連パッケージを利用する
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;    //ネットワーク関連のパッケージを利用する
//　ユーティリティパッケージを利用する

public class XmasTCPClient {

    /*メイン・メソッド
     * 指定されたサーバに対して接続を要求し、
     * 接続されたらキーボードから読み込んだ年齢を送信し、
     *受信した飲酒可否の判定結果をディスプレイに表示する
    */
    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            BufferedReader reader =        //キーボードから接続するサーバ名を読み込む
                    new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Server name(localhost or 133.27.....)? >");
            String serverName = reader.readLine();
            Socket socket =            //指定されたサーバの5000番ポートに接続を要求する
                    new Socket(serverName, 5002);

			/* キーボードから年齢を読み込む */
            System.out.print("Your age? >");

            String lineStr = reader.readLine();

			/* サーバに年齢を送信する */
            ObjectOutputStream oos =
                    new ObjectOutputStream(socket.getOutputStream());

            //intはobject型でないのでObject型であるIntergerクラスに変換する。


            XmasPresent present = new XmasPresent();
            present.setMessage("クリスマスおめでとう！" + lineStr);
            present.setPresent("消しゴム");


            oos.writeObject(present);
            oos.flush();
			
			/* サーバから判定結果を受信する */
            ObjectInputStream ois =
                    new ObjectInputStream(socket.getInputStream());
            //String result = (String)ois.readObject();//返事を文字列型でキャストする。

            XmasPresent okaeshiPresent = (XmasPresent) ois.readObject();

            String replayMsg = okaeshiPresent.getMessage();
            System.out.println("お返しのプレゼントについていたのメッセージは" + replayMsg);
            String replayPresent = okaeshiPresent.getPresent();
            System.out.println("お返しプレゼントは" + replayPresent);

            oos.close();
            ois.close();

            socket.close();

        }// エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (java.net.UnknownHostException uhe) {
            uhe.printStackTrace();
            System.err.println("送信先のサーバ名が間違っているのでプログラムを終了します");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("エラーが発生したのでプログラムを終了します");
        }
    }
}