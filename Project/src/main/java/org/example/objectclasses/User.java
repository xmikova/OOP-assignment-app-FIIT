package org.example.objectclasses;

import java.io.*;
import java.util.ArrayList;

/**
 * The User superclass that classes Customer and Local inherit from. It contains methods for registration and login. It also performs serialization of user information for successful registration and further login.
 */
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isFemale;
    private int age;
    private boolean isCustomer;

    public User(String firstName, String lastName, String username, String password, boolean isFemale, int age, boolean isCustomer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isFemale = isFemale;
        this.age = age;
        this.isCustomer = isCustomer;
    }

    public User(){
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public int getAge() {
        return age;
    }

    public boolean isCustomer() {
        return isCustomer;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    /**
     * A method which adds the user into the users database after registration.
     */
    public void registration(User user) {
        ArrayList<User> users = getUsers();
        users.add(user);
        setUsers(users);
    }

    /**
     * A method that takes a username and password as input and checks if they match with any existing user's credentials. It returns true if a match is found and false otherwise.
     */
    public static boolean login(String username, String password) {
        ArrayList<User> users = getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method reads user data from a file named "users.dat" using ObjectInputStream and returns an ArrayList of User objects.
     */
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * A private static method that takes an ArrayList of User objects as its argument. It serializes the ArrayList and writes it to a binary file called "users.dat" using an ObjectOutputStream.
     */
    private static void setUsers(ArrayList<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}