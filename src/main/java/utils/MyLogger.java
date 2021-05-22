package utils;

public class MyLogger {
    private static final boolean isEnabled = true;

    public static void log(String className, String msg){
        if (isEnabled){
            System.out.println("[" + className + "]: " + msg + "\n");
        }
    }
}
