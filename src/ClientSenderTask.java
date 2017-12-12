import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientSenderTask implements Runnable {

    private Charset charset = Charset.forName("UTF-8");

    private SocketChannel channel;

    private BufferedReader keyin;

    public ClientSenderTask(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            while (channel.isConnected()) {
                keyin = new BufferedReader(new InputStreamReader(System.in));
                String line = keyin.readLine();
                System.out.println("送信：" + line);

                channel.write(charset.encode(CharBuffer.wrap(line + "\n")));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}