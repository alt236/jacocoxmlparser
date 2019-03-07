package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import org.junit.Test;
import testonly.TestReportReader;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import static org.junit.Assert.assertEquals;

public class GlobalReportCreatorTest {

    @Test
    public void printsCorrectGlobalReport() throws Exception {
        final Report report = TestReportReader.readTestReport(getClass().getClassLoader(), "test_jacoco.xml");
        final ReportCreator cut = new GlobalReportCreator(false);

        final String reportText = cut.createReport(report);

        final String expected = "---------------------------------------------------------------------------------------\n" +
                "| Instructions | Instr. Covered | Instr. % | Branches | Branches Covered | Branches % |\n" +
                "---------------------------------------------------------------------------------------\n" +
                "| 1575         |            448 |      28% |       54 |               18 |        33% |\n" +
                "---------------------------------------------------------------------------------------\n";

        assertEquals(expected, reportText);
    }
}