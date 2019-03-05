package uk.co.alt236.jacocoxmlparser.printer;

import com.jakewharton.fliptables.FlipTable;
import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.xml.Package;
import uk.co.alt236.jacocoxmlparser.xml.Report;

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


        final String[] header = {"Package", "Classes", "Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %"};
        final String[][] data = new String[report.getPackages().size()][header.length];

        for (int i = 0; i < packages.size(); i++) {
            final Package pack = packages.get(i);

            final String name = pack.getName();
            final String classCount = String.valueOf(pack.getClasses().size());

            final CounterResult instructions = CounterResultFactory.getCounter(pack.getCounters(), "INSTRUCTION");
            final CounterResult branches = CounterResultFactory.getCounter(pack.getCounters(), "BRANCH");

            final Status instructionStatus = getStatus(instructions);
            final Status branchStatus = getStatus(branches);

            data[i] = new String[]{
                    name,
                    classCount,
                    colorize(instructionStatus, instructions.getItems()),
                    colorize(instructionStatus, instructions.getItemsCovered()),
                    colorize(instructionStatus, instructions.getItemsPercent()),
                    colorize(branchStatus, branches.getItems()),
                    colorize(branchStatus, branches.getItemsCovered()),
                    colorize(branchStatus, branches.getItemsPercent()),
            };
        }

        System.out.println(FlipTable.of(header, data));
    }

    private void packageGlobalStats(Report report) {
        final String[] header = {"Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %"};
        final String[][] data = new String[1][header.length];

        final CounterResult instructions = CounterResultFactory.getCounter(report.getCounters(), "INSTRUCTION");
        final CounterResult branches = CounterResultFactory.getCounter(report.getCounters(), "BRANCH");

        final Status instructionStatus = getStatus(instructions);
        final Status branchStatus = getStatus(branches);

        data[0] = new String[]{
                colorize(instructionStatus, instructions.getItems()),
                colorize(instructionStatus, instructions.getItemsCovered()),
                colorize(instructionStatus, instructions.getItemsPercent()),
                colorize(branchStatus, branches.getItems()),
                colorize(branchStatus, branches.getItemsCovered()),
                colorize(branchStatus, branches.getItemsPercent()),
        };

        System.out.println(FlipTable.of(header, data));
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
