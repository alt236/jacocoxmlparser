package uk.co.alt236.jacocoxmlparser;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import uk.co.alt236.jacocoxmlparser.xml.Report;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.io.FileReader;

public class ReportParser {


    public Report parseReport(final File file) throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Report.class);

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        spf.setFeature("http://xml.org/sax/features/validation", false);

        XMLReader xmlReader = spf.newSAXParser().getXMLReader();
        InputSource inputSource = new InputSource(new FileReader(file));
        SAXSource source = new SAXSource(xmlReader, inputSource);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (Report) unmarshaller.unmarshal(source);
    }
}
