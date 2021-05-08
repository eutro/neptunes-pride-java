package io.github.eutro.neptunespride.types;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * A collection of annotations that indicate when an optional value may be present.
 */
public interface Only {
    /**
     * Fields annotated with this are only present when the object is owned by the API key owner.
     */
    @Documented
    @Target(ElementType.FIELD)
    @interface Owned {
    }

    /**
     * Fields annotated with this are only present when the object is in scanning range of the API key owner.
     */
    @Documented
    @Target(ElementType.FIELD)
    @interface Visible {
    }
}
