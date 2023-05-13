package org.example.exceptions;

/**
 * Order exceptions class which extends the built-in Java Exception class and handles the exceptions which are each a static inner class during the order creation.
 */
public class OrderExceptions extends Exception{

    public String message;

    public OrderExceptions() {
        this.message = "Error";
    }

    public static class NoLocationChosen extends OrderExceptions {
        public NoLocationChosen() {
            this.message = "Please choose one of the locations.";
        }
    }

    public static class BlankFieldsExceptionOrder extends OrderExceptions {
        public BlankFieldsExceptionOrder() {
            this.message = "Please fill in all the fields.";
        }
    }

    public static class InvalidNumberInput extends OrderExceptions {
        public InvalidNumberInput() {
            this.message = "Please input valid number.";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

}
