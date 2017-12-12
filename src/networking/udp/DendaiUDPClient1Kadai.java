package networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class DendaiUDPClient1Kadai {
	public static void main(String[] args) {		// TODO Auto-generated method stub
		try {
			String servhostname="localhost";
			//String servhostname="133.14.44.133";//ここに隣の人のアドレスを入れる。
			
			InetAddress serverAddress = InetAddress.getByName(servhostname);
			String message="hello UDP from Masayuki Iwai";
			byte[] bytesToSend = message.getBytes();
			
			System.out.println("sending msg is"+message);
			//int serverPort = Integer.parseInt(portnumstr);
			int serverPort = 5000;	
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, serverPort);
			socket.send(sendPacket);
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//try catch end
		
		
		try {
			final int DMAX = 255;
			
			int serverPort = 5001;
			System.out.println("UDP client is waiting replay at" + serverPort);
			DatagramSocket socket = new DatagramSocket(serverPort);
			DatagramPacket receivePacket = new DatagramPacket(
					new byte[DMAX], DMAX);
			
			socket.receive(receivePacket);
			String replaymessage=new String(receivePacket.getData());
			System.out.println("Receiced Packet Message is "
					+replaymessage );

			socket.receive(receivePacket);
			replaymessage=new String(receivePacket.getData());
			System.out.println("Receiced Packet Message is "
					+replaymessage );

			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//try end
		
		
		
		
		
		
	}//main

}
