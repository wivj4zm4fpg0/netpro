package networking.httpd;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.Arrays;

public class MyInvokeClass {
    public static void main(String[] args) {
        MyInvokeClass main = new MyInvokeClass();
        main.test("TestString");
    }

    public void test(Object obj) {
        Lookup lookup = MethodHandles.lookup();
        try { // 同じクラスを引数に取るコンストラクタを探す
            // 注意点は、Void.classじゃなくてVoid.TYPEが必要。
            MethodHandle mh = lookup.findConstructor(obj.getClass(),
                    MethodType.methodType(Void.class, obj.getClass()));
            Object retConstructor = mh.invoke(obj);
            // 確認
            System.out.println(retConstructor);
            System.out.println(retConstructor.getClass());
            // toCharArray()してみる。引数なし、戻り値あり（しかもプリミティブ配列)
            MethodHandle toCharArray = lookup.findVirtual(obj.getClass(),
                    "toCharArray", MethodType.methodType(char.class));
            Object retToCharArray = toCharArray.invoke(retConstructor);
            // 確認
            System.out.println(Arrays.toString((char[]) retToCharArray));
            // concat(String)してみる。引数あり、戻り値あり。
            MethodHandle concat = lookup.findVirtual(obj.getClass(), "concat",
                    MethodType.methodType(obj.getClass(), obj.getClass()));
            Object retConcat = concat.invoke(retConstructor, retConstructor);
            // 確認
            System.out.println(retConcat);
        } catch (Throwable e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }

    }
}
