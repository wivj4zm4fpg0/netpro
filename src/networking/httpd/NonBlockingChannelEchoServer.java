package networking.httpd;
 import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
 
public class NonBlockingChannelEchoServer {
 
    private static final int ECHO_PORT = 10007;
    private static final int BUF_SIZE = 1000;
 
    private Selector selector;
 
    public static void main(String[] args) {
        new NonBlockingChannelEchoServer().run();
    }
 
    public void run() {
        ServerSocketChannel serverChannel = null;
        try {

            /*
             * ノンブロッキングモードのチャネルでは、
             * 入出力操作を行っても処理がブロックされません。
             * たとえば ServerSocketChannel で accept() メソッドを呼び出した場合、
             * 接続があってもなくてもただちにメソッドの実行が終了します。
             * 
             * これではいつ入出力を行うメソッドを呼び出したらよいのかわかりません。
             * 利用可能なチャネルを取得するための仕組みとしてセレクタ(Selector)というものを用います。
             * セレクタにチャネルを登録しておき、
             * そこから利用可能となったものを取り出して入出力を行います。
             * セレクタには複数のチャネルを登録することが可能で、
             * 一つのスレッドでも見かけ上複数の入出力を同時に行うことができます。
             * 
             */
        	
        	selector = Selector.open();
            //↑同様の内容Selector selector = SelectorProvider.provider().openSelector();
            
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);//ノンブロッキングなチャネルを宣言
            serverChannel.socket().bind(new InetSocketAddress(ECHO_PORT));
            
            //セレクタにチャネルを登録するには、SelectableChannel の register() メソッドを呼び出す
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            
            System.out.println("NonBlockingChannelEchoServerが起動しました(port="
                    + serverChannel.socket().getLocalPort() + ")");
            

            /*
             * 
             * セレクタから利用可能となったチャネルを取り出すためには、通常はまず Selector クラスの select() メソッドを呼び出します。select() メソッドは利用可能なチャネルの個数を返します。ここで「利用可能とは」、たとえば OP_ACCEPT を指定して登録した ServerSocketChannel に接続要求がきた場合などを指します。select() メソッドには以下の３つのバリエーションがあります。
             * int select()
             * int select(long timeout)
             * int selectNow()
             */
            
            while (selector.select() > 0) {
                for (Iterator it = selector.selectedKeys().iterator(); it.hasNext();) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isAcceptable()) {
                        doAccept((ServerSocketChannel) key.channel());
                    } else if (key.isReadable()) {
                        doRead((SocketChannel) key.channel());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverChannel != null && serverChannel.isOpen()) {
                try {
                    System.out.println("NonBlockingChannelEchoServerを停止します。");
                    serverChannel.close();
                } catch (IOException e) {}
            }
        }
    }
 
    private void doAccept(ServerSocketChannel serverChannel) {
        try {
            SocketChannel channel = serverChannel.accept();
            String remoteAddress = channel.socket()
                    .getRemoteSocketAddress()
                    .toString();
            System.out.println(remoteAddress + ":[接続されました]");
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private void doRead(SocketChannel channel) {
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
            System.out.print(remoteAddress + ":"
                    + charset.decode(buf).toString());
            buf.flip();
            channel.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println(remoteAddress + ":[切断しました]");
            try {
                channel.close();
            } catch (IOException e) {}
        }
    }
 
}