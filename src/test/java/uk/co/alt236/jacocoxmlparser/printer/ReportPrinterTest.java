package uk.co.alt236.jacocoxmlparser.printer;

import org.junit.Test;
import org.mockito.Mockito;
import testonly.TestReportReader;
import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.xml.Report;

public class ReportPrinterTest {

    @Test(expected = IllegalStateException.class)
    public void throwsIfNoOutputIsConfigured() throws Exception {
        final Report report = TestReportReader.readTestReport(
                getClass().getClassLoader(),
                "test_jacoco.xml");

        final CommandLineOptions options = Mockito.mock(CommandLineOptions.class);
        new ReportPrinter(options).print(report);
    }
}