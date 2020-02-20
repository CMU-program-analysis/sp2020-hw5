package hw5;

import soot.SootMethod;

import java.util.*;

public class InterSignAnalysis {
    public static Set<Context> worklist;
    public static Stack<Context> analyzing;
    public static Map<Context, Summary> results;
    public static Map<Context, Set<Context>> callers;
    private static InterSignAnalysis theInstance = new InterSignAnalysis();

    static {
        worklist = new HashSet<>();
        analyzing = new Stack<>();
        results = new HashMap<>();
        callers = new HashMap<>();
    }

    /**
     * We're using a singleton pattern here so that analysis results can be
     * looked up as they're collected.
     * e.g.,
     *   InterSignAnalysis.getInstance().callers can be accessed from IntraSignAnalysis
     */
    public static InterSignAnalysis getInstance() {
        return theInstance;
    }

    public static void resetAnalysis() {
        theInstance = new InterSignAnalysis();
    }

    void analyzeProgram(SootMethod main) {
        // TODO: Implement me!
    }

    void analyze(Context ctx, Sigma sigma_i) {
        // TODO: Implement me!
        // A good strategy will likely involve passing the context and the input
        // sigma to the SignAnalysis and then looking at the results to extract
        // the new sigma.
    }
}
