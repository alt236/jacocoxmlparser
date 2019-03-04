package uk.co.alt236.jacocoxmlparser.printer;

import uk.co.alt236.jacocoxmlparser.xml.Counter;

import java.util.List;
import java.util.stream.Collectors;

class CounterResultFactory {

    public static CounterResult getCounter(final List<Counter> counters,
                                           final String name) {
        final List<Counter> filtered = counters
                .stream()
                .filter(counter -> counter.getType().equals(name))
                .collect(Collectors.toList());

        return filtered.isEmpty()
                ? new CounterResult()
                : new CounterResult(filtered.get(0));
    }


}
