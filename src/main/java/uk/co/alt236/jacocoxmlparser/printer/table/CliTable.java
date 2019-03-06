package uk.co.alt236.jacocoxmlparser.printer.table;

import java.util.List;

public final class CliTable {

    public static String from(Line header,
                              List<Line> lines) {
        final int headerSize = header.size();
        validate(lines, headerSize);
        return new TableBuilder().createTable(header, lines);
    }

    private static void validate(List<Line> lines, int headerSize) {

        for (int i = 0; i < lines.size(); i++) {
            int lineSize = lines.get(i).size();
            if (lineSize != headerSize) {
                throw new IllegalArgumentException("Line index " + i + " has unexpected number of items. Expected " + headerSize + " but got " + lineSize);
            }
        }
    }
}
