package uk.co.alt236.jacocoxmlparser.printer;

import com.jakewharton.fliptables.FlipTable;
import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.xml.Package;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.util.List;

public class ReportPrinter {

    public void print(CommandLineOptions options, Report report) {

        System.out.println("Jacoco Report for: " + report.getName());

        if (options.isPringGlobalStats()) {
            packageGlobalStats(report);
        }

        if (options.isPringPackageStats()) {
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

            data[i] = new String[]{
                    name,
                    classCount,
                    instructions.getItems(),
                    instructions.getItemsCovered(),
                    instructions.getItemsPercent(),
                    branches.getItems(),
                    branches.getItemsCovered(),
                    branches.getItemsPercent()
            };
        }

        System.out.println(FlipTable.of(header, data));
    }

    private void packageGlobalStats(Report report) {
        final String[] header = {"Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %"};
        final String[][] data = new String[1][header.length];

        final CounterResult instructions = CounterResultFactory.getCounter(report.getCounters(), "INSTRUCTION");
        final CounterResult branches = CounterResultFactory.getCounter(report.getCounters(), "BRANCH");

        data[0] = new String[]{
                instructions.getItems(),
                instructions.getItemsCovered(),
                instructions.getItemsPercent(),
                branches.getItems(),
                branches.getItemsCovered(),
                branches.getItemsPercent()
        };

        System.out.println(FlipTable.of(header, data));
    }
}
