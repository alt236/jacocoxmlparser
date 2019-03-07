package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import uk.co.alt236.jacocoxmlparser.printer.table.CliTable;
import uk.co.alt236.jacocoxmlparser.printer.table.Line;
import uk.co.alt236.jacocoxmlparser.xml.Package;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.util.ArrayList;
import java.util.List;

public class PackageReportCreator extends ReportCreator {

    public PackageReportCreator(boolean colorise) {
        super(colorise);
    }

    @Override
    public String createReport(Report report) {
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

        return CliTable.from(header, lines);
    }

}
