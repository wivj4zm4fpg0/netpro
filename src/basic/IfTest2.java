package basic;

//xが7,8,9ながbignumber
//xが4,5,6ならmiddle number
//xが1,2,3ならsmall numberを表示するプログラムを書け


public class IfTest2 {
	public static void main(String[] args) { 
		int x=Integer.parseInt(args[0]);
		if(x>=10||x<=0){
			System.err.println("out of scope");
			System.exit(0);
		}

		if(x>=7){
			System.out.println(x+" is Big Number");
		}
		else if(x>=4){
			System.out.println(x+" is Middle Number");
		}else {
			System.out.println(x+" is Small Number");
		}
	/*ここにelse if文を追加する。**/
	

	}//main end
}//class end

