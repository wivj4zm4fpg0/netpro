//複数接続Socket通信サンプルプログラム(サーバー)
//クライアントからの接続を待ち、接続が行なわれたら
//1行のデータを受け取り、コンソールに表示して接続を切断する。
//複数のクライアントとの通信をスレッドにより行なう。
//プログラム終了は，コマンドプロンプトでCTRL-C
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class MultiServerSample 
{
  public static void main(String[] args){
	  new MultiServerSample();
	  
   }//mainend
  
  MultiServerSample(){//コンストラクター
	  
      ServerSocket serverSoc = null;
      try {
        // ポート番号は、5000
        //ソケットを作成
        serverSoc = new ServerSocket(5000);
        boolean flag=true;
      
        //クライアントからの接続を待機するaccept()メソッド。
        //accept()は、接続があるまで処理はブロックされる。
        //もし、複数のクライアントからの接続を受け付けるようにするには
        //スレッドを使う。
        //accept()は接続時に新たなsocketを返す。これを使って通信を行なう。
        System.out.println("Waiting for Connection. ");
        int thcounter=0;
        while(flag){
          Socket socket=null;
          socket = serverSoc.accept();
          //accept()は、クライアントからの接続要求があるまでブロックする。
          //接続があれば次の命令に移る。
          //スレッドを起動し、クライアントと通信する。
          new SrvWorkerThread(socket,thcounter++).start();
          
          System.out.println("Waiting for New Connection. ");
        }//while end
      }
      catch (IOException e) {
        System.out.println("IOException!");
        e.printStackTrace();
      }
      finally{
        try{
          if (serverSoc != null){
            serverSoc.close();
          }
        }
        catch (IOException ioex) {
          ioex.printStackTrace();
        }
      }//finally end

  }//コンストラクターMultiServerSample()end
}//class MultiServerSample end

class SrvWorkerThread extends Thread{
  private Socket soc;

  public SrvWorkerThread(Socket sct,int thcounter){
	
    soc=sct;
    System.out.println("Thread "+thcounter+"is Generated.  Connect to " + soc.getInetAddress());
  }

  public void run(){
    try{
      //socketからのデータはInputStreamReaderに送り、さらに
      //BufferedReaderによってバッファリングする。
      BufferedReader reader = new BufferedReader
        (new InputStreamReader(soc.getInputStream()));
      //Clientへの出力用PrintWriter
      PrintWriter sendout = new PrintWriter(soc.getOutputStream(), true);
      //データ読み取りと表示。
      String line;
      line = reader.readLine();
      System.out.println("Message from client :" + line);
      
      if(line.equals("end")){//強制終了
    	  System.exit(1);
      }
    	  
      //Clientにメッセージ送信
      sendout.println("Message is received at Server. Thankyou! your message is ["+line+"]");
      
    }
    catch(IOException ioex){
      ioex.printStackTrace();
    }
    finally{
      try{
        if(soc != null){
          soc.close();
        }
      }
      catch (IOException ioex){
        ioex.printStackTrace();
      }
    }//finall end
  }//run end
}//SrvWorkerThread end


