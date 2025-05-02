import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static <BuiltInClassLoadersDemo> void main(String[] args) {
        Main mainDemo = new Main();
        ClassLoader applicationClassLoader = mainDemo.getClass().getClassLoader();
        printClassLoaderDetails(applicationClassLoader);

        int i = 1;                      // 1
        Object o = new Object();        // 2
        Integer ii = 2;                 // 3
        printAll(o, i, ii);             // 4

        ClassLoader applicationClassLoader2 = args.getClass().getClassLoader();
        printClassLoaderDetails(applicationClassLoader2);
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        System.out.println(o.toString() + i + ii);  // 6
    }

    public static void printClassLoaderDetails(ClassLoader classLoader) {
        if (classLoader != null) {
            System.out.println("ClassLoader name : " + classLoader.getName());
            System.out.println("ClassLoader class : " + classLoader.getClass().getName());
        } else {
            System.out.println("Bootstrap classloader");
        }

    }
}
