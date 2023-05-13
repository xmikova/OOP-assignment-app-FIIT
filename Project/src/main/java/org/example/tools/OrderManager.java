package org.example.tools;

import org.example.objectclasses.Order;

/**
 * A class which uses Singleton design pattern and provides the ability to work with one order object throughout the whole order creation even in different controllers.
 */
public class OrderManager  {
    private static OrderManager instance = null;
    private Order currentOrder = null;

    private OrderManager() {}

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void clearCurrentOrder() {
        currentOrder = null;
    }
}
