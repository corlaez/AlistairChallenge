import java.util.*;
import java.util.stream.Collectors;

class KWIC extends OrderedSet<Line> {
    public KWIC(LinkedHashSet<Line> lines) {
        super(lines);
    }
    public KWIC(Line ... lines) {
        super(new LinkedHashSet<>(Arrays.asList(lines)));
    }

    KWIC orderedShiftsKWIC() {
        List<Line> lines = super.set.stream()
                .flatMap(line -> line.shifts().stream())
                .sorted(Comparator.comparing(Line::toString))
                .collect(Collectors.toList());
        return new KWIC(new LinkedHashSet<>(lines));
    }


    @Override
    public String toString() {
        return super.set.stream().map(Line::toString).collect(Collectors.joining("\n"));
    }
}

class Line extends OrderedSet<Word> {
    public Line(LinkedHashSet<Word> words) {
        super(words);
    }
    public Line(String ... words) {
        super(new LinkedHashSet<>(
                Arrays.stream(words)
                        .map(Word::new)
                        .collect(Collectors.toList())
        ));
    }

    Line shift() {
        List<Word> mutableWords = mutableListOfSet();
        Word word = mutableWords.remove(0);
        mutableWords.add(word);
        return new Line(new LinkedHashSet<>(mutableWords));
    }

    List<Line> shifts() {
        List<Line> shiftedLines = new ArrayList<>();
        Line currentLine = this;
        do {
            shiftedLines.add(currentLine);
            currentLine = currentLine.shift();
        } while (!this.equals(currentLine));
        return shiftedLines;
    }
}

class Word extends OrderedSet<Character> {
    public Word(LinkedHashSet<Character> chars) {
        super(chars);
    }
    public Word(String word) {
        super(new LinkedHashSet<>(word.chars().mapToObj(i -> (char)i).collect(Collectors.toList())));
    }

    @Override
    public String toString() {
        return super.set.stream().map(String::valueOf).collect(Collectors.joining());
    }
}

class OrderedSet<T> {
    public final List<T> set;

    public OrderedSet(LinkedHashSet<T> set) {
        this.set = List.copyOf(new ArrayList<>(set));
    }

    List<T> mutableListOfSet() {
        return new ArrayList<>(set);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedSet<?> that = (OrderedSet<?>) o;
        return set.equals(that.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
