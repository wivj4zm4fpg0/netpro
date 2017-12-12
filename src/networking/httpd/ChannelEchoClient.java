package networking.httpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ChannelEchoClient {

    public static final int ECHO_PORT = 10007;
    public static final int BUF_SIZE = 1000;


    SocketChannel channel = null;
    ByteBuffer buf;
    BufferedReader keyin;
    Charset charset;

    public static void main(String[] args) {


        ChannelEchoClient client = new ChannelEchoClient();
        int i=5;
        while(i-->0) {
            client.connect();
        }
        //client.connect();

    }

    ChannelEchoClient() {
        buf = ByteBuffer.allocate(BUF_SIZE);
        charset = Charset.forName("UTF-8");

    }

    void connect() {


        try {


            channel = SocketChannel.open(new InetSocketAddress("localhost",
                    ECHO_PORT));
            keyin = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("送信：");
            String line = keyin.readLine();
            channel.write(charset.encode(CharBuffer.wrap(line + "\n")));
            while (channel.isConnected()) {
                buf.clear();
                if (channel.read(buf) < 0) {
                    return;
                }
                buf.flip();
                System.out.print("受信：" + charset.decode(buf).toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
            }
        }
    }

}