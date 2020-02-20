package hw5;

import soot.*;
import soot.jimple.Stmt;
import soot.toolkits.graph.ExceptionalUnitGraph;

import java.util.*;

public class IntraAnalysisTransformer extends BodyTransformer {
    public static final String ANALYSIS_NAME = "jap.signanalysis";

    private static IntraAnalysisTransformer theInstance = new IntraAnalysisTransformer();

    public static IntraAnalysisTransformer getInstance() {
        return theInstance;
    }

    @Override
    protected void internalTransform(Body body, String phaseName, Map<String, String> options) {
        NormalUnitPrinter printer = new NormalUnitPrinter(body);
        IntraSignAnalysis analysis = new IntraSignAnalysis(new ExceptionalUnitGraph(body));

        // Print abstract values for all variables at all program points
        // You can view this output by running `gradle test --info`
        for (Unit unit: body.getUnits()) {
            Stmt stmt = (Stmt) unit;
            System.out.print(stmt.getClass() + ": ");
            stmt.toString(printer);
            System.out.println(printer.output());
            printer.output().setLength(0);
            System.out.println("\t" + analysis.sigmaAt.get(stmt).toString());
        }
    }
}