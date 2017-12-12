package networking.httpd;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;

public class NioHttpServer {
    private static final int DEFOULT_PORT = 80;
    private static final int TIMEOUT = 10 * 1000;
    private static final int BUFFERSIZE = 8192;

    private ServerSocketChannel serverChannel;
    private final int port;

    public NioHttpServer() throws IOException {
        this(DEFOULT_PORT);
    }

    public NioHttpServer(final int port) throws IOException {
        this.port = port;
        this.serverChannel = ServerSocketChannel.open();
        this.serverChannel.configureBlocking(false);
        this.serverChannel.socket().setReuseAddress(true);
        this.serverChannel.socket().bind(new InetSocketAddress(this.port));
    }

    public void start() throws IOException {
        Selector selector = Selector.open();
        serverChannel.register(selector, serverChannel.validOps());

        while (selector.keys().size() > 0) {
            selector.select(TIMEOUT);
            for (SelectionKey key : selector.selectedKeys()) {
                selector.selectedKeys().remove(key);
                if (!key.isValid()) continue;
                if (key.isAcceptable()) handleAcceptable(key);
                if (key.isReadable()) handleReadable(key);
                if (key.isWritable()) handleWritable(key);
            }
        }
    }

    // OP_ACCEPT の処理
    private void handleAcceptable(SelectionKey key) throws IOException {
        SocketChannel ch = ((ServerSocketChannel) key.channel()).accept();
        ch.configureBlocking(false);
        ByteBuffer buff = ByteBuffer.allocateDirect(BUFFERSIZE);
        ch.register(key.selector(), SelectionKey.OP_READ, buff);
    }

    // OP_READ の処理
    private void handleReadable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buff = (ByteBuffer) key.attachment();
        channel.read(buff);
        printRequest(buff);
        mkResponse(buff);
        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
    }

    // OP_WRITE の処理
    private void handleWritable(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buff = (ByteBuffer) key.attachment();
        buff.flip();
        channel.write(buff);
        buff.compact();
        if (buff.position() > 0) {
            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
        } else {
            channel.close();
        }
    }

    // リクエストデータをコンソールへ出力
    private void printRequest(ByteBuffer buff) {
        buff.flip();
        byte[] bytes = new byte[buff.limit()];
        buff.get(bytes);
        buff.compact();
        System.out.println(new String(bytes));
    }

    // レスポンスデータ（固定）のBufferへの書き込み
    private void mkResponse(ByteBuffer buff) {
        buff.clear();
        String text = "HTTP/1.1 200 OK\n\n"
                + new Date() + " (" + Thread.currentThread() + ")";
        buff.put(text.getBytes());
    }

    public static void main(String[] args) throws IOException {
        new NioHttpServer().start();
    }
}