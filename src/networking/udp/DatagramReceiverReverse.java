package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
  
 public class DatagramReceiverReverse {
  
   public static final int RECEIVE_PORT = 10007;
   public static final int SENDBACK_PORT = 10008;
   
   public static final int PACKET_SIZE = 1024;
  
  public static void main(String args[]) {
 
    DatagramSocket socket = null;
    byte[] buf = new byte[PACKET_SIZE];
	//格納用パケット
    DatagramPacket packet = new DatagramPacket(buf, buf.length);
 
    try {
		//パケットの送受信に用いるソケットを作成。10007番ポートを利用。
      socket = new DatagramSocket(RECEIVE_PORT);
      System.out.println("DatagramReceiverが起動しました(port="
                         + socket.getLocalPort() + ")");
      while (true) {

		//パケットの到着を待つ。
		//DatagramSocketクラスのreceiveメソッドは、
		//ServerSocketクラスのacceptメソッドと同じように、パケットが到着するかタイムアウトなど			//により中断されるまで動作をブロック。
		//到着したパケットはreceiveメソッドの引数で指定したpacketに格納。
        socket.receive(packet);

		//受信したパケットから文字列を取り出す。
		//byte配列から文字列を作成するStringクラスのコンストラクタを利用。
        String message = new String(buf, 0, packet.getLength());

		//送信元のアドレスを表示。
		//送信元のアドレスは、DatagramPacketクラスのgetSocketAddressメソッドで取得する。
        System.out.println(packet.getSocketAddress()
                           + " 受信: " + message);
        //////sendbackの処理////
        
        InetSocketAddress sendbackAddress =
        	      new InetSocketAddress(packet.getAddress(), SENDBACK_PORT);
        
        byte[] sendbackbuf = new byte[PACKET_SIZE];
        sendbackbuf=("kaetteyoshi!:"+message).getBytes();
        
        DatagramPacket sendback_packet =
                new DatagramPacket(sendbackbuf, sendbackbuf.length, sendbackAddress);
        DatagramSocket sendback_socket = new DatagramSocket();
        sendback_socket.send(sendback_packet);
        sendback_socket.close();
        

      }//while end
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (socket != null) {
        socket.close();
      }
    }//finall end
  }//main end
 
}//class end