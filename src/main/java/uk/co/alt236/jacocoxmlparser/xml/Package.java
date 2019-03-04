package uk.co.alt236.jacocoxmlparser.xml;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Package {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "class")
    private List<Clazz> classes;

    @XmlElement(name = "counter")
    private List<Counter> counters;


    @Nonnull
    public String getName() {
        return SafeReturn.safe(name);
    }

    @Nonnull
    public List<Clazz> getClasses() {
        return SafeReturn.safe(classes);
    }

    @Nonnull
    public List<Counter> getCounters() {
        return SafeReturn.safe(counters);
    }

    @Override
    public String toString() {
        return "Package{" +
                "name='" + name + '\'' +
                ", classes=" + classes +
                ", counters=" + counters +
                '}';
    }
}
