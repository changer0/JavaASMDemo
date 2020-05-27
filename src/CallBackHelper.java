public class CallBackHelper {
    public static void before(TestClass testClass, int a) {
        System.out.println("before insert ：" + a + " testClass:" + testClass);
    }

    public static void after(TestClass testClass, int a) {
        System.out.println("after insert ：" + a + " testClass:" + testClass);
    }
}
