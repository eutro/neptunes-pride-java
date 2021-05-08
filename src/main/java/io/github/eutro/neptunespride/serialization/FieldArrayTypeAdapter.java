package io.github.eutro.neptunespride.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.*;

/**
 * A type adapter that converts to/from a JSON array based on the order of fields in a class.
 *
 * @param <T> The type of the object to adapt.
 */
public class FieldArrayTypeAdapter<T> extends TypeAdapter<T> {
    /**
     * A factory for this type adapter.
     */
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        public <C> TypeAdapter<C> create(Gson gson, TypeToken<C> type) {
            try {
                @SuppressWarnings("unchecked")
                Class<C> clazz = (Class<C>) type.getRawType();
                return new FieldArrayTypeAdapter<>(gson, clazz);
            } catch (NoSuchMethodException ignored) {
                return null;
            }
        }
    };

    private final Gson gson;
    private final Constructor<T> ctor;
    private final Field[] fields;

    /**
     * Construct a FieldArrayTypeAdapter for the given class.
     *
     * @param gson The Gson instance to delegate to.
     * @param clazz The class of T.
     * @throws NoSuchMethodException If there is no nullary constructor.
     */
    public FieldArrayTypeAdapter(Gson gson, Class<T> clazz) throws NoSuchMethodException {
        this.gson = gson;
        ctor = clazz.getConstructor();
        fields = clazz.getFields();
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.beginArray();
        for (Field field : fields) {
            try {
                gson.toJson(field.get(value), field.getGenericType(), out);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        out.endArray();
    }

    @Override
    public T read(JsonReader in) throws IOException {
        T object;
        try {
            object = ctor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new JsonParseException(e);
        }
        in.beginArray();
        for (int i = 0; i < fields.length && in.hasNext(); i++) {
            Field field = fields[i];
            try {
                field.set(object, gson.fromJson(in, field.getGenericType()));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
        while (in.hasNext()) in.skipValue();
        in.endArray();
        return object;
    }
}
