import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class PrimeCelinet {
    public static void main(String[] args) {
        try {
            //
            //InetSocketAddress socketAddress =
            //new InetSocketAddress(args[0], Integer.parseInt(args[1]));

            InetSocketAddress socketAddress = new InetSocketAddress("localhost", 8888);
            //隣の人にチャットができるか確認してみよう。
            //InetSocketAddress socketAddress = new InetSocketAddress("192.168.11.1", 8888);

            Socket socket = new Socket();
            socket.connect(socketAddress, 10000);

            InetAddress inetadrs;
            if ((inetadrs = socket.getInetAddress()) != null) {
                System.out.println("address:" + inetadrs);
            } else {
                System.out.println("Connection fail");
                return;
            }

            //please reset your name
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            BufferedReader receiveReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.println();
                System.out.println("please input");
                String message = reader.readLine();
                System.out.println("send message at Client: " + message);
                writer.println(message);
                writer.flush();

                String reply = receiveReader.readLine();
                if (reply.equals("-1")) {
                    break;
                } else if (reply.equals("1")) {
                    System.out.println(message + " is Prime");
                } else if (reply.equals("0")) {
                    System.out.println(message + " is non-Prime");
                }
            }

            System.out.println("exit");
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
