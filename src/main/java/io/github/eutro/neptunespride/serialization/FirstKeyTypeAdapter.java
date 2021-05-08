package io.github.eutro.neptunespride.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * A type adapter that serializes to/deserializes from an object with a single key and value.
 * <p>
 * The key is ignored, and the value deserialized according to the delegate adapter.
 *
 * @param <T> The type of the value.
 */
public class FirstKeyTypeAdapter<T> extends TypeAdapter<T> {
    /**
     * A type adapter factory for this type adapter.
     */
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        public <C> TypeAdapter<C> create(Gson gson, TypeToken<C> type) {
            TypeAdapter<C> delegate = gson.getDelegateAdapter(this, type);
            return new FirstKeyTypeAdapter<>(delegate);
        }
    };

    private final TypeAdapter<T> delegate;

    /**
     * Construct a FirstKeyTypeAdapter with a delegate.
     *
     * @param delegate The delegate.
     */
    public FirstKeyTypeAdapter(TypeAdapter<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.beginObject();
        out.name("value");
        delegate.write(out, value);
        out.endObject();
    }

    @Override
    public T read(JsonReader in) throws IOException {
        in.beginObject();
        in.nextName();
        T ret = delegate.read(in);
        in.endObject();
        return ret;
    }
}
