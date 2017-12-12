package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class DendaiUDPClient2 {

	private static final int DMAX = 255;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InetAddress serverAddress = InetAddress.getByName(args[0]);
			byte[] bytesToSend = args[2].getBytes();
			int serverPort = Integer.parseInt(args[1]);
			
			DatagramSocket socket = new DatagramSocket();
			
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, serverPort);
			
			DatagramPacket receivePacket = new DatagramPacket(new byte[DMAX], DMAX);
			
			socket.send(sendPacket);
			socket.receive(receivePacket);
			
			System.out.println(new String(receivePacket.getData()));
			
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
