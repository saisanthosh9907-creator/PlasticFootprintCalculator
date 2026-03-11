package DSA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * DSA Project: Plastic Footprint Calculator
 * 
 * Data Structures & Algorithms Concepts Demonstrated:
 * - HashMap: For O(1) time complexity User Authentication (Signup/Login)
 * - ArrayList: For dynamic tracking of Usage History
 * - Linear Search: To find matching objects within the usage history
 * - Traversal Algorithms: Computing iterative totals logically across the ArrayList
 */
public class PlasticFootprintCalculator {

    // --- DSA Concept #1: HashMap for storing user accounts ---
    // Key: Username (String), Value: Password (String)
    private static HashMap<String, String> usersDatabase = new HashMap<>();

    // --- DSA Concept #2: ArrayList for storing plastic usage data ---
    // Dynamically tracks the logged records for the active session
    private static ArrayList<UsageRecord> usageHistory = new ArrayList<>();

    // State Variables
    private static String currentLoggedInUser = null;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Inner class acting as a structured node for ArrayList data
     */
    static class UsageRecord {
        String itemName;
        int quantity;
        int weightPerItem;
        int totalWeight;

        public UsageRecord(String itemName, int quantity, int weightPerItem) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.weightPerItem = weightPerItem;
            this.totalWeight = quantity * weightPerItem;
        }

        @Override
        public String toString() {
            return String.format("Item: %-15s | Qty: %-3d | Weight/item: %-3d g | Total: %d g", 
                    itemName, quantity, weightPerItem, totalWeight);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println(" PLASTIC FOOTPRINT CALCULATOR (DATA STRUCTURES & ALGORITHMS) ");
        System.out.println("=========================================================");

        boolean exitSystem = false;
        while (!exitSystem) {
            System.out.println("\n------------- MAIN MENU -------------");
            
            if (currentLoggedInUser == null) {
                System.out.println("1. Signup");
                System.out.println("2. Login");
                System.out.println("7. Exit");
            } else {
                System.out.println(" Welcome User: [" + currentLoggedInUser + "]");
                System.out.println("-------------------------------------");
                System.out.println("3. Add Plastic Usage");
                System.out.println("4. View Plastic Usage History");
                System.out.println("5. Calculate Total Plastic Footprint");
                System.out.println("6. Search Plastic Item");
                System.out.println("7. Logout / Exit");
            }

            System.out.print("\nEnter your choice: ");
            int option = -1;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            if (currentLoggedInUser == null) {
                switch (option) {
                    case 1: processSignup(); break;
                    case 2: processLogin(); break;
                    case 7: 
                        exitSystem = true; 
                        System.out.println("Exiting the application. Keep tracking your footprint!"); 
                        break;
                    default: System.out.println("Access Denied: Please Login or Signup first.");
                }
            } else {
                switch (option) {
                    case 3: insertPlasticUsage(); break;
                    case 4: traverseHistory(); break;
                    case 5: computeFootprint(); break;
                    case 6: searchItem(); break;
                    case 7: 
                        currentLoggedInUser = null; 
                        usageHistory.clear(); // Clear the DSA ArrayList when user logs out
                        System.out.println("Logged out successfully."); 
                        break;
                    default: System.out.println("Invalid Menu Option. Please try again.");
                }
            }
        }
        scanner.close();
    }

    // ==========================================================
    // MODULE 1: USER AUTHENTICATION
    // ==========================================================

    private static void processSignup() {
        System.out.println("\n--- SIGNUP ---");
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();

        // DSA Concept: HashMap Retrieval / Search O(1)
        if (usersDatabase.containsKey(username)) {
            System.out.println("Error: Username already exists! Please try a different one.");
        } else {
            System.out.print("Enter a secure password: ");
            String password = scanner.nextLine();
            
            // DSA Concept: Insertion operation into HashMap
            usersDatabase.put(username, password);
            System.out.println("Success! Account created. You may now login.");
        }
    }

    private static void processLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        // DSA Concept: HashMap exact match lookups
        if (usersDatabase.containsKey(username) && usersDatabase.get(username).equals(password)) {
            currentLoggedInUser = username;
            System.out.println("Login Authenticated! Welcome back.");
        } else {
            System.out.println("Error: Invalid Credentials. Please check username and password.");
        }
    }

    // ==========================================================
    // MODULE 2: DATA STRUCTURE INSERTION (ADD USAGE)
    // ==========================================================

    private static void insertPlasticUsage() {
        System.out.println("\n--- ADD PLASTIC USAGE ---");
        System.out.println("Available categories:");
        System.out.println("  1. Plastic Bottles (12g)");
        System.out.println("  2. Plastic Bags (5g)");
        System.out.println("  3. Food Wrappers (2g)");
        System.out.println("  4. Plastic Cups (10g)");
        System.out.print("Select item type (1-4): ");
        
        int itemChoice = -1;
        try {
            itemChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid selection.");
            return;
        }

        String itemName = "";
        int weight = 0;

        switch (itemChoice) {
            case 1: itemName = "Plastic Bottles"; weight = 12; break;
            case 2: itemName = "Plastic Bags";    weight = 5;  break;
            case 3: itemName = "Food Wrappers";   weight = 2;  break;
            case 4: itemName = "Plastic Cups";    weight = 10; break;
            default: System.out.println("Unknown category mapping. Returning to menu."); return;
        }

        System.out.print("Enter quantity consumed: ");
        int qty = 0;
        try {
            qty = Integer.parseInt(scanner.nextLine());
            if (qty < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println("Quantity must be a positive integer.");
            return;
        }

        // DSA Concept: Object creation and Insertion operation into Dynamic Array list
        UsageRecord newRecord = new UsageRecord(itemName, qty, weight);
        usageHistory.add(newRecord);
        System.out.println("Success! Added: " + qty + "x " + itemName);
    }

    // ==========================================================
    // MODULE 3: TRAVERSAL - VIEW HISTORY
    // ==========================================================

    private static void traverseHistory() {
        System.out.println("\n--- PLASTIC USAGE HISTORY ---");
        
        // Check if data structure is empty
        if (usageHistory.isEmpty()) {
            System.out.println("No records found in database.");
            return;
        }
        
        // DSA Concept: Traversal Algorithms through ArrayList
        // O(N) iteration where N is the number of logs
        for (int i = 0; i < usageHistory.size(); i++) {
            System.out.println("ID " + (i + 1) + ": " + usageHistory.get(i).toString());
        }
    }

    // ==========================================================
    // MODULE 4: COMPUTATION LOGIC & REDUCTION SUGGESTION
    // ==========================================================

    private static void computeFootprint() {
        System.out.println("\n--- CALCULATE TOTAL FOOTPRINT ---");
        
        if (usageHistory.isEmpty()) {
            System.out.println("Insufficient data. Add records first.");
            return;
        }

        // DSA Concept: Iterative Traversal to accumulate values
        int totalPlasticGrams = 0;
        for (UsageRecord record : usageHistory) {
            totalPlasticGrams += record.totalWeight;
        }

        System.out.println("******************************************");
        System.out.println(" Accumulated Total Plastic: " + totalPlasticGrams + " grams");
        System.out.println("******************************************");

        // System Evaluation Logic based on thresholds
        System.out.print("\n[SYSTEM SUGGESTION]: ");
        if (totalPlasticGrams <= 100) {
            System.out.println("Low Plastic Usage.");
            System.out.println("Great job! Your footprint is minimal. Keep utilizing reusable materials.");
        } else if (totalPlasticGrams > 100 && totalPlasticGrams <= 300) {
            System.out.println("Moderate Plastic Usage.");
            System.out.println("Try to reduce your dependency on single-use plastics bags and bottles.");
        } else {
            System.out.println("High Plastic Usage!");
            System.out.println("Warning! Switch immediately to sustainable alternatives like cloth bags or metal bottles.");
        }
    }

    // ==========================================================
    // MODULE 5: SEARCH FUNCTIONALITY
    // ==========================================================

    private static void searchItem() {
        System.out.println("\n--- SEARCH PLASTIC ITEM ---");
        
        if (usageHistory.isEmpty()) {
            System.out.println("History is empty. Nothing to search.");
            return;
        }

        System.out.print("Enter itemName to search closely (e.g., 'bags', 'cups'): ");
        String keyword = scanner.nextLine().toLowerCase();

        boolean foundMatch = false;
        
        System.out.println("Search Results:");
        // DSA Concept: Linear Search Algorithm. O(N) complexity traversing through ArrayList.
        for (UsageRecord record : usageHistory) {
            if (record.itemName.toLowerCase().contains(keyword)) {
                System.out.println(">> " + record.toString());
                foundMatch = true;
            }
        }

        if (!foundMatch) {
            System.out.println("No elements matched the search query '" + keyword + "'.");
        }
    }
}
