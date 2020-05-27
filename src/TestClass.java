
public class TestClass {
    public static void main(String args[]) {
        new TestClass().insertFun(123654);
    }
    private void insertFun(int a) {
        CallBackHelper.before(this, a);
        System.out.println("running");
        CallBackHelper.after(this, a);
    }
}



