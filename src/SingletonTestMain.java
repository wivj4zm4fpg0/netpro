
public class SingletonTestMain {

	public static void main(String[] args) {
		//SingletonSampleを実行してみよう。
		SingletonSample singleton1= SingletonSample.getInstance();
		SingletonSample singleton2= SingletonSample.getInstance();

		if(singleton1==singleton1){
			System.out.println(singleton1);
			System.out.println(singleton2);
			System.out.println("Singleton1とSingleton2は同一です");
		}
		
	}

}
