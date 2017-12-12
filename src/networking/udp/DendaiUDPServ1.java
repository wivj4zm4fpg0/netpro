package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class DendaiUDPServ1 {

	private static final int DMAX = 255;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			
			int serverPort = 5000;
			
			System.out.println("UDP server is waiting at"+serverPort);
			
			DatagramSocket socket = new DatagramSocket(serverPort);
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[DMAX], DMAX);
			
			socket.receive(receivePacket);
			
			System.out.println("Receiced Packet Message is "+new String(receivePacket.getData()));
			
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
