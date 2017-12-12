import java.nio.channels.SocketChannel;

public interface ServerInteraction {

    public void brokenPipeChannel(SocketChannel channel);

    public void replyAll(String message);
}