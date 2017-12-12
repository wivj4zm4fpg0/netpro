import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerSenderTask implements Runnable {

    private SocketChannel channel;

    private ByteBuffer buf;

    private ServerInteraction interaction;

    public ServerSenderTask(SocketChannel channel, ByteBuffer buf, ServerInteraction interaction) {
        this.channel = channel;
        this.buf = buf;
        this.interaction = interaction;
    }

    @Override
    public void run() {
        try {
            channel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
            interaction.brokenPipeChannel(channel);
        }
    }
}