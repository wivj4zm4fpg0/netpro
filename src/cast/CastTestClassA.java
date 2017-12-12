package cast;

public class CastTestClassA {
    public static void main(String[] args) {
        new CastTestClassA();

    }

    CastTestClassA(){
        TestB tb1 = new TestB();
        tb1.printA();	//testA と testB-A
        tb1.printB();	//testB

        TestA ta1 =  tb1;
        ta1.printA();	//testA と testB-A
//		ta1.printB(); //コンパイルエラーß
    }


    class TestA {
        public void printA() {
            System.out.println("testA");
        }
    }
    // 子クラス (TestAクラスを継承)
    class TestB extends TestA {
        public void printA() {
            super.printA();
            System.out.println("testB-A");
        }
        public void printB() {
            System.out.println("testB");
        }
    }

}
