import common.ErrorMessage;
import common.Utils;
import hw5.InterAnalysisTransformer;
import org.junit.Assert;
import org.junit.Test;
import soot.Main;
import soot.PackManager;
import soot.Transform;
import soot.options.Options;

public class InterAnalysisTest extends AnalysisTest {
    void add_analysis() {
        analysisName = InterAnalysisTransformer.ANALYSIS_NAME;
        PackManager.v().getPack("wjap").add (
                new Transform(analysisName,
                        InterAnalysisTransformer.getInstance())
        );

        // These options are required when analyzing a whole program
        Options.v().set_whole_program(true);
        Options.v().set_app(true);
    }

    @Test
    public void testInterAnalysis() {
        addTestClass("inputs.InterTest");
        Main.main(getArgs());

        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 31);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 41);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 54);
        addExpected(ErrorMessage.POSSIBLE_NEGATIVE_INDEX_WARNING, 55);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 70);
        addExpected(ErrorMessage.POSSIBLE_NEGATIVE_INDEX_WARNING, 71);
        Assert.assertEquals(expected, Utils.getErrors());
    }
}
