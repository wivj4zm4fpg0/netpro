package networking;

import java.net.InetAddress;


public class MyPing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		InetAddress host;
		if(args.length < 1) {
			System.out.println("input:java networking.MyPing <IPAddress or HostName>");
			System.exit(-1);
		}
		
		try {
			host = InetAddress.getByName(args[0]);
			if(host.isReachable(1000))
				System.out.println("OK");
			else
				System.out.println("NG");
		}catch(Exception e) {
			System.out.println("ホストに到達できません。");
		}

	}

}
