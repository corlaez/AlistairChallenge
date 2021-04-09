import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class KWICTest {

    @Test
    public void lineShift() {
        String input = "ABC DEF GHI";
        List<String> output = KWIC.shifts(input);

        // Check shift
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("ABC DEF GHI");
        expectedOutput.add("DEF GHI ABC");
        expectedOutput.add("GHI ABC DEF");
        Assertions.assertEquals(expectedOutput, output);

        // Check that input was not mutated
        String expectedInput = "ABC DEF GHI";
        Assertions.assertEquals(expectedInput, input);
    }

    @Test
    public void orderedShifts() {
        String input = "" +
                "A AB ABC\n" +
                "XYZ XZ X";
        String output = KWIC.orderedShiftsKWIC(input);

        String expectedOutput = "" +
                "A AB ABC\n" +
                "AB ABC A\n" +
                "ABC A AB\n" +
                "X XYZ XZ\n" +
                "XYZ XZ X\n" +
                "XZ X XYZ";
        Assertions.assertEquals(expectedOutput, output);

        // Check that input was not mutated
        String expectedInput = "" +
                "A AB ABC\n" +
                "XYZ XZ X";
        Assertions.assertEquals(expectedInput, input);
    }

    @Test
    public void orderedShiftsKWICIsIdempotent() {
        String input = "" +
                "A AB ABC\n" +
                "XYZ XZ X";
        String output = KWIC.orderedShiftsKWIC(input);

        String expectedOutput = "" +
                "A AB ABC\n" +
                "AB ABC A\n" +
                "ABC A AB\n" +
                "X XYZ XZ\n" +
                "XYZ XZ X\n" +
                "XZ X XYZ";
        Assertions.assertEquals(expectedOutput, output);
        output = KWIC.orderedShiftsKWIC(input);
        Assertions.assertEquals(expectedOutput, output);
        output = KWIC.orderedShiftsKWIC(input);
        Assertions.assertEquals(expectedOutput, output);
    }
}
