package io.github.eutro.neptunespride.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Deserializes a value from its index in the given array.
 *
 * @param <T> The type of the array elements.
 */
public class ArrayIndexTypeAdapter<T> extends TypeAdapter<T> {
    /**
     * A type adapter factory that creates type adapters for enum classes.
     */
    public static final TypeAdapterFactory ENUM_FACTORY = new TypeAdapterFactory() {
        @Override
        public <C> TypeAdapter<C> create(Gson gson, TypeToken<C> type) {
            Class<? super C> rawType = type.getRawType();
            if (!rawType.isEnum()) return null;
            @SuppressWarnings("unchecked")
            C[] constants = (C[]) rawType.getEnumConstants();
            return new ArrayIndexTypeAdapter<>(constants);
        }
    };

    private final T[] elements;

    /**
     * Construct a {@link ArrayIndexTypeAdapter} from an array of elements.
     *
     * @param elements The array of elements to index.
     */
    public ArrayIndexTypeAdapter(T[] elements) {
        this.elements = elements;
    }

    private int indexOf(T value) {
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.value(indexOf(value));
    }

    @Override
    public T read(JsonReader in) throws IOException {
        try {
            return elements[in.nextInt()];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JsonParseException(e);
        }
    }
}
