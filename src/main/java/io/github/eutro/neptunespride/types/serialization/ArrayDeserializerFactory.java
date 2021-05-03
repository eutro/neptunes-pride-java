package io.github.eutro.neptunespride.types.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.*;

public class ArrayDeserializerFactory implements TypeAdapterFactory {
    public static ArrayDeserializerFactory INSTANCE = new ArrayDeserializerFactory();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        try {
            Class<? super T> clazz = type.getRawType();
            @SuppressWarnings("unchecked")
            Constructor<T> ctor = (Constructor<T>) clazz.getConstructor();
            Field[] fields = clazz.getFields();
            class ArrayDeserializer extends TypeAdapter<T> {
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
            return new ArrayDeserializer();
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }
}
