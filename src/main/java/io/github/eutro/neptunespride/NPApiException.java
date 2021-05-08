package io.github.eutro.neptunespride;

/**
 * An exception thrown if the Neptune's Pride API returns an error.
 */
public class NPApiException extends RuntimeException {
    /**
     * Constructs a new Neptune's Pride API exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public NPApiException(String message) {
        super(message);
    }
}
