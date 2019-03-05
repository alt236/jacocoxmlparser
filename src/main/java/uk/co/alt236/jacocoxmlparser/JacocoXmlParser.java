package uk.co.alt236.jacocoxmlparser;

import uk.co.alt236.jacocoxmlparser.cli.CommandLineOptions;
import uk.co.alt236.jacocoxmlparser.parser.ReportParser;
import uk.co.alt236.jacocoxmlparser.printer.ReportPrinter;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.io.File;

class JacocoXmlParser {

    void parse(CommandLineOptions options, File jacocoFile) throws Exception {
        final Report report = new ReportParser().parseReport(jacocoFile);
        new ReportPrinter(options).print(report);
    }
}
