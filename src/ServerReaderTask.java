import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ServerReaderTask implements Runnable {

    private static final int BUF_SIZE = 1000;

    private SocketChannel channel = null;

    private ServerInteraction interaction;

    public ServerReaderTask(SocketChannel channel, ServerInteraction interaction) {
        this.channel = channel;
        this.interaction = interaction;
    }

    public void run() {
        while (true) {
            String remoteAddress = channel.socket()
                    .getRemoteSocketAddress()
                    .toString();
            try {
                String input = readMessage();

                if (input.isEmpty()) {
                    continue;
                }

                String message = remoteAddress + ": " + input;
                interaction.replyAll(message);
            } catch (IOException e) {
                e.printStackTrace();
                interaction.brokenPipeChannel(channel);
                return;
            }
        }
    }

    private String readMessage() throws IOException {
        ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
        Charset charset = Charset.forName("UTF-8");

        if (channel.read(buf) < 0) {
            return "";
        }

        //flip()は、新しい一連のチャネル読込み操作または相対「get」操作のためにバッファを準備します。リミットの値を現在位置の値に合わせたあと、位置の値をゼロにします。
        buf.flip();
        return charset.decode(buf).toString();
    }

}