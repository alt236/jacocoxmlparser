package uk.co.alt236.jacocoxmlparser.printer.table;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TableBuilderTest {
    private TableBuilder cut;

    @Before
    public void setUp() {
        cut = new TableBuilder();
    }

    @Test
    public void printsSingleRowTable() {
        final Line header = Line.fromStrings("Size", "Event", "Time");

        final List<Line> lines = Collections.singletonList(
                Line.fromStrings("Big", "Game", "Tonight")
        );


        final String expected =
                "--------------------------\n" +
                        "| Size | Event |    Time |\n" +
                        "--------------------------\n" +
                        "| Big  |  Game | Tonight |\n" +
                        "--------------------------\n";

        final String result = cut.createTable(header, lines);
        assertEquals(expected, result);
    }
}