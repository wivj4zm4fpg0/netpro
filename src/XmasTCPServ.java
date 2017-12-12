import java.io.ObjectInputStream;    //　入出力関連パッケージを利用する
import java.io.ObjectOutputStream;
import java.net.ServerSocket;    //ネットワーク関連のパッケージを利用する
import java.net.Socket;
//　ユーティリティパッケージを利用する

public class XmasTCPServ {

    /*メイン・メソッド
     * 接続要求のあったクライアントに対して接続を行い
     * クライアントから送られる年齢を受信し、その年齢を元に
     * 飲酒の可否を判定した結果をクライアントに対して送信する
     */
    public static void main(String arg[]) {
        try {
            /* 通信の準備をする */
            ServerSocket server =
                    new ServerSocket(5002);        //ポート番号を指定し、クライアントとの接続の準備を行う
            System.out.println("server is waiting present at 5002");

            Socket socket = server.accept();    //クライアントからの接続要求を待ち、
            // 要求があればソケットを取得し接続を行う
            System.out.println("connected");

			/* 年齢を受信する */
            ObjectInputStream ois =
                    new ObjectInputStream(socket.getInputStream());


            XmasPresent present = (XmasPresent) ois.readObject();//Integerクラスでキャスト。


            String msgPresent = present.getMessage();
            System.out.println("受け取ったpresentのメッセージは" + msgPresent);
            String presentfromClinet = present.getPresent();
            System.out.println("presentはメッセージは" + presentfromClinet);
	 		
	 		
			/* 判定結果をクライアントに送信する */
            ObjectOutputStream oos =
                    new ObjectOutputStream(socket.getOutputStream());


            XmasPresent present2 = new XmasPresent();
            present2.setMessage("サバ男です。メリークリスマス");
            present2.setPresent("豪華なもの");

            oos.writeObject(present2);
            oos.flush();


            //close処理

            oos.close();
            ois.close();
            //socketの終了。
            socket.close();
            server.close();

        }// エラーが発生したらエラーメッセージを表示してプログラムを終了する
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("エラーが発生したのでプログラムを終了します");
        }
    }
}