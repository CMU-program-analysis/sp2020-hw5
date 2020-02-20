import common.ErrorMessage;
import common.Utils;
import hw5.IntraAnalysisTransformer;
import org.junit.*;
import soot.*;

public class IntraAnalysisTest extends AnalysisTest {
    void add_analysis() {
        analysisName = IntraAnalysisTransformer.ANALYSIS_NAME;
        PackManager.v().getPack("jap").add(
                new Transform(analysisName,
                        IntraAnalysisTransformer.getInstance())
        );
    }

    @Test
    public void testIntraAnalysis() {
        addTestClass("inputs.IntraTest");
        Main.main(getArgs());

        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 20);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 31);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 42);
        addExpected(ErrorMessage.NEGATIVE_INDEX_ERROR, 54);
        addExpected(ErrorMessage.POSSIBLE_NEGATIVE_INDEX_WARNING, 63);
        Assert.assertEquals(expected, Utils.getErrors());
    }
}
