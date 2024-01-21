package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Car;
import com.model.Customer;
import com.model.Reservation;

public class CarRentalrepository {
	private CarRentalrepository repository;
	

	    private List<Car> cars;
	    private List<Customer> customers;
	    private List<Reservation> reservations;

	    public CarRentalrepository() {
	        this.cars = new ArrayList<>();
	        this.customers = new ArrayList<>();
	        this.reservations = new ArrayList<>();
	        
	    }

	 private static final String JDBC_URL = "jdbc:mysql://localhost:3306/CarRental";
	    private static final String JDBC_USERNAME = "root";
	    private static final String JDBC_PASSWORD = "Kirthu17@99";
	    private Connection connection;
	    
	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }


	    private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	    }

	    private void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public List<Car> getAvailableCars() {
	        List<Car> availableCars = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
	             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cars WHERE available = true");
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                String carId = resultSet.getString("carId");
	                String brand = resultSet.getString("brand");
	                boolean available = resultSet.getBoolean("available");

	                availableCars.add(new Car(carId, brand, available));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return availableCars;
	    }
	    public void addCar(Car car) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	            		 "INSERT INTO cars (carId, brand, available) VALUES (?, ?, ?)"
	             )) {
	            preparedStatement.setString(1, car.getCarId());
	            preparedStatement.setString(2, car.getBrand());
	            preparedStatement.setBoolean(3, car.isAvailable());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void addCustomer(Customer customer) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO customers (customerId, name, contactNumber) VALUES (?, ?, ?)"
	             )) {
	            preparedStatement.setString(1, customer.getCustomerId());
	            preparedStatement.setString(2, customer.getName());
	            preparedStatement.setString(3, customer.getContactNumber());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void makeReservation(Reservation reservation) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "INSERT INTO reservations (reservationId, customerId, carId, startDate, endDate, totalAmount) " +
	                             "VALUES (?, ?, ?, ?, ?, ?)"
	             )) {
	            preparedStatement.setString(1, reservation.getReservationId());
	            preparedStatement.setString(2, reservation.getCustomer().getCustomerId());
	            preparedStatement.setString(3, reservation.getCar().getCarId());
	            preparedStatement.setDate(4, java.sql.Date.valueOf(reservation.getStartDate()));
	            preparedStatement.setDate(5, java.sql.Date.valueOf(reservation.getEndDate()));
	            preparedStatement.setDouble(6, reservation.getTotalAmount());
	            preparedStatement.executeUpdate();

	            // Update car availability
	            updateCarAvailability(reservation.getCar().getCarId(), false);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void returnCar(String reservationId) {
	        Reservation reservation = getReservationById(reservationId);
	        if (reservation != null) {
	            Car car = reservation.getCar();

	            // Update car availability
	            updateCarAvailability(car.getCarId(), true);

	            // Remove reservation
	            try (Connection connection = getConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(
	                         "DELETE FROM reservations WHERE reservation_id = ?"
	                 )) {
	                preparedStatement.setString(1, reservationId);
	                preparedStatement.executeUpdate();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private void updateCarAvailability(String carId, boolean isAvailable) {
	        try (Connection connection = getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "UPDATE cars SET available = ? WHERE car_id = ?"
	             )) {
	            preparedStatement.setBoolean(1, isAvailable);
	            preparedStatement.setString(2, carId);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private Reservation getReservationById(String reservationId) {
	        // Retrieve reservation details from the database based on the reservationId
	        // and construct a Reservation object
	        // Implement this method based on your database schema
	        // ...

	        return null;
	    }

}
