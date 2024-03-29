package io.github.eutro.neptunespride.serialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.function.Predicate;

/**
 * A type adapter factory that ignores any type tokens that don't match a predicate.
 */
public class FilterFactory implements TypeAdapterFactory {
    private final Predicate<TypeToken<?>> predicate;
    private final TypeAdapterFactory delegate;

    /**
     * Construct a FilterFactory with the given predicate and delegate.
     *
     * @param predicate The predicate.
     * @param delegate The delegate.
     */
    public FilterFactory(Predicate<TypeToken<?>> predicate, TypeAdapterFactory delegate) {
        this.predicate = predicate;
        this.delegate = delegate;
    }

    /**
     * Construct a FilterFactory that only accepts a given type.
     *
     * @param type The only type to accept.
     * @param delegate The delegate.
     */
    public FilterFactory(Type type, TypeAdapterFactory delegate) {
        this(TypeToken.get(type)::equals, delegate);
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return predicate.test(type) ? delegate.create(gson, type) : null;
    }
}
