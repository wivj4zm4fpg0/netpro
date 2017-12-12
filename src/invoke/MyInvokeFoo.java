package invoke;

/*
 * リフレクション (reflection) とは，
 * プログラムの実行過程でプログラム自身の構造を読み取ったり
 * 書き換えたりする技術のことである。
 *
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvokeFoo {

    public static void main(String args[]) {
        Class cls;
        try {

            //クラスを探します。
            cls = Class.forName("invoke.Foo");
            //helloというmethodをさがします。
            Method method = cls.getMethod("hello");
            //そのクラスをインスタンス化し上記のメソッドを実行します。
            method.invoke(cls.newInstance());
            new MyInvokeFoo().methodArgsTest();


        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

    }


    public void methodArgsTest() {
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            Class thisClass = cl.loadClass("invoke.Foo");
            Object obj = thisClass.newInstance();

            String msg = obj.toString();
            System.out.println(msg);

            //--------------------------------------------------
            // obj が calculateメソッドを持っていたら、
            // 2+3 を計算させる
            // ( 該当するメソッドを持っていないと、例外が飛ぶ )
            //--------------------------------------------------
            Class objClass = obj.getClass();
            Class[] params = new Class[]{int.class, int.class};
            Method method = objClass.getDeclaredMethod("calculate", params);

            Object[] args = new Object[]{new Integer(2), new Integer(3)};
            Object result = method.invoke(obj, args);

            // 戻り値は Integer のはず
            Integer intResult = (Integer) result;
            System.out.println(intResult.toString());


        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }

    }
}