import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientReaderTask implements Runnable {

    private static final int BUF_SIZE = 1000;

    private Charset charset = Charset.forName("UTF-8");

    private ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);

    private SocketChannel channel;

    public ClientReaderTask(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            while (channel.isConnected()) {
                buf.clear();

                if (channel.read(buf) < 0) {
                    continue;
                }

                buf.flip();
                System.out.println("受信：" + charset.decode(buf).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}