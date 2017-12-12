package networking.udp;

import java.net.DatagramSocket;
 import java.net.DatagramPacket;
 import java.io.IOException;
  
 public class DatagramReceiver {
  
   public static final int SERVER_PORT = 10007;
   public static final int PACKET_SIZE = 1024;
  
  public static void main(String args[]) {
 
    DatagramSocket socket = null;
    byte[] buf = new byte[PACKET_SIZE];
	//格納用パケット
    DatagramPacket packet = new DatagramPacket(buf, buf.length);
 
    try {
		//パケットの送受信に用いるソケットを作成。10007番ポートを利用。
      socket = new DatagramSocket(SERVER_PORT);
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