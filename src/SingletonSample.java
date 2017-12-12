public class SingletonSample {

    private static SingletonSample singleton
            = new SingletonSample();

    private SingletonSample(){

        System.out.println("インスタンスを作成しました。");

    }

    public static SingletonSample getInstance(){

        return singleton;
    }
}