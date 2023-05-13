package org.example.objectclasses;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.tools.PlaceDeserializer;

/**
 * A place class that handles the JSON database where places for each city are stored.
 */
public class Place {
    private String id;
    private String id2;
    private String city;
    private String name;
    private String description;
    private double price;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId2() {
        return id2;
    }

    public void setId2(String id2) {
        this.id2 = id2;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private boolean selected;

    private static List<Place> places;

    public Place() throws IOException {}

    public Place(String id, String id2, String name, String description, double price, String type, String city) throws IOException {
        this.id = id;
        this.id2 = id2;
        this.city = city;
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method that returns a list of places using the method which deserializes the database.
     */
    public static List<Place> getPlaces() {
        if (places == null) {
            try {
                places = readPlacesFromJsonDatabase();
            } catch (IOException e) {
                e.printStackTrace();
                places = new ArrayList<>();
            }
        }
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    /**
     * A method that reads a JSON file named "places.json" and deserializes its content into a list of Place objects using the Gson library. The list is then returned as the method's result.
     */
    private static List<Place> readPlacesFromJsonDatabase() throws IOException {
        List<Place> places;
        InputStream inputStream = Place.class.getResourceAsStream("/data/places.json");
        Reader reader = new InputStreamReader(inputStream);
        Gson gson = new GsonBuilder().registerTypeAdapter(Place.class, new PlaceDeserializer()).create();
        Type placeListType = new TypeToken<List<Place>>() {}.getType();
        places = gson.fromJson(reader, placeListType);
        return places;
    }
}



