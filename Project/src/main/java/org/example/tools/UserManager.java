package org.example.tools;

import org.example.objectclasses.User;

/**
 * A generic class called using Singleton design pattern that provides access to the current user object of type T which extends the User class.
 */
public class UserManager<T extends User> {
    private static UserManager instance = null;
    private T currentUser = null;

    private UserManager() {}

    /**
     * The getInstance() method provides access to the single instance of the UserManager class.
     */
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public T getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(T currentUser) {
        this.currentUser = currentUser;
    }
}