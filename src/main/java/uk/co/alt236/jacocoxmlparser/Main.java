package uk.co.alt236.jacocoxmlparser;

import uk.co.alt236.jacocoxmlparser.printer.ReportPrinter;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import java.io.File;

public class Main {

    private static final String FILE_NAME = "/home/alex/vcs/telegraph-android/banksyandroid/app/build/reports/jacoco/jacocoTestReport/jacocoTestReport.xml";

    public static void main(String[] args) throws Exception {
        final Report report = new ReportParser().parseReport(new File(FILE_NAME));
        new ReportPrinter().print(report);
    }

}
