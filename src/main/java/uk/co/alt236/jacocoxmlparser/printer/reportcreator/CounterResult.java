package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import uk.co.alt236.jacocoxmlparser.xml.Counter;

class CounterResult {
    private static final String NO_DATA = "-";
    private final String items;
    private final String itemsCovered;
    private final String itemsPercent;
    private final double itemPercentRaw;

    CounterResult(Counter counter) {
        items = String.valueOf(counter.getTotal());
        itemsCovered = String.valueOf(counter.getCovered());
        itemPercentRaw = Math.floor(counter.getPercentage());
        itemsPercent = toPercentage(itemPercentRaw);
    }

    CounterResult() {
        items = NO_DATA;
        itemsCovered = NO_DATA;
        itemsPercent = NO_DATA;
        itemPercentRaw = -1;
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

    public double getItemsPercentRaw() {
        return itemPercentRaw;
    }

    private String toPercentage(double amount) {
        return String.format("%.0f", amount) + "%";
    }
}
