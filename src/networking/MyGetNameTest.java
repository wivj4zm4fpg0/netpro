package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class MyGetNameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		try {
			InetAddress address = InetAddress.getByName("www.dendai.ac.jp");
			System.out.println(address.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}

}
