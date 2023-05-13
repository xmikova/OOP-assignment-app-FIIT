package org.example.objectclasses;

import java.io.Serial;

/**
 * Customer class that inherits from the User superclass and has its own preferences which are of CustomerPreferences class (aggregation).
 */
public class Customer extends User {
    @Serial
    private static final long serialVersionUID = 1L;

    private CustomerPreferences preferences;

    public Customer(User user) {
        super(user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.isFemale(), user.getAge(), user.isCustomer());
    }

    public void registration(Customer customer) {
        super.registration(customer);
    }

    public CustomerPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(CustomerPreferences preferences) {
        this.preferences = preferences;
    }
}
