package org.example.objectclasses;

import java.io.Serializable;
import java.util.List;

/**
 * A class to handle all preferences from the customer for his trip.
 */
public class CustomerPreferences implements Serializable {
    private String location;
    private boolean children;
    private boolean pets;
    private boolean male;
    private int length;
    private boolean cityCentre;
    private double maxPricePerDay;
    private int minAge;
    private int maxAge;
    private List<Place> selectedPlaces;
    private static final long serialVersionUID = 1L;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public boolean isPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean isCityCentre() {
        return cityCentre;
    }

    public void setCityCentre(boolean cityCentre) {
        this.cityCentre = cityCentre;
    }

    public double getMaxPricePerDay() {
        return maxPricePerDay;
    }

    public void setMaxPricePerDay(double maxPricePerDay) {
        this.maxPricePerDay = maxPricePerDay;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Place> getSelectedPlaces() {
        return selectedPlaces;
    }

    public void setSelectedPlaces(List<Place> selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }
}
