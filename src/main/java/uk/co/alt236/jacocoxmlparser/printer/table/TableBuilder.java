package uk.co.alt236.jacocoxmlparser.printer.table;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

class TableBuilder {
    private static final Pattern ANSI_COLOR_CODES_PATTERN = Pattern.compile("\u001B\\[[;\\d]*m");
    private final static ColumnMaxLength columnMaxLength = new ColumnMaxLength();

    private static final char V_LINE = '|';
    private static final char H_LINE = '-';
    private static final char SPACE = ' ';

    String createTable(Line header,
                       List<Line> lines) {

        final List<Line> allLines = new ArrayList<>();
        allLines.add(header);
        allLines.addAll(lines);

        return createTable(allLines);
    }

    private String createTable(List<Line> lines) {
        final StringBuilder sb = new StringBuilder();
        final int[] maxWidths = columnMaxLength.calculate(lines);

        final String horizontalLine = createHorizontalLine(maxWidths);

        sb.append(horizontalLine);

        for (int i = 0; i < lines.size(); i++) {
            addLine(sb, maxWidths, lines.get(i));
            if (i == 0) {
                sb.append(horizontalLine);
            }
        }

        sb.append(horizontalLine);

        return sb.toString();
    }

    private void addLine(StringBuilder sb, int[] maxWidths, Line line) {
        sb.append(V_LINE);
        for (int i = 0; i < maxWidths.length; i++) {
            sb.append(SPACE);

            final String lineText = line.getItem(i);
            final int padSize = calculatePadSize(lineText, maxWidths[i]);


            if (i == 0) {
                sb.append(StringUtils.rightPad(lineText, padSize));
            } else {
                sb.append(StringUtils.leftPad(lineText, padSize));
            }
            sb.append(SPACE);
            sb.append(V_LINE);
        }

        sb.append('\n');
    }

    private int calculatePadSize(String lineText, int expectedPadSize) {
        final int strippedTextLength = getSizeOfStringWithoutAsciiColors(lineText);

        if (lineText.length() > strippedTextLength) {
            return expectedPadSize + (lineText.length() - strippedTextLength);
        } else {
            return expectedPadSize;
        }
    }


    private int getSizeOfStringWithoutAsciiColors(final String string) {
        return ANSI_COLOR_CODES_PATTERN.matcher(string).replaceAll("").length();
    }

    private String createHorizontalLine(int[] maxWidths) {
        final int sum = IntStream.of(maxWidths).sum();

        // There are 3 characters added per field (2 spaces and a pipe),
        // plus an extra one pipe for the first one
        final int lineSize = sum + (3 * maxWidths.length) + 1;

        return StringUtils.leftPad("", lineSize, H_LINE) + "\n";
    }
}