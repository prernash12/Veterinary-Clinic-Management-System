package mypack;

import java.sql.*;
import java.util.Scanner;

public class OwnerService {
    public static void insertOwner(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Owner ID: ");
        int ownerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact Info: ");
        String contact = sc.nextLine();
        System.out.print("Enter Animal ID Owned: ");
        int animalId = sc.nextInt();

        PreparedStatement pst = con.prepareStatement("INSERT INTO owners VALUES (?, ?, ?, ?)");
        pst.setInt(1, ownerId);
        pst.setString(2, name);
        pst.setString(3, contact);
        pst.setInt(4, animalId);
        pst.executeUpdate();
        System.out.println("Owner registered successfully!");
    }

    public static void viewOwners(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM owners");
        System.out.println("\n--- Owners ---");
        while (rs.next()) {
            System.out.println(rs.getInt("owner_id") + " | " +
                               rs.getString("name") + " | " +
                               rs.getString("contact") + " | Animal ID: " +
                               rs.getInt("animal_owned"));
        }
    }

    public static void updateOwner(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Owner ID to update: ");
        int ownerId = sc.nextInt();
        sc.nextLine();
        System.out.print("Which field do you want to update? (name/contact/animal_owned): ");
        String field = sc.nextLine().toLowerCase();

        String sql = "UPDATE owners SET " + field + " = ? WHERE owner_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        if (field.equals("animal_owned")) {
            System.out.print("Enter new Animal ID: ");
            int newAnimalId = sc.nextInt();
            pst.setInt(1, newAnimalId);
        } else {
            System.out.print("Enter new " + field + ": ");
            String value = sc.nextLine();
            pst.setString(1, value);
        }

        pst.setInt(2, ownerId);
        int updated = pst.executeUpdate();
        System.out.println(updated > 0 ? "Owner updated." : "Owner not found.");
    }

    public static void deleteOwner(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Owner ID to delete: ");
        int ownerId = sc.nextInt();

        PreparedStatement pst = con.prepareStatement("DELETE FROM owners WHERE owner_id = ?");
        pst.setInt(1, ownerId);
        int deleted = pst.executeUpdate();
        System.out.println(deleted > 0 ? "Owner deleted." : "Owner not found.");
    }
}