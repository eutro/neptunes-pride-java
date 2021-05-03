package io.github.eutro.neptunespride.types.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class FirstKeyDeserializerFactory implements TypeAdapterFactory {
    public static final FirstKeyDeserializerFactory INSTANCE = new FirstKeyDeserializerFactory();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        class FirstKeyDeserializer extends TypeAdapter<T> {
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
        return new FirstKeyDeserializer();
    }
}
