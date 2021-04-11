import java.util.*;

class KWICLineShifter {

    public KWIC shift(Line lineToShift, KWIC kwic) {
        int index = kwic.mutableListOfSet().indexOf(lineToShift);
        return replace(index, shift(lineToShift), kwic);
    }

    private KWIC replace(int index, Line line, KWIC kwic) {
        List<Line> mutableLines = kwic.mutableListOfSet();
        mutableLines.set(index, line);
        return new KWIC(new LinkedHashSet<>(mutableLines));
    }

    private Line shift(Line line) {
        List<Word> mutableWords = line.mutableListOfSet();
        Word word = mutableWords.remove(0);
        mutableWords.add(word);
        return new Line(new LinkedHashSet<>(mutableWords));
    }
}

class Word extends OrderedSet<Character> {
    Word(LinkedHashSet<Character> chars) {
        super(chars);
    }
}

class Line extends OrderedSet<Word> {
    Line(LinkedHashSet<Word> words) {
        super(words);
    }
}

class KWIC extends OrderedSet<Line> {
    public KWIC(LinkedHashSet<Line> lines) {
        super(lines);
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
}
