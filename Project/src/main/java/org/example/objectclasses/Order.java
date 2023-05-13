package org.example.objectclasses;

import java.sql.*;

/**
 * Class which has all the attributes needed for order creation and also methods to save order into SQLite database.
 */
public class Order{
    private int id;
    private String customerFirstName;
    private String customerAge;
    private String customerUsername;
    private String location;
    private boolean children;
    private boolean pets;
    private boolean male;
    private int length;
    private boolean cityCentre;
    private double maxPricePerDay;
    private int minAge;
    private int maxAge;
    private String selectedPlaces;

    private double expectedPrice;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(String customerAge) {
        this.customerAge = customerAge;
    }

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

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
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

    public String getSelectedPlaces() {
        return selectedPlaces;
    }

    public void setSelectedPlaces(String selectedPlaces) {
        this.selectedPlaces = selectedPlaces;
    }

    public String getStatus() {
        return status;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public String getSellerAge() {
        return sellerAge;
    }

    public void setSellerAge(String sellerAge) {
        this.sellerAge = sellerAge;
    }

    public double getExpectedPrice() {
        return expectedPrice;
    }

    public void setExpectedPrice(double expectedPrice) {
        this.expectedPrice = expectedPrice;
    }

    private String sellerFirstName;

    public Order() {
        this.status = "In process";
    }

    private String sellerAge;
    private String sellerUsername;


    /**
     * This method saves the initial version of order when it is created by customer, without having the seller attributes.
     */
    public void save(Connection connection) throws SQLException {
        String query = "INSERT INTO orders (customer_first_name, customer_age, customer_username, location, children, pets, male, length, city_centre, max_price_per_day, min_age, max_age, selected_places, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, this.customerFirstName);
        statement.setString(2, this.customerAge);
        statement.setString(3, this.customerUsername);
        statement.setString(4, this.location);
        statement.setBoolean(5, this.children);
        statement.setBoolean(6, this.pets);
        statement.setBoolean(7, this.male);
        statement.setInt(8, this.length);
        statement.setBoolean(9, this.cityCentre);
        statement.setDouble(10, this.maxPricePerDay);
        statement.setInt(11, this.minAge);
        statement.setInt(12, this.maxAge);
        statement.setString(13, this.selectedPlaces);
        statement.setString(14, this.status);
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                this.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Inserting order failed, no ID obtained.");
            }
        }
    }

    public void save() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:orders.db")) {
            initializeTable(connection);
            save(connection);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    /**
     * This method handles the situation where local accepts the order.
     */
    public void updateLocal(Connection connection, int id, String status, int sellerAge, String sellerFirstName, String sellerUsername ) throws SQLException {
        String query = "UPDATE orders SET status=?, seller_age=?, seller_first_name=?, seller_username=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, status);
        statement.setInt(2, sellerAge);
        statement.setString(3, sellerFirstName);
        statement.setString(4, sellerUsername);
        statement.setInt(5, id);
        statement.executeUpdate();
    }

    public void updateLocal(int id, String status, int sellerAge, String sellerFirstName, String sellerUsername) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:orders.db")) {
            updateLocal(connection, id, status,sellerAge,sellerFirstName,sellerUsername);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    /**
     * Method for any status updates of order.
     */
    public void updateStatus(int id, Connection connection, String status) throws SQLException {
        String query = "UPDATE orders SET status=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, status);
        statement.setInt(2, id);
        statement.executeUpdate();
    }

    public void updateStatus(int id,String status) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:orders.db")) {
            updateStatus(id, connection, status);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }


    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * An orders table initializator in case it does not exist.
     */
    public static void initializeTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "customer_first_name TEXT NOT NULL," +
                "customer_age TEXT NOT NULL," +
                "customer_username TEXT NOT NULL," +
                "location TEXT NOT NULL," +
                "children INTEGER NOT NULL," +
                "pets INTEGER NOT NULL," +
                "male INTEGER NOT NULL," +
                "length INTEGER NOT NULL," +
                "city_centre INTEGER NOT NULL," +
                "max_price_per_day REAL NOT NULL," +
                "min_age INTEGER NOT NULL," +
                "max_age INTEGER NOT NULL," +
                "selected_places TEXT NOT NULL," +
                "status TEXT NOT NULL," +
                "seller_first_name TEXT," +
                "seller_username TEXT," +
                "seller_age INTEGER)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        }
    }
}


