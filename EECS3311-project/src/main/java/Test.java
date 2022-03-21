public class Test {
    public static void TestRun(){
        System.out.println("TestRun");
        verify();
    }

    public static void verify(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        System.out.println(stackTrace[0] + ", " + stackTrace[1] + ", " + stackTrace[2] + ", " + stackTrace[3].getClassName() + ", " + stackTrace.length);
    }
}
