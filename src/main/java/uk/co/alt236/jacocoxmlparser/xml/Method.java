package uk.co.alt236.jacocoxmlparser.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Method {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "desc")
    private String desc;

    @XmlAttribute(name = "line")
    private int line;

    @XmlElement(name = "counter")
    private List<Counter> counters;

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", line=" + line +
                ", counters=" + counters +
                '}';
    }

}
