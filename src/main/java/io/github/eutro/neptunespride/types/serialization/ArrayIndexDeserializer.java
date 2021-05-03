package io.github.eutro.neptunespride.types.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ArrayIndexDeserializer<T> implements JsonDeserializer<T> {
    private final T[] elements;

    public ArrayIndexDeserializer(T[] elements) {
        this.elements = elements;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return elements[json.getAsInt()];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JsonParseException(e);
        }
    }
}
