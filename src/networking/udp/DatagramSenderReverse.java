package networking.udp;//一方向にメッセージを送信するだけのプログラム
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
  
 public class DatagramSenderReverse {
  
  public static final int SERVER_PORT = 10007;
  //パケットサイズを1024byteに設定
  public static final int SENDBACK_PORT = 10008;
  
  
  public static final int PACKET_SIZE = 1024;
  public static final String name="Masayuki iwai"; 
  public static void main(String args[]) {
    DatagramSocket socket = null;
    //パケットのあて先に利用するソケットアドレスを設定。
    //送信先ホストはプログラムの引数で設定したアドレスとし、ポート番号は10007番。
    InetSocketAddress remoteAddress =
      new InetSocketAddress("localhost", SERVER_PORT);
 	System.out.println("remoteAddress done"+remoteAddress);

    try {
      
      BufferedReader keyIn =
        new BufferedReader(new InputStreamReader(System.in));
      //パケットの送信に利用するDatagramSocketを作成。

      socket = new DatagramSocket();
	  System.out.println("sender socket is ready"+socket);
      String message;
      
      //空のえんたーを打たれるまで繰り返せ
      while ( (message = keyIn.readLine()).length() > 0 ) {
		message=name+">"+message;
        //byte[] buf = message.getBytes();
		byte[] buf = message.getBytes("MS932");
		//送信するパケットを作成。
		//まず、キーボードから入力された文字列をbyte型の配列に変換、
		//そのデータを用いてパケットを作成。
        DatagramPacket packet =
          new DatagramPacket(buf, buf.length, remoteAddress);
     	System.out.println("sender DatagramPacket is ready"+packet);

		//作成したパケットを送信します。
        socket.send(packet);
     	System.out.println("sender sent message"+packet);
     	//リバースを待つ処理がはしる。
     	
     	  
     	DatagramSocket sendbacksocket = null;
     	byte[] sendbackbuf = new byte[PACKET_SIZE];
     	//格納用パケット
     	DatagramPacket sendbackpacket = new DatagramPacket(sendbackbuf, sendbackbuf.length);
     	
        sendbacksocket = new DatagramSocket(SENDBACK_PORT);
        
        sendbacksocket.receive(sendbackpacket);
         String sendbackmessage = new String(sendbackbuf, 0, sendbackpacket.getLength());
         System.out.println(sendbackpacket.getSocketAddress()
                            + " 受信: " + sendbackmessage);
         sendbacksocket.close();
         
     	
      }//while end
    } catch (IOException e) {
      e.printStackTrace();
    } finally {//try catchに関係なく実行される
      if (socket != null) {
        socket.close();
      }
    }//finally end
  }//main end
}//class end