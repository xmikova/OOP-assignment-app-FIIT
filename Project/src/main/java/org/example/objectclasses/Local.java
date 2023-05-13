package org.example.objectclasses;

import java.io.Serial;

/**
 * Local class that inherits from the User superclass and has some of its own attributes to store even more info about the local.
 */
public class Local extends User {
    @Serial
    private static final long serialVersionUID = 1L;
    private String location;
    private boolean children;
    private boolean pets;
    private boolean cityCentre;
    private double pricePerDay = -1;


    public Local(User user) {
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.isFemale(), user.getAge(), user.isCustomer());
        this.location = location;
        this.children = children;
        this.pets = pets;
        this.cityCentre = cityCentre;
        this.pricePerDay = pricePerDay;
    }

    public Local() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public boolean getPets() {
        return pets;
    }

    public void setPets(boolean pets) {
        this.pets = pets;
    }

    public boolean getCityCentre() {
        return cityCentre;
    }

    public void setCityCentre(boolean cityCentre) {
        this.cityCentre = cityCentre;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void registration(Local local){
        super.registration(local);
    }

}
