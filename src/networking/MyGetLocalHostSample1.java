package networking;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyGetLocalHostSample1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
        try{
            InetAddress address = InetAddress.getLocalHost();
            //InetAddress address = new InetAddress();
            System.out.println(address.getHostAddress());
        }catch(UnknownHostException e){
            System.out.println("Error");
        }

		
	}

}
