package testonly;

import uk.co.alt236.jacocoxmlparser.parser.ReportParser;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.io.File;
import java.net.URL;

public final class TestReportReader {

    public static Report readTestReport(final ClassLoader classLoader, final String name) throws Exception {
        final URL url = classLoader.getResource(name);
        final File file = new File(url.getFile());

        final ReportParser parser = new ReportParser();
        return parser.parseReport(file);
    }

}
