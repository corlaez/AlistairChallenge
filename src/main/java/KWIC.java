import java.util.*;
import java.util.stream.Collectors;

class KWIC {

    static String orderedShiftsKWIC(String kwic) {
        return kwic.lines()
                .flatMap(line -> shifts(line).stream())
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    static List<String> shifts(String stringLine) {
        List<String> shiftedLines = new ArrayList<>();
        LinkedList<String> currentWords = new LinkedList<>(Arrays.asList(stringLine.split("[ ]+")));
        for (int i = 0; i < currentWords.size(); i++) {
            shiftedLines.add(String.join(" ", currentWords));
            currentWords.add(currentWords.remove(0));
        }
        return shiftedLines;
    }
}
