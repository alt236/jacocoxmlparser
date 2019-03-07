package uk.co.alt236.jacocoxmlparser.xml;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Counter {

    @XmlAttribute(name = "type")
    private String type;

    @XmlAttribute(name = "missed")
    private int missed;

    @XmlAttribute(name = "covered")
    private int covered;

    private Counter() {
        // NOOP - Here to allow binding
    }

    public Counter(final String type,
                   final int covered,
                   final int missed) {
        this.type = type;
        this.covered = covered;
        this.missed = missed;
    }

    @Nonnull
    public String getType() {
        return SafeReturn.safe(type);
    }

    public int getMissed() {
        return missed;
    }

    public int getCovered() {
        return covered;
    }

    public int getTotal() {
        return covered + missed;
    }

    public double getPercentage() {
        return (covered / (float) getTotal()) * 100;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "type='" + type + '\'' +
                ", missed=" + missed +
                ", covered=" + covered +
                '}';
    }
}
