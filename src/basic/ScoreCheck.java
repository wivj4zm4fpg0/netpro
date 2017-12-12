package basic;

//xが0,100の範囲外
//xが80以上ならよくできました
//xが80未満ならがんばりましょう。f

public class ScoreCheck {
	public static void main(String[] args) { 
		int x1=Integer.parseInt(args[0]);
		int x2=Integer.parseInt(args[1]);
		double a=(x1+x2)/2;
		if(a<0||100<a){
			System.err.println("入力が間違っています"+x1+","+x2);
			System.exit(0);
		}

		if(a>=80){
			System.out.println(a+" よくがんばりました");
		}else {
			System.out.println(a+" がんばりましょう！");
		}
	/*ここにelse if文を追加する。**/
	

	}//main end
}//class end

