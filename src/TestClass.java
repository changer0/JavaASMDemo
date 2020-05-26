public class TestClass {
    public static void main(String args[]) {
        new TestClass().newFun();
    }
    private long funA(int n, String s, int[] arr) {
        System.out.println("funA");
        return 0;
    }

    private void newFun() {
        //System.out.println("before insert");
        System.out.println("running");
        //System.out.println("after insert");

        fun("a");
    }

    private void fun(String a) {

    }

    public void sample1(){
        int num = 5;
    }
    public int sample2(int a, int b) {
        return a + b;
    }
}
