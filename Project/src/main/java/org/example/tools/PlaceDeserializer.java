package org.example.tools;

import com.google.gson.*;
import org.example.objectclasses.Place;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * A deserializer for the Place class that implements the JsonDeserializer interface provided by the Gson library. It is used to convert a JSON object to a Place object by extracting the necessary fields from the JSON and passing them to the Place constructor.
 */
public class PlaceDeserializer implements JsonDeserializer<Place> {

    /**
     * The deserializing method that parses objects from JSON database of places and return a new Place object.
     */
    @Override
    public Place deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String id = jsonObject.get("id").getAsString();
        String id2 = jsonObject.get("id2").getAsString();
        String name = jsonObject.get("name").getAsString();
        String description = jsonObject.get("description").getAsString();
        double price = jsonObject.get("price").getAsDouble();
        String type = jsonObject.get("type").getAsString();
        String city = jsonObject.get("city").getAsString();

        try {
            return new Place(id, id2, name, description, price, type, city);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}