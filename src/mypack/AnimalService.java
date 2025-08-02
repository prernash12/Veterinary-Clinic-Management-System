package mypack;

import java.sql.*;
import java.util.Scanner;

public class AnimalService {
    public static void insertAnimal(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Animal ID: ");
        int animalId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Species: ");
        String species = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        PreparedStatement pstAnimal = con.prepareStatement("INSERT INTO animals VALUES (?, ?, ?, ?)");
        pstAnimal.setInt(1, animalId);
        pstAnimal.setString(2, name);
        pstAnimal.setString(3, species);
        pstAnimal.setInt(4, age);
        pstAnimal.executeUpdate();
        System.out.println("Animal registered successfully!");
    }

    public static void viewAnimals(Connection con) throws SQLException {
        Statement stAnimals = con.createStatement();
        ResultSet rsAnimals = stAnimals.executeQuery("SELECT * FROM animals");
        System.out.println("\n--- Animals ---");
        while (rsAnimals.next()) {
            System.out.println(rsAnimals.getInt("animal_id") + " | " +
                               rsAnimals.getString("name") + " | " +
                               rsAnimals.getString("species") + " | Age: " +
                               rsAnimals.getInt("age"));
        }
    }

    public static void updateAnimal(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Animal ID to update: ");
        int updateAnimalId = sc.nextInt();
        sc.nextLine();
        System.out.print("Which field do you want to update? (name/species/age): ");
        String field = sc.nextLine().toLowerCase();

        String sql = "UPDATE animals SET " + field + " = ? WHERE animal_id = ?";
        PreparedStatement pst = con.prepareStatement(sql);

        if (field.equals("age")) {
            System.out.print("Enter new age: ");
            int age = sc.nextInt();
            pst.setInt(1, age);
        } else {
            System.out.print("Enter new " + field + ": ");
            String value = sc.nextLine();
            pst.setString(1, value);
        }

        pst.setInt(2, updateAnimalId);
        int updated = pst.executeUpdate();
        System.out.println(updated > 0 ? "Animal updated." : "Animal not found.");
    }

    public static void deleteAnimal(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Animal ID to delete: ");
        int deleteId = sc.nextInt();

        PreparedStatement pst = con.prepareStatement("DELETE FROM animals WHERE animal_id = ?");
        pst.setInt(1, deleteId);
        int deleted = pst.executeUpdate();
        System.out.println(deleted > 0 ? "Animal deleted." : "Animal not found.");
    }
}