package networking.chat;//networking.chat.ChatClient.java
//チャットクライアントサンプルプログラム
//チャットサーバーが動いているコンピュータに対して
//接続を行う。
//コマンドラインパラメータとして接続先アドレスとポート番号を
//指定する。
//java CharClient localhost 99

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

class ChatClientPrintWriter
{
	public static void main(String[] args)
	{
	try {
	    //
	    //InetSocketAddress socketAddress =
		//new InetSocketAddress(args[0], Integer.parseInt(args[1]));

		InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
		//隣の人にチャットができるか確認してみよう。
		//InetSocketAddress socketAddress = new InetSocketAddress("192.168.11.1", 8888);


		//※2

	    Socket socket = new Socket();
	    socket.connect(socketAddress, 10000);

	    InetAddress inetadrs;
	    if ((inetadrs = socket.getInetAddress()) != null) {
		System.out.println("address:" + inetadrs);
	    }
	    else {
		System.out.println("Connection fail");
		return;
	    }

	    //※1
	    //please reset your name
	    String message = "Chat Test from Your name";
	    PrintWriter writer = new PrintWriter(socket.getOutputStream());
	    System.out.println("send message at Client: " + message);
	    writer.println(message);

	    //フラッシュすることでバッファ内の内容を送信を行います。
	    writer.flush();
	    //※1
	    writer.close();
	    socket.close();
	    //※2

	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
