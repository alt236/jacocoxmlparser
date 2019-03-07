package uk.co.alt236.jacocoxmlparser.printer.table;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class ColumnMaxLengthTest {

    private ColumnMaxLength cut;

    @Before
    public void setUp() {
        cut = new ColumnMaxLength();
    }

    @Test
    public void calculatesOneLineWithOneItem() {
        final Line line = Line.fromStrings("foo");
        final List<Line> lines = Collections.singletonList(line);

        final int[] expected = new int[]{3};

        assertArrayEquals(expected, cut.calculate(lines));
    }

    @Test
    public void calculatesOneLineWithOneItemOfZeroLength() {
        final Line line = Line.fromStrings("");
        final List<Line> lines = Collections.singletonList(line);

        final int[] expected = new int[]{0};

        assertArrayEquals(expected, cut.calculate(lines));
    }

    @Test
    public void calculatesThreeLineWithThreeItems() {
        final Line line1 = Line.fromStrings("111", "2", "3");
        final Line line2 = Line.fromStrings("1", "222", "3");
        final Line line3 = Line.fromStrings("1", "2", "333");
        final List<Line> lines = Arrays.asList(line1, line2, line3);

        final int[] expected = new int[]{3, 3, 3};

        assertArrayEquals(expected, cut.calculate(lines));
    }
}