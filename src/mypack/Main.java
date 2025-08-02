package mypack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/vet_db", "root", "Prernashaw@1206");
            System.out.println("Connected to Database");

            int choice;
            do {
                System.out.println("\n===== Veterinary Management Menu =====");
                System.out.println("1. Register Animal");
                System.out.println("2. Register Owner");
                System.out.println("3. View All Animals");
                System.out.println("4. View All Owners");
                System.out.println("5. Update Animal");
                System.out.println("6. Update Owner");
                System.out.println("7. Delete Owner");
                System.out.println("8. Delete Animal");
                System.out.println("9. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1: AnimalService.insertAnimal(con, sc); break;
                    case 2: OwnerService.insertOwner(con, sc); break;
                    case 3: AnimalService.viewAnimals(con); break;
                    case 4: OwnerService.viewOwners(con); break;
                    case 5: AnimalService.updateAnimal(con, sc); break;
                    case 6: OwnerService.updateOwner(con, sc); break;
                    case 7: OwnerService.deleteOwner(con, sc); break;
                    case 8: AnimalService.deleteAnimal(con, sc); break;
                    case 9: System.out.println("Exiting system..."); break;
                    default: System.out.println("Invalid choice. Please try again."); break;
                }

            } while (choice != 9);

            con.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}