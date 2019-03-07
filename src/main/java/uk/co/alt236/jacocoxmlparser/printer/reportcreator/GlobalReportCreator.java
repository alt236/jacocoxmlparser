package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import uk.co.alt236.jacocoxmlparser.printer.table.CliTable;
import uk.co.alt236.jacocoxmlparser.printer.table.Line;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.util.ArrayList;
import java.util.List;

public class GlobalReportCreator extends ReportCreator {

    public GlobalReportCreator(boolean colorise) {
        super(colorise);
    }

    @Override
    public String createReport(Report report) {

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

        return CliTable.from(header, lines);
    }

}
