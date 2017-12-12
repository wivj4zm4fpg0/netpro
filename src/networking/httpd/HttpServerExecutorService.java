package networking.httpd;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

public class HttpServerExecutorService {
    private static final int DEFOULT_PORT = 80;
    private final int port;
    private final ServerSocket socket;
    private final ExecutorService executor;
    private final ConversationHandler conversationHandler;
    
    public HttpServerExecutorService() throws IOException {
        this(DEFOULT_PORT);
    }
    public HttpServerExecutorService(final int port) throws IOException {
        this.port = port;
        this.socket = new ServerSocket();
        this.executor = Executors.newCachedThreadPool();
        this.conversationHandler = new HttpConversationHandler();
    }
    
    public void start() {
        try {
            this.socket.setReuseAddress(true);
            this.socket.bind(new InetSocketAddress(port));
            
            while(!executor.isShutdown()) {
                final Socket connection = socket.accept();
                try {
                    executor.execute(new Runnable() {
                        @Override public void run() {
                            handleConnection(connection);
                        }});
                } catch (RejectedExecutionException e) {
                    if(!executor.isShutdown()) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutDown();
        }
    }
    
    public void shutDown() {
        closeServerSocket(socket);
        executor.shutdown();
        try {
            executor.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    void handleConnection(final Socket connection) {
        try { 
            connection.setTcpNoDelay(true);
        } catch (SocketException e) {  }

        try {
            conversationHandler.handle(
                new BufferedInputStream(connection.getInputStream()),
                new BufferedOutputStream(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeSocket(connection);
        }
    }
    
    public void closeSocket(final Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
    public void closeServerSocket(final ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }
    
    interface ConversationHandler {
        void handle(InputStream request, OutputStream response) throws IOException;
    }
    class HttpConversationHandler implements ConversationHandler {
        @Override
        public void handle(InputStream request, OutputStream response) throws IOException {
            Writer writer = new OutputStreamWriter(response, "UTF-8");
            writer.write("HTTP/1.1 200 OK\n");
            writer.write("\n");
            writer.write(new Date() + " (" + Thread.currentThread() + ")");
            writer.flush();
        }
    }
    
    public static void main(String[] args) throws IOException {
        new HttpServerExecutorService().start();
    }
}