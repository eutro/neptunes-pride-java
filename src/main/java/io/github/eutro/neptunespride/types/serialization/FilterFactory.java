package io.github.eutro.neptunespride.types.serialization;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.function.Predicate;

public class FilterFactory implements TypeAdapterFactory {
    private final Predicate<TypeToken<?>> predicate;
    private final TypeAdapterFactory delegate;

    public FilterFactory(Predicate<TypeToken<?>> predicate, TypeAdapterFactory delegate) {
        this.predicate = predicate;
        this.delegate = delegate;
    }

    public FilterFactory(Type type, TypeAdapterFactory delegate) {
        this(TypeToken.get(type)::equals, delegate);
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        return predicate.test(type) ? delegate.create(gson, type) : null;
    }
}
