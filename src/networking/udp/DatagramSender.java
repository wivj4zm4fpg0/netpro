package networking.udp;//一方向にメッセージを送信するだけのプログラム
 import java.net.DatagramSocket;
 import java.net.DatagramPacket;
 import java.net.InetSocketAddress;
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.IOException;
  
 public class DatagramSender {
  
  public static final int SERVER_PORT = 10007;
  //パケットサイズを1024byteに設定
  public static final int PACKET_SIZE = 1024;
  public static final String name="ほげほげ"; 
  public static void main(String args[]) {
    DatagramSocket socket = null;
    //パケットのあて先に利用するソケットアドレスを設定。
    //送信先ホストはプログラムの引数で設定したアドレスとし、ポート番号は10007番。
    InetSocketAddress remoteAddress =
      new InetSocketAddress(args[0], SERVER_PORT);
 	System.out.println("remoteAddress done"+remoteAddress);

    try {
      
      BufferedReader keyIn =
        new BufferedReader(new InputStreamReader(System.in));
      //パケットの送信に利用するDatagramSocketを作成。

      socket = new DatagramSocket();
	  System.out.println("sender socket is ready"+socket);
      String message;

      while ( (message = keyIn.readLine()).length() > 0 ) {
		message=name+">"+message;
        byte[] buf = message.getBytes();
		//送信するパケットを作成。
		//まず、キーボードから入力された文字列をbyte型の配列に変換、
		//そのデータを用いてパケットを作成。
        DatagramPacket packet =
          new DatagramPacket(buf, buf.length, remoteAddress);
     	System.out.println("sender DatagramPacket is ready"+packet);

		//作成したパケットを送信します。
        socket.send(packet);
     	System.out.println("sender sent message"+packet);

      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {//try catchに関係なく実行される
      if (socket != null) {
        socket.close();
      }
    }//finally end
  }//main end
}//class end