package hw5;

import common.ErrorMessage;
import common.Utils;
import soot.Local;
import soot.Unit;
import soot.ValueBox;
import soot.toolkits.graph.DominatorsFinder;
import soot.toolkits.graph.ExceptionalUnitGraph;
import soot.toolkits.graph.MHGDominatorsFinder;
import soot.toolkits.graph.UnitGraph;
import soot.toolkits.scalar.ForwardFlowAnalysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntraSignAnalysis extends ForwardFlowAnalysis {
    // Maps Units to sigmas;
    Map<Unit, Sigma> sigmaAt;

    // Holds the set of local variables
    private Set<Local> locals = new HashSet<>();

    // The calling context for the analysis
    // Null if no context (e.g., when only running intraprocedurally)
    private Context ctx;

    // The input sigma for this analysis
    private Sigma sigma_i;

    /**
     * Constructor with no context. This is useful for testing the intraprocedural
     * analysis on its own.
     */
    IntraSignAnalysis(UnitGraph graph) {
        this(graph, null, null);
    }

    /**
     * Allows creating an intra analysis with just the context and the input sigma,
     * since the unit graph can be grabbed from the function in the context.
     */
    IntraSignAnalysis(Context ctx, Sigma sigma_i) {
        this(new ExceptionalUnitGraph(ctx.fn.getActiveBody()), ctx, sigma_i);
    }

    IntraSignAnalysis(UnitGraph graph, Context ctx, Sigma sigma_i) {
        super(graph);
        this.ctx = ctx;
        sigmaAt = new HashMap<>(graph.size() * 2 + 1);

        // Collect locals
        DominatorsFinder<Unit> df = new MHGDominatorsFinder<>(graph);
        for (Unit s : graph) {
            for (Object d : df.getDominators(s)) {
                Unit dominator = (Unit) d;
                for (ValueBox box : dominator.getDefBoxes()) {
                    if (box.getValue() instanceof Local) {
                        locals.add((Local) box.getValue());
                    }
                }
            }
        }

        // TODO: Initialize all locals at all program points to ???
        for (Unit s : graph) {
            Sigma sigma = new Sigma();
            // TODO: You'll need to initialize sigma with some values before adding it
            sigmaAt.put(s, sigma);
        }

        if (sigma_i == null) {
            // TODO: Initialize sigma_i if one isn't passed in
        } else {
            this.sigma_i = sigma_i;
        }

        // Collect the analysis information
        doAnalysis();

        // Report warnings after analyzing
        reportWarnings();
    }

    /**
     * Report warnings. This will use the analysis results collected by the constructor.
     */
    private void reportWarnings() {
        // TODO: Implement this (raise warnings)!
        // TODO: This implementation is incorrect, but it shows how to report a warning
        for (Unit u : sigmaAt.keySet()) {
            // Reports an error for a definite negative index
            Utils.reportWarning(u, ErrorMessage.NEGATIVE_INDEX_ERROR);
            // Reports a warning for a possible negative index
            Utils.reportWarning(u, ErrorMessage.POSSIBLE_NEGATIVE_INDEX_WARNING);
        }
    }

    /**
     * Run flow function for this unit
     *
     * @param inValue  The initial Sigma at this point
     * @param unit     The current Unit
     * @param outValue The updated Sigma after the flow function
     */
    @Override
    protected void flowThrough(Object inValue, Object unit, Object outValue) {
        // TODO: Implement me!
    }

    /**
     * Initial flow information at the start of a method
     */
    @Override
    protected Object newInitialFlow() {
        // TODO: Implement me!
        return new Sigma();
    }

    /**
     * Initial flow information at each other program point
     */
    @Override
    protected Object entryInitialFlow() {
        // TODO: Implement me!
        return new Sigma();
    }

    /**
     * Join at a program point lifted to sets
     */
    @Override
    protected void merge(Object in1, Object in2, Object out) {
        // TODO: Implement me!
    }

    /**
     * Copy for sets
     */
    @Override
    protected void copy(Object source, Object dest) {
        // TODO: Implement me!
    }
}
