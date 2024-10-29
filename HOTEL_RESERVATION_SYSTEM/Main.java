package JDBC.PROJExTs.HOTEL_RESERVATION_SYSTEM;

// HOTEL RESERVATION SYSTEM USING Statement.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "ashish";

    public static void main(String[] args) throws SQLException, InterruptedException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Meths meth=new Meths();
        Connection connection = DriverManager.getConnection(url, username, password);
            System.out.print("Establishing connection");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
             System.out.println();
            System.out.println("---- Connection Established Successfully ---- ");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
            while (true) {

                Scanner sc = new Scanner(System.in);
                System.out.println("-----------Hotel Reservation System-----------");
                System.out.println("|" + "\t \t" + "1. Reserve a room" + "\t \t \t \t \t" + "|");
                System.out.println("|" + "\t \t" + "2. View Reservations" + "\t \t \t \t" + "|");
                System.out.println("|" + "\t \t" + "3. Get Room Number" + "\t \t \t \t\t" + "|");
                System.out.println("|" + "\t \t" + "4. Update Reservations" + "\t\t\t \t" + "|");
                System.out.println("|" + "\t \t" + "5. Delete Reservations" + "\t \t\t\t" + "|");
                System.out.println("|" + "\t \t" + "0. Exit" + "\t \t \t \t \t \t \t    " + "|");
                System.out.println("Choose an Option: ");
                int option = sc.nextInt();
                switch (option) {
                    case 1:
                        meth.reserveRoom(connection, sc);
                        break;
                    case 2:
                        meth.viewReservation(connection, sc);
                        break;
                    case 3:
                        meth.getRoomNumber(connection, sc);
                        break;
                    case 4:
                        meth.updateReservation(connection, sc);
                        break;
                    case 5:
                        meth.deleteReservation(connection, sc);
                        break;
                    case 0:
                        System.exit(0);
                        sc.close();
                        break;
                    default:
                        System.out.println("Invalid Option");
                }

            }
        }
        }




