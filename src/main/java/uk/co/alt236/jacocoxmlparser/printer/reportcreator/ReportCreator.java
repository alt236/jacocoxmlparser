package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import uk.co.alt236.jacocoxmlparser.util.Colorizer;
import uk.co.alt236.jacocoxmlparser.xml.Report;

public abstract class ReportCreator {

    private final Colorizer colorizer;

    public ReportCreator(boolean colorise) {
        colorizer = new Colorizer(colorise);
    }

    public abstract String createReport(Report report);

    String colorize(Status status, String text) {
        switch (status) {

            case GOOD:
                return colorizer.green(text);
            case AVERAGE:
                return colorizer.blue(text);
            case BAD:
                return colorizer.red(text);
            default:
                return text;
        }
    }

    Status getStatus(CounterResult result) {
        final double value = result.getItemsPercentRaw();

        if (value >= 66) {
            return Status.GOOD;
        } else if (value >= 33) {
            return Status.AVERAGE;
        } else if (value >= 0) {
            return Status.BAD;
        } else {
            return Status.NOT_APPLICABLE;
        }
    }

    enum Status {
        GOOD,
        AVERAGE,
        BAD,
        NOT_APPLICABLE
    }
}
