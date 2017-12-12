import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.CharBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
/*
従来の java.net パッケージを使用した入出力では、ソケットの accept メソッドや read メソッドなどを呼び出すと接続や入力があるまで処理が待ち状態になりました。
このような入出力待ちの動作のことをブロックといいます。

接続の待ちうけでブロックが発生するため、複数のネットワーク接続を同時に処理するサーバアプリケーションを実装するにはマルチスレッドを利用する必要がありました。
しかしスレッドの生成はそれなりにコストのかかる処理であり、アクセスの多いサーバではその影響が無視できないくらい大きくなります。
そこで NIO ではブロックの発生しない入出力を実現する方法が提供されました。
ブロックされない入出力を利用すると、１つのスレッドでも複数の入出力を見かけ上同時に処理することができるようになります。

NIO でネットワーク入出力を行うためには SocketChannel や ServerSocketChannel を利用します。
これらはそれぞれ、java.io パッケージの Socket や ServerSocket に相当します。

これらのクラスはブロックする入出力とブロックしない入出力のどちらも利用することができます。
*/

public class ChannelEchoServer2 implements ServerInteraction {

    private static final int ECHO_PORT = 10007;

    private Set<SocketChannel> channels = new HashSet<>();

    private Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {
        new ChannelEchoServer2().run();
    }

    public void run() {
        ServerSocketChannel serverChannel = null;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
            System.out.println("ChannelEchoServerが起動しました(port=" + serverChannel.socket().getLocalPort() + ")");

            loop(serverChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeServer(serverChannel);
        }
    }

    private void loop(ServerSocketChannel serverChannel) throws IOException {
        while (true) {
            showMessage("waiting accept()");

            SocketChannel channel = serverChannel.accept();

            SocketAddress address = channel.socket().getRemoteSocketAddress();
            showMessage(address + ": 接続されました");
            register(channel);

            ServerReaderTask reader = new ServerReaderTask(channel,this);
            new Thread(reader).start();
        }
    }

    private void closeServer(ServerSocketChannel serverChannel) {
        if (serverChannel == null || !serverChannel.isOpen()) {
            return;
        }

        try {
            System.out.println();
            showMessage("ChannelEchoServerを停止します。");
            serverChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message) {
        System.out.println(message);
        System.out.println("-----------------");
    }

    private void register(SocketChannel channel) {
        channels.add(channel);
    }

    private void unregister(@Nullable SocketChannel channel) {
        if (channel == null) {
            return;
        }

        showMessage(channel.socket().getRemoteSocketAddress() + " is closed");

        if (channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        channels.remove(channel);
    }

    private void send(String input) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("receive message: ").append(input).append("\n");
        messageBuilder.append("send list: \n");

        for (SocketChannel channel : channels) {
            if (!channel.isConnected()) {
                unregister(channel);
                continue;
            }

            ServerSenderTask sender = new ServerSenderTask(channel, charset.encode(CharBuffer.wrap(input)), this);
            new Thread(sender).start();
            messageBuilder.append(channel.socket().getRemoteSocketAddress()).append("\n");
        }

        showMessage(messageBuilder.toString());
    }

    @Override
    public void brokenPipeChannel(SocketChannel channel) {
        unregister(channel);
    }

    @Override
    public void replyAll(String message) {
        send(message);
    }
}