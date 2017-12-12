import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PrimeServer {
    public static void main(String[] args) {
        try {
            // サーバーソケット作成
            //起動時パラメータからポートを読み取り、
            //そのポートで接続要求を待つ
            //ServerSocketクラスはクライアントからの接続を待ち、
            //srvSock.accept();によって接続したSocketオブジェクト
            //を返す。
            //その後の通信には、このSocketオブジェクトを使用する。
            //int port = Integer.parseInt(args[0]);
            int port = 8888;
            ServerSocket srvSock = new ServerSocket(port);

            // 接続待機。接続完了後、次行命令に移る。
            Socket socket = srvSock.accept();
            //接続先アドレスを表示
            System.out.println("Address:" + socket.getInetAddress());

            //　通信処理
            //ソケットの入力ストリームから文字列を1行読み取る。
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                System.out.println();
                String line = reader.readLine();
                if (line.equals("q") || line.equals("exit")) {
                    System.out.println("exit");
                    writer.println("-1");
                    writer.flush();
                    writer.close();
                    reader.close();
                    socket.close();
                    srvSock.close();
                    System.exit(0);
                } else if (sosuuHantei(Integer.parseInt(line))) {
                    System.out.println(line + " is Prime");
                    writer.println("1");
                    writer.flush();
                } else {
                    System.out.println(line + " is non-Prime");
                    writer.println("0");
                    writer.flush();
                }
            }
            //読み取った文字列を表示

            //終了処理　このプログラムは1行読み取ったら終了する。
            //通信を続けるのであれば、reader.readLine();を
            //ループするが、終了コマンドをチェックする等の処理を
            //記述する。
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean sosuuHantei(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i < number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
