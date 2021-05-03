package io.github.eutro.neptunespride;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.*;
import io.github.eutro.neptunespride.types.response.Fleet;
import io.github.eutro.neptunespride.types.response.Player;
import io.github.eutro.neptunespride.types.response.ScanningData;
import io.github.eutro.neptunespride.types.response.Toggle;
import io.github.eutro.neptunespride.types.serialization.ArrayDeserializerFactory;
import io.github.eutro.neptunespride.types.serialization.ArrayIndexDeserializer;
import io.github.eutro.neptunespride.types.serialization.FilterFactory;
import io.github.eutro.neptunespride.types.serialization.FirstKeyDeserializerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeptunesPride {
    public static final String NP_API = "https://np.ironhelmet.com/api";
    public static final EmptyContent CONTENT = new EmptyContent();
    public static HttpTransport TRANSPORT = new NetHttpTransport();

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new FilterFactory(Fleet.Order.class, ArrayDeserializerFactory.INSTANCE))
            .registerTypeAdapterFactory(new FilterFactory(ScanningData.class, FirstKeyDeserializerFactory.INSTANCE))
            .registerTypeAdapter(Fleet.Order.Type.class, new ArrayIndexDeserializer<>(Fleet.Order.Type.values()))
            .registerTypeAdapter(Toggle.class, new ArrayIndexDeserializer<>(Toggle.values()))
            .registerTypeAdapter(Player.Conceded.class, new ArrayIndexDeserializer<>(Player.Conceded.values()))
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    public static ScanningData run(String gameId, String apiKey) throws IOException {
        JsonElement object = runRaw(gameId, apiKey);
        if (object.isJsonObject() && object.getAsJsonObject().has("error")) {
            throw new RuntimeException(object.getAsJsonObject().get("error").getAsString());
        }
        return gson.fromJson(object, ScanningData.class);
    }

    public static JsonElement runRaw(String gameId, String apiKey) throws IOException {
        GenericUrl url = new GenericUrl(NP_API);
        url.put("game_number", gameId);
        url.put("code", apiKey);
        url.put("api_version", "0.1");
        return JsonParser.parseReader(new BufferedReader(new InputStreamReader(TRANSPORT
                .createRequestFactory()
                .buildPostRequest(url, CONTENT)
                .execute()
                .getContent())));
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Expected two arguments: [game-id], [api-key]");
            return;
        }
        new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(runRaw(args[0], args[1]), System.out);
    }
}
