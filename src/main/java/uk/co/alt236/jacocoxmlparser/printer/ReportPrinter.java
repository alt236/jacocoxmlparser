package uk.co.alt236.jacocoxmlparser.printer;

import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.printer.table.CliTable;
import uk.co.alt236.jacocoxmlparser.printer.table.Line;
import uk.co.alt236.jacocoxmlparser.xml.Package;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportPrinter {

    private final Colorizer colorizer;
    private final boolean printGlobalStats;
    private final boolean printPackageStats;

    public ReportPrinter(CommandLineOptions options) {
        colorizer = new Colorizer(options.isColorise());
        printGlobalStats = options.isPrintGlobalStats();
        printPackageStats = options.isPrintPackageStats();
    }


    public void print(Report report) {


        System.out.println("Jacoco Report for: " + report.getName());

        if (printGlobalStats) {
            packageGlobalStats(report);
        }

        if (printPackageStats) {
            packageStats(report);
        }
    }

    private void packageStats(Report report) {
        final List<Package> packages = report.getPackages();
        packages.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));

        final Line header = Line.fromStrings("Package", "Classes", "Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %");
        final List<Line> lines = new ArrayList<>();

        for (final Package pack : packages) {
            final String name = pack.getName();
            final String classCount = String.valueOf(pack.getClasses().size());

            final CounterResult instructions = CounterResultFactory.getCounter(pack.getCounters(), "INSTRUCTION");
            final CounterResult branches = CounterResultFactory.getCounter(pack.getCounters(), "BRANCH");

            final Status instructionStatus = getStatus(instructions);
            final Status branchStatus = getStatus(branches);

            final Line line = Line.fromStrings(
                    name,
                    classCount,
                    colorize(instructionStatus, instructions.getItems()),
                    colorize(instructionStatus, instructions.getItemsCovered()),
                    colorize(instructionStatus, instructions.getItemsPercent()),
                    colorize(branchStatus, branches.getItems()),
                    colorize(branchStatus, branches.getItemsCovered()),
                    colorize(branchStatus, branches.getItemsPercent())
            );

            lines.add(line);
        }

        System.out.println(CliTable.from(header, lines));
    }

    private void packageGlobalStats(Report report) {

        final Line header = Line.fromStrings("Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %");
        final List<Line> lines = new ArrayList<>();

        final CounterResult instructions = CounterResultFactory.getCounter(report.getCounters(), "INSTRUCTION");
        final CounterResult branches = CounterResultFactory.getCounter(report.getCounters(), "BRANCH");

        final Status instructionStatus = getStatus(instructions);
        final Status branchStatus = getStatus(branches);

        final Line line = Line.fromStrings(
                colorize(instructionStatus, instructions.getItems()),
                colorize(instructionStatus, instructions.getItemsCovered()),
                colorize(instructionStatus, instructions.getItemsPercent()),
                colorize(branchStatus, branches.getItems()),
                colorize(branchStatus, branches.getItemsCovered()),
                colorize(branchStatus, branches.getItemsPercent())
        );

        lines.add(line);
        System.out.println(CliTable.from(header, lines));
    }


    private String colorize(Status status, String text) {
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

    private Status getStatus(CounterResult result) {
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

    private enum Status {
        GOOD,
        AVERAGE,
        BAD,
        NOT_APPLICABLE
    }
}
