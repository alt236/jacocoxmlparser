package uk.co.alt236.jacocoxmlparser.printer;

import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.printer.reportcreator.GlobalReportCreator;
import uk.co.alt236.jacocoxmlparser.printer.reportcreator.PackageReportCreator;
import uk.co.alt236.jacocoxmlparser.xml.Report;

public class ReportPrinter {

    private final boolean colorize;
    private final boolean printGlobalStats;
    private final boolean printPackageStats;

    public ReportPrinter(CommandLineOptions options) {
        colorize = options.isColorise();
        printGlobalStats = options.isPrintGlobalStats();
        printPackageStats = options.isPrintPackageStats();
    }

    public void print(Report report) {
        System.out.println("Jacoco Report for: " + report.getName());

        if (printGlobalStats) {
            final String text = new GlobalReportCreator(colorize).createReport(report);
            System.out.println(text);
        } else if (printPackageStats) {
            final String text = new PackageReportCreator(colorize).createReport(report);
            System.out.println(text);
        } else {
            throw new IllegalStateException("No report output was configured");
        }
    }

}
