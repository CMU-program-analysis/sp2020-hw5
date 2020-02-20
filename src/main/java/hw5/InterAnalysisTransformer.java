package hw5;

import soot.*;

import java.util.*;

public class InterAnalysisTransformer extends SceneTransformer {
    public static final String ANALYSIS_NAME = "wjap.intersignanalysis";

    private static InterAnalysisTransformer theInstance =
            new InterAnalysisTransformer();

    public static InterAnalysisTransformer getInstance() {
        return theInstance;
    }

    @Override
    protected void internalTransform(String phaseName, Map<String, String> options) {
        System.out.println("Starting interprocedural analysis");
        InterSignAnalysis analysis = InterSignAnalysis.getInstance();
        analysis.analyzeProgram(Scene.v().getMainMethod());
        // If you'd like to inspect the results of your interprocedural analysis
        // you can do it here
        System.out.println("Done");
    }
}
