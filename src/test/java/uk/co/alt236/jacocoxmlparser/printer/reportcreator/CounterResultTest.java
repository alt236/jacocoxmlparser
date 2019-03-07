package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import org.junit.Test;
import uk.co.alt236.jacocoxmlparser.xml.Counter;

import static org.junit.Assert.assertEquals;

public class CounterResultTest {

    @Test
    public void defaultConstructorCreatesObjectWithNoData() {
        final CounterResult cut = new CounterResult();

        assertEquals("Items", "-", cut.getItems());
        assertEquals("Items Covered", "-", cut.getItemsCovered());
        assertEquals("Items Percent", "-", cut.getItemsPercent());
        assertEquals("Items Percent Raw", -1, cut.getItemsPercentRaw(), 0);
    }

    @Test
    public void parsesCounterObjectSuccessfully() {
        final Counter counter = new Counter("foo", 7, 2);
        final CounterResult cut = new CounterResult(counter);

        assertEquals("Items Percent Raw", 77.0, cut.getItemsPercentRaw(), 0);
        assertEquals("Items Percent", "77%", cut.getItemsPercent());
        assertEquals("Items Covered", "7", cut.getItemsCovered());
        assertEquals("Items Total", "9", cut.getItems());
    }
}