package uk.co.alt236.jacocoxmlparser.printer.table;

import java.util.List;

class ColumnMaxLength {

    private static void setMaximums(int[] maxWidths, Line line) {
        for (int i = 0; i < line.size(); i++) {
            int textSize = line.getItem(i).length();

            if (textSize > maxWidths[i]) {
                maxWidths[i] = textSize;
            }
        }
    }

    int[] calculate(List<Line> lines) {
        int[] maxWidths = new int[lines.get(0).size()];

        for (Line line : lines) {
            setMaximums(maxWidths, line);
        }

        return maxWidths;
    }
}
