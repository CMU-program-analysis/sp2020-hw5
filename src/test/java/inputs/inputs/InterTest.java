package inputs;

public class InterTest {

    public static int neg(int n) {
        return -1 * n;
    }

    public static int more_neg(int n) {
        return neg(n) - 1;
    }

    public static int even_more_neg(int n) {
        return more_neg(n) - 1;
    }

    public static void main(String[] args) {
        int x, y, z, w;
        int[] array = new int[5];
        int ignore;

        if (condition()) {
            y = 1;
            z = -1;
        } else {
            y = 2;
            z = -2;
        }

        ignore = array[y]; // OK (y -> P)
        ignore = array[z]; // ERROR (z -> N)

        // Analysis stack:
        // (fn: neg, ctx: neg@35, input: P) = N
        w = neg(y);

        // Analysis stack:
        // (fn: neg, ctx: neg@39, input: N) = P
        x = neg(z);

        ignore = array[w]; // ERROR (w -> N)
        ignore = array[x]; // OK (x -> P)

        // Analysis stack:
        // (fn: more_neg, ctx: more_neg@45, input: P) =
        // (fn: neg, ctx: more_neg@45, neg@10, input: P) = N
        w = more_neg(y);

        // Analysis stack:
        // (fn: more_neg, ctx: more_neg@52, input: N) = P
        // (fn: neg, ctx: more_neg@52, neg@10, input: N) = T
        x = more_neg(z);

        ignore = array[w]; // ERROR (w -> N)
        ignore = array[x]; // WARNING (x -> T)

        // Analysis stack:
        // (fn: even_more_neg, ctx: even_more_neg@61, input: P) =
        // (fn: more_neg, ctx: even_more_neg@61, more_neg@14, input: P) =
        // (fn: neg, ctx: more_neg@14, neg@10, input: P) = N
        w = even_more_neg(y);

        // Analysis stack:
        // (fn: even_more_neg, ctx: even_more_neg@68, input: N) =
        // (fn: more_neg, ctx: even_more_neg@68, more_neg@14, input: N) =
        // (fn: more_neg, ctx: more_neg@14, neg@10, input: N) =
        // join(P, N) = T (because we have seen this context before and its result was N)
        x = even_more_neg(z);

        ignore = array[w]; // ERROR: (w -> N)
        ignore = array[x]; // WARNING: (x -> T)
    }
    public static boolean condition() { return false; }
}
