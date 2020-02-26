package inputs;

public class IntraTest {
    public static void test1() {
        int x, y, z, w;
        int[] array = new int[5];
        x = 0;
        y = 5;
        z = -3;
        y = y * y;
        if (condition())
            w = y * x;
        else
            w = x * z;
        while (condition())
            z = y * z;
        int ignore = array[w];
        ignore = array[x];
        ignore = array[y];
        ignore = array[z]; // ERROR
    }

    public static void test2() {
        int x, y, z, w;
        int[] array = new int[5];
        y = -5;
        z = -3;
        y = y * y;
        z = z + z;
        int ignore = array[y];
        ignore = array[z]; // ERROR
    }

    public static void test3() {
        int x, y, z, w;
        int[] array = new int[5];
        y = 5;
        z = -3;
        w = y - z;
        int v = z - y;
        int ignore = array[w];
        ignore = array[v]; // ERROR
    }

    public static void test4() {
        int x, y, z, w;
        int[] array = new int[5];
        y = 5;
        z = -3;
        if (condition())
            w = y;
        else
            w = z;
        int ignore = array[w]; // WARNING
    }

    public static void test5() {
        int x, y, z, w;
        int[] array = new int[5];
        x = getInt();
        y = 5;
        z = x * y;
        int ignore = array[z]; // WARNING
    }

    public static int getInt() {
        return 0;
    }

    public static boolean condition() {
        return true;
    }
}
