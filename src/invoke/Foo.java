package invoke;

public class Foo {

    public static void main(String[] args) {

        new Foo().hello();

    }

    public void hello() {
        System.out.println("hello refrection world!");
    }

    public int calculate(int a, int b) {

        return (a + b);
    }
}
