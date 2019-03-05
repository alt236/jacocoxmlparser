package uk.co.alt236.jacocoxmlparser.printer;

import uk.co.alt236.jacocoxmlparser.xml.Counter;

class CounterResult {
    private static final String NO_DATA = "-";
    private final String items;
    private final String itemsCovered;
    private final String itemsPercent;

    CounterResult(Counter counter) {
        items = String.valueOf(counter.getTotal());
        itemsCovered = String.valueOf(counter.getCovered());
        itemsPercent = toPercentage(counter.getPercentage());
    }

    CounterResult() {
        items = NO_DATA;
        itemsCovered = NO_DATA;
        itemsPercent = NO_DATA;
    }

    public String getItems() {
        return items;
    }

    public String getItemsCovered() {
        return itemsCovered;
    }

    public String getItemsPercent() {
        return itemsPercent;
    }

    private String toPercentage(double amount) {
        return String.format("%.0f", Math.floor(amount)) + "%";
    }
}
