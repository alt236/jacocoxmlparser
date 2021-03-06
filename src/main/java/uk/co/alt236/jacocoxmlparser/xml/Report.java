package uk.co.alt236.jacocoxmlparser.xml;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Report {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "package")
    private List<Package> packages;

    @XmlElement(name = "counter")
    private List<Counter> counters;

    @Nonnull
    public String getName() {
        return SafeReturn.safe(name);
    }

    @Nonnull
    public List<Package> getPackages() {
        return SafeReturn.safe(packages);
    }

    @Nonnull
    public List<Counter> getCounters() {
        return SafeReturn.safe(counters);
    }

    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", packages=" + packages +
                ", counters=" + counters +
                '}';
    }
}
