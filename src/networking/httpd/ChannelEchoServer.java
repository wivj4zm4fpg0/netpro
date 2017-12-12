package networking.httpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
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

public class ChannelEchoServer {

    public static final int ECHO_PORT = 10007;

    public static void main(String[] args) {
        new ChannelEchoServer().run();
    }
    static String message="SERVER Message";
    public void run() {
        //ServerSocketChannel
        ServerSocketChannel serverChannel = null;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
            System.out.println("ChannelEchoServerが起動しました(port="
                    + serverChannel.socket().getLocalPort() + ")");

            while (true) {

                SocketChannel channel = serverChannel.accept();

                System.out.println(channel.socket().getRemoteSocketAddress() + ":[接続されました]");

                new Thread(new ChannelEchoThread(channel,this)).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverChannel != null && serverChannel.isOpen()) {
                try {
                    System.out.println("ChannelEchoServerを停止します。");
                    serverChannel.close();
                } catch (IOException e) {
                }
            }
        }
    }


    class ChannelEchoThread implements Runnable {

        private static final int BUF_SIZE = 1000;
        SocketChannel channel = null;
        ChannelEchoServer server;

        public ChannelEchoThread(SocketChannel channel,ChannelEchoServer server) {
            this.channel = channel;
            this.server=server;
        }

        public void run() {
            ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
            Charset charset = Charset.forName("UTF-8");
            String remoteAddress = channel.socket()
                    .getRemoteSocketAddress()
                    .toString();
            try {
                if (channel.read(buf) < 0) {
                    return;
                }
                buf.flip();
                String input = charset.decode(buf).toString();
                System.out.print(remoteAddress + ":" + input);
                buf.flip();
                //flip()は、新しい一連のチャネル読込み操作または相対「get」操作のためにバッファを準備します。リミットの値を現在位置の値に合わせたあと、位置の値をゼロにします。

                channel.write(buf);

                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(".");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("");

            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                System.out.println(remoteAddress + ":[切断しました]");
                if (channel != null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

    }
}
 
