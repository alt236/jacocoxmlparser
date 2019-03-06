package uk.co.alt236.jacocoxmlparser.printer.table;

import org.junit.Before;
import org.junit.Test;

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
}