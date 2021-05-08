package io.github.eutro.neptunespride.types;

/**
 * An enum used for fields that are represented as integers but are boolean values.
 */
public enum Toggle {
    /**
     * No
     */
    NO,

    /**
     * Yes
     */
    YES,
    ;

    /**
     * Get whether the value of this toggle is yes.
     *
     * @return Whether this is {@link #YES}.
     */
    public boolean isYes() {
        return this == YES;
    }
}
