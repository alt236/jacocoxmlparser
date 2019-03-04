package uk.co.alt236.jacocoxmlparser.printer;

import com.jakewharton.fliptables.FlipTable;
import uk.co.alt236.jacocoxmlparser.xml.Counter;
import uk.co.alt236.jacocoxmlparser.xml.Package;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.util.List;
import java.util.stream.Collectors;

public class ReportPrinter {

    public void print(Report report) {

        System.out.println("Jacoco Report for: " + report.getName());

        final List<Package> packages = report.getPackages();
        packages.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));


        final String[] header = {"Package", "Classes", "Instructions", "Instr. Covered", "Instr. %", "Branches", "Branches Covered", "Branches %"};
        final String[][] data = new String[report.getPackages().size()][header.length];

        for (int i = 0; i < packages.size(); i++) {
            final Package pack = packages.get(i);

            final String name = pack.getName();
            final String classCount = String.valueOf(pack.getClasses().size());

            // Instruction Coverage
            final Counter instructionCounter = getCounter(pack.getCounters(), "INSTRUCTION");
            final String instructions = String.valueOf(instructionCounter.getTotal());
            final String InstructionsCovered = String.valueOf(instructionCounter.getCovered());
            final String instructionPercent = toPercentage(instructionCounter.getPercentage()) + "%";

            // Branch Coverage
            final Counter branchCounter = getCounter(pack.getCounters(), "BRANCH");
            final String branches = String.valueOf(branchCounter.getTotal());
            final String branchesCovered = String.valueOf(branchCounter.getCovered());
            final String branchesPercent = toPercentage(branchCounter.getPercentage()) + "%";


            data[i] = new String[]{
                    name,
                    classCount,
                    instructions,
                    InstructionsCovered,
                    instructionPercent,
                    branches,
                    branchesCovered,
                    branchesPercent
            };
        }


        System.out.println(FlipTable.of(header, data));
    }

    private String toPercentage(double amount) {
        return String.format("%.0f", amount) + "%";
    }


    private Counter getCounter(final List<Counter> counters,
                               final String name) {
        final List<Counter> filtered = counters
                .stream()
                .filter(counter -> counter.getType().equals(name))
                .collect(Collectors.toList());

        return filtered.isEmpty() ? null : filtered.get(0);
    }
}
