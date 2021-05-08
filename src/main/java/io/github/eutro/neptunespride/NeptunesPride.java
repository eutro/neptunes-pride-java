package io.github.eutro.neptunespride;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.*;
import io.github.eutro.neptunespride.serialization.FieldArrayTypeAdapter;
import io.github.eutro.neptunespride.serialization.ArrayIndexTypeAdapter;
import io.github.eutro.neptunespride.serialization.FilterFactory;
import io.github.eutro.neptunespride.serialization.FirstKeyTypeAdapter;
import io.github.eutro.neptunespride.types.Fleet;
import io.github.eutro.neptunespride.types.ScanningData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main entry point for the Neptune's Pride API.
 */
public class NeptunesPride {
    private static final HttpTransport TRANSPORT = new NetHttpTransport();
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapterFactory(new FilterFactory(Fleet.Order.class, FieldArrayTypeAdapter.FACTORY))
            .registerTypeAdapterFactory(new FilterFactory(ScanningData.class, FirstKeyTypeAdapter.FACTORY))
            .registerTypeAdapterFactory(ArrayIndexTypeAdapter.ENUM_FACTORY)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    /**
     * Fetch and deserialize the scanning data for a game, as returned by the API.
     * <p>
     * This method {@link #fetchRaw(String, String) fetches the raw data}
     * and deserializes it using {@link #getGson() Gson},
     * or throws an exception if the API returned an error.
     *
     * @param gameId The ID of the game, as a string.
     * @param apiKey The API code generated in the game's options.
     * @return The {@link ScanningData scanning data} fetched and deserialized from the API.
     * @throws IOException        If an IO exception occurs fetching the data.
     * @throws NPApiException  If the API returns an error.
     * @throws JsonParseException If the API did not return valid JSON.
     * @see #fetchRaw(String, String)
     * @see #getGson()
     */
    public static ScanningData fetch(String gameId, String apiKey) throws IOException, NPApiException, JsonParseException {
        JsonElement object = fetchRaw(gameId, apiKey);
        if (object.isJsonObject() && object.getAsJsonObject().has("error")) {
            throw new RuntimeException(object.getAsJsonObject().get("error").getAsString());
        }
        return getGson().fromJson(object, ScanningData.class);
    }

    /**
     * Fetch the JSON data for a game, as returned by the API.
     *
     * @param gameId The ID of the game, as a string.
     * @param apiKey The API code generated in the game's options.
     * @return The JSON the API returned.
     * @throws IOException        If an IO exception occurs fetching the data.
     * @throws JsonParseException If the API did not return valid JSON.
     * @see #fetch(String, String)
     */
    public static JsonElement fetchRaw(String gameId, String apiKey) throws IOException, JsonParseException {
        GenericUrl url = new GenericUrl("https://np.ironhelmet.com/api");
        url.put("game_number", gameId);
        url.put("code", apiKey);
        url.put("api_version", "0.1");
        HttpResponse response = TRANSPORT
                .createRequestFactory()
                .buildPostRequest(url, new EmptyContent())
                .execute();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getContent()))) {
            return JsonParser.parseReader(reader);
        }
    }

    /**
     * Get the {@link Gson} instance used to deserialize the JSON data.
     *
     * @return The Gson instance that is used to deserialize the incoming data.
     * @see #fetch(String, String)
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * Pretty-print the data from the API, taking a game ID and an API key.
     *
     * @param args The program arguments, an array of two strings, the game ID and the API key.
     * @throws IOException If the API couldn't be polled.
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Expected two arguments: [game-id], [api-key]");
            return;
        }
        new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(fetchRaw(args[0], args[1]), System.out);
    }
}
