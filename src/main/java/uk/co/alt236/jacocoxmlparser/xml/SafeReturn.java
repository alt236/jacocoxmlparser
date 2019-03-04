package uk.co.alt236.jacocoxmlparser.xml;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

final class SafeReturn {

    @Nonnull
    static String safe(@Nullable String value) {
        return value == null ? "" : value;
    }

    @Nonnull
    static <T> List<T> safe(@Nullable List<T> value) {
        return value == null ? Collections.emptyList() : value;
    }
}
