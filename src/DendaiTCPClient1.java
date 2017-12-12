import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class DendaiTCPClient1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String server = "18.220.134.226";
		//String server = "133.27....";
		
		int serverPort = 55555;
		//
		
		String str="Hello World from Masayuki IWAI";
		byte[] byteBuffer = str.getBytes();

		
		//byte[] byteBuffer = args[2].getBytes();
		
		Socket socket;
		try {
			
			System.out.println("client start");
			socket = new Socket(server, serverPort);
			OutputStream out = socket.getOutputStream();
			
			out.write(byteBuffer);
			System.out.println("client sent message to Server"+str);

		//String str2="Hello This is biyoshitsu L de-su";
		//byteBuffer = str2.getBytes();
		//out.write(byteBuffer);
			
			out.flush();
			out.close();
			
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
