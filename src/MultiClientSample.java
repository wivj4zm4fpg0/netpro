//************以下、クライアントソフト。

//クライアントサンプルプログラム
//サーバーに接続し、メッセージを送信する。
//ポートは5000に固定。先にMultiServerSampleを起動しておくこと。
//第2引数で、メッセージを指定する。一行送ってサーバーからの
//メッセージ受信，表示後にプログラム終了する。
//コマンドライン例：java MultiClientSample localhost abcdefg

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

class MultiClientSample 
{
    public static void main(String[] args)
    {

		new MultiClientSample();
	}
    
    MultiClientSample(){//コンストラクター
		String hostname="localhost";
		//String hostname="133.27....";//おとなりのipaddress
		
		doClientJob(hostname,"message hello1");
		doClientJob(hostname,"message hello2");
    }

    public void doClientJob(String hostname,String msg)
    {
        try {
        
    //アドレス情報を保持するsocketAddressを作成。
    //ポート番号は5000
        InetSocketAddress socketAddress = 
            new InetSocketAddress(hostname, 5000);
            
    //socketAddressの値に基づいて通信に使用するソケットを作成する。

    //
        Socket socket = new Socket();
    //タイムアウトは10秒(10000msec)
        socket.connect(socketAddress, 10000);

    //接続先の情報を入れるInetAddress型のinadrを用意する。
        InetAddress inadr;

    //inadrにソケットの接続先アドレスを入れ、nullである場合には
    //接続失敗と判断する。
    //nullでなければ、接続確立している。
        if ((inadr = socket.getInetAddress()) != null) {
            System.out.println("Connect to " + inadr);
        }else {
            System.out.println("Connection failed.");
            return;
        }

    //メッセージの送信処理
    //コマンドライン引数の2番目をメッセージとする。
        String message = msg;

    //PrintWriter型のwriterに、ソケットの出力ストリームを渡す。(Auto Flush)
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
    //ソケットの入力ストリームをBufferedReaderに渡す。
        BufferedReader rd = 
            new BufferedReader(new InputStreamReader( socket.getInputStream()));

        System.out.println("Send message: " + message);

    //ソケットから出力する。
        writer.println(message);
        
        
    //もしPrintWriterがAutoFlushでない場合は，以下が必要。
    //  writer.flush();

    //サーバーからのメッセージ読み取り
        String getline=rd.readLine();
        System.out.println("Message from Server:" + getline);

    //終了処理
        	
        	rd.close();
            writer.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    } 
}