package JDBC.PROJExTs.HOTEL_RESERVATION_SYSTEM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Meths {

    public void reserveRoom(Connection connection, Scanner sc) {
        System.out.println("Enter Your Name");
        String name = sc.next();
        System.out.println("Enter Your Room Number");
        int roomNumber = sc.nextInt();
        System.out.println("Enter Your Contact Number");
        String contactNumber = sc.next();

        String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) VALUES ('"+ name + "', " + roomNumber + ", " + contactNumber + ")";
        try {
            Statement stmt = connection.createStatement();
            int affectedRows = stmt.executeUpdate(sql);
            if (affectedRows > 0) {
                System.out.println("Your Room is SucessFully Reserved");
            } else {
                System.out.println("Something went Wrong during Reserving Room! Try Again.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewReservation(Connection connection, Scanner sc) {

        String sql = "Select * from reservations";
        try {
           Statement stmt= connection.createStatement();
           ResultSet resultSet = stmt.executeQuery(sql);
            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest           | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
        while (resultSet.next()) {
            int reservationId = resultSet.getInt("reservation_id");
            String guestName = resultSet.getString("guest_name");
            int roomNumber = resultSet.getInt("room_number");
            String contactNumber = resultSet.getString("contact_number");
            String reservationDate = resultSet.getTimestamp("reservation_date").toString();
            System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s   |\n",
                    reservationId, guestName, roomNumber, contactNumber, reservationDate);
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println();
            System.out.println();
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void getRoomNumber(Connection connection, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int reservationId = sc.nextInt();
            System.out.print("Enter guest name: ");
            String guestName = sc.next();
            String sql = "SELECT room_number FROM reservations WHERE reservation_id = " + reservationId + " AND guest_name = '" + guestName + "';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                System.out.println("Room number for Reservation ID " + reservationId +
                        " and Guest " + guestName + " is: " + roomNumber);
            } else {
                System.out.println("Reservation not found for the given ID and guest name.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateReservation(Connection connection, Scanner sc) {
        try {
            System.out.print("Enter reservation ID to update: ");
            int reservationId = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            System.out.print("Enter new guest name: ");
            String newGuestName = sc.nextLine();
            System.out.print("Enter new room number: ");
            int newRoomNumber = sc.nextInt();
            System.out.print("Enter new contact number: ");
            String newContactNumber = sc.next();

            String sql = "UPDATE reservations SET guest_name = '" + newGuestName + "', " +
                    "room_number = " + newRoomNumber + ", " +
                    "contact_number = '" + newContactNumber + "' " +
                    "WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation updated successfully!");
                } else {
                    System.out.println("Reservation update failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deleteReservation(Connection connection, Scanner sc) {
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = sc.nextInt();

            if (!reservationExists(connection, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully!");
                } else {
                    System.out.println("Reservation deletion failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private static boolean reservationExists(Connection connection, int reservationId) {
        try {
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }

}

