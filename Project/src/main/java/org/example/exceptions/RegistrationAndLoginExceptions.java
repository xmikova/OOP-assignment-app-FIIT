package org.example.exceptions;

/**
 * Registration and login exceptions class which extends the built-in Java Exception class and handles the exceptions which are each a static inner class during the registration and login process.
 */
public class RegistrationAndLoginExceptions extends Exception {
    public String message;

    public RegistrationAndLoginExceptions() {
        this.message = "Error";
    }

    public static class UsernameTakenException extends RegistrationAndLoginExceptions {
        public UsernameTakenException() {
            this.message = "Username already taken.";
        }
    }

    public static class BlankFieldsExceptionReg extends RegistrationAndLoginExceptions {
        public BlankFieldsExceptionReg() {
            this.message = "Please fill in all the fields.";
        }
    }

    public static class NoChoiceExceptionReg extends RegistrationAndLoginExceptions {
        public NoChoiceExceptionReg() {
            this.message = "Please choose whether you are a customer or local.";
        }
    }

    public static class BlankFieldsExceptionRegLocal extends RegistrationAndLoginExceptions {
        public BlankFieldsExceptionRegLocal() {
            this.message = "Please fill in all the fields.";
        }
    }

    public static class BlankFieldsExceptionLogin extends RegistrationAndLoginExceptions {
        public BlankFieldsExceptionLogin() {
            this.message = "Please fill in all the fields.";
        }
    }

    public static class InvalidLogin extends RegistrationAndLoginExceptions {
        public InvalidLogin() {
            this.message = "Invalid username or password.";
        }
    }

    public static class InvalidNumberInput extends RegistrationAndLoginExceptions {
        public InvalidNumberInput() {
            this.message = "Please input valid number.";
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}