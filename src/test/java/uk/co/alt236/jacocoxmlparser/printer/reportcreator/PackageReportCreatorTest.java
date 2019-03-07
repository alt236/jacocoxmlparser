package uk.co.alt236.jacocoxmlparser.printer.reportcreator;

import org.junit.Test;
import testonly.TestReportReader;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import static org.junit.Assert.assertEquals;

public class PackageReportCreatorTest {

    @Test
    public void printsCorrectReport() throws Exception {
        final Report report = TestReportReader.readTestReport(getClass().getClassLoader(), "test_jacoco.xml");
        final ReportCreator cut = new PackageReportCreator(false);

        final String reportText = cut.createReport(report);

        final String expected =
                "------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "| Package                                            | Classes | Instructions | Instr. Covered | Instr. % | Branches | Branches Covered | Branches % |\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "| uk/co/alt236/jacocoxmlparser                       |       2 |          126 |              0 |       0% |        8 |                0 |         0% |\n" +
                        "| uk/co/alt236/jacocoxmlparser/cli                   |       4 |          213 |              0 |       0% |        - |                - |          - |\n" +
                        "| uk/co/alt236/jacocoxmlparser/parser                |       1 |           51 |             51 |     100% |        - |                - |          - |\n" +
                        "| uk/co/alt236/jacocoxmlparser/printer               |       1 |           62 |             37 |      59% |        4 |                2 |        50% |\n" +
                        "| uk/co/alt236/jacocoxmlparser/printer/reportcreator |       6 |          473 |             68 |      14% |       14 |                0 |         0% |\n" +
                        "| uk/co/alt236/jacocoxmlparser/printer/table         |       4 |          301 |            240 |      79% |       20 |               15 |        75% |\n" +
                        "| uk/co/alt236/jacocoxmlparser/resources             |       1 |           27 |              0 |       0% |        - |                - |          - |\n" +
                        "| uk/co/alt236/jacocoxmlparser/util                  |       1 |           98 |              0 |       0% |        4 |                0 |         0% |\n" +
                        "| uk/co/alt236/jacocoxmlparser/xml                   |       6 |          224 |             52 |      23% |        4 |                1 |        25% |\n" +
                        "------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        assertEquals(expected, reportText);
    }

}