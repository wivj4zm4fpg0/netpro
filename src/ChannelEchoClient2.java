import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ChannelEchoClient2 {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {
        ChannelEchoClient2 client = new ChannelEchoClient2();
        client.connect();
    }

    private void connect() {

        try {
            SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", ECHO_PORT));

            System.out.println(channel.socket().getRemoteSocketAddress() + ": 接続しました");
            System.out.println("文字を入力しEnterで送信します");

            new Thread(new ClientSenderTask(channel)).start();
            new Thread(new ClientReaderTask(channel)).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}