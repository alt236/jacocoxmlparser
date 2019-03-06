package uk.co.alt236.jacocoxmlparser.printer.table;

import java.util.Arrays;
import java.util.List;

public class Line {
    private final List<String> items;

    private Line(List<String> items) {
        this.items = items;
    }

    public static Line fromStrings(String... items) {
        return new Line(Arrays.asList(items));
    }

    public String getItem(int i) {
        return items.get(i);
    }

    public int size() {
        return items.size();
    }
}
