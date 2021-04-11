import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KWICTest {

    @Test
    public void orderedSetCannotBeInstantiatedWithANullSet() {
        Assertions.assertThrows(NullPointerException.class, () ->
                new OrderedSet<>(null)
        );
    }

    @Test
    public void lineShift() {
        Line input = new Line("ABC", "DEF", "GHI");
        Line output = input.shift();

        // Check shift
        Line expectedOutput = new Line("DEF", "GHI", "ABC");
        Assertions.assertEquals(expectedOutput, output);

        // Check that input was not mutated
        Line expectedInput = new Line("ABC", "DEF", "GHI");
        Assertions.assertEquals(expectedInput, input);
    }

    @Test
    public void orderedShifts() {
        KWIC input = new KWIC(
                new Line("A", "AB", "ABC"),
                new Line("A", "AB", "ABC"),
                new Line("X", "XYZ", "XZ")
        );
        String output = input.orderedShiftsKWIC().toString();

        String expectedOutput = "" +
                "[A, AB, ABC]\n" +
                "[AB, ABC, A]\n" +
                "[ABC, A, AB]\n" +
                "[X, XYZ, XZ]\n" +
                "[XYZ, XZ, X]\n" +
                "[XZ, X, XYZ]";
        Assertions.assertEquals(expectedOutput, output);

        // Check that input was not mutated
        KWIC expectedInput = new KWIC(
                new Line("A", "AB", "ABC"),
                new Line("A", "AB", "ABC"),
                new Line("X", "XYZ", "XZ")
        );
        Assertions.assertEquals(expectedInput, input);
    }

    @Test
    public void orderedShiftsKWICIsIdempotent() {
        KWIC input = new KWIC(
                new Line("A", "AB", "ABC"),
                new Line("A", "AB", "ABC"),
                new Line("X", "XYZ", "XZ")
        ).orderedShiftsKWIC();
        String output = input.orderedShiftsKWIC().toString();

        String expectedOutput = "" +
                "[A, AB, ABC]\n" +
                "[AB, ABC, A]\n" +
                "[ABC, A, AB]\n" +
                "[X, XYZ, XZ]\n" +
                "[XYZ, XZ, X]\n" +
                "[XZ, X, XYZ]";
        Assertions.assertEquals(expectedOutput, output);

        // Check that input was not mutated
        KWIC expectedInput = new KWIC(
                new Line("A", "AB", "ABC"),
                new Line("A", "AB", "ABC"),
                new Line("X", "XYZ", "XZ")
        ).orderedShiftsKWIC();
        Assertions.assertEquals(expectedInput, input);
    }
}
