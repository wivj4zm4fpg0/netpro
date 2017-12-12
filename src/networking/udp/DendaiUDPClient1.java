package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class DendaiUDPClient1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String servhostname="localhost";
			//String servhostname="133.27....";//個々に隣の人のアドレスを入れる。
			
			InetAddress serverAddress = InetAddress.getByName(servhostname);
			String message="hello UDP from yourname";
			byte[] bytesToSend = message.getBytes();
			
			
			System.out.println("sending msg is"+message);
			//int serverPort = Integer.parseInt(portnumstr);
			int serverPort = 5000;
			
			DatagramSocket socket = new DatagramSocket();
			
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, serverPort);
			
			socket.send(sendPacket);
			
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
