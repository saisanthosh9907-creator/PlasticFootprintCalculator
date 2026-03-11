package DSA_Java;

import java.util.Scanner;

/**
 * DSA Project: Plastic Footprint Calculator
 * 
 * ALIGNMENT WITH SYLLABUS:
 * - CO1: Implements Bubble Sort to rank plastic usage from highest to lowest.
 * - CO2: Uses Arrays as primary Abstract Data Types to store items.
 * - CO5: A practical application modeled for linear Data Structures.
 * - CO6: A complete, runnable program applying DS concepts to a real-world
 * problem.
 */
public class PlasticFootprintCalculator {

    // Custom data type to hold information about each plastic item
    static class PlasticItem {
        String name;
        int count;
        int weightPerItem;
        int totalFootprint;

        public PlasticItem(String name, int weightPerItem) {
            this.name = name;
            this.weightPerItem = weightPerItem;
            this.count = 0;
            this.totalFootprint = 0;
        }

        // Method to calculate the footprint for this specific item
        public void calculateFootprint() {
            this.totalFootprint = this.count * this.weightPerItem;
        }
    }

    // CO1 Focus: Sorting Algorithm (Bubble Sort)
    // We sort the array of items in descending order so the user sees their worst
    // plastic habit first
    public static void sortItemsByFootprint(PlasticItem[] items) {
        int n = items.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Sorting in descending order (highest footprint first)
                if (items[j].totalFootprint < items[j + 1].totalFootprint) {
                    // Swap elements
                    PlasticItem temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then array is sorted
            if (!swapped)
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // CO2 & CO5 Focus: Linear Data Structure (Array of Objects)
        PlasticItem[] items = new PlasticItem[] {
                new PlasticItem("Plastic Bottles", 12),
                new PlasticItem("Plastic Bags", 5),
                new PlasticItem("Plastic Wrappers", 2),
                new PlasticItem("Plastic Cups", 10)
        };

        System.out.println("============================================");
        System.out.println("   PLASTIC FOOTPRINT CALCULATOR (DSA EXPO)  ");
        System.out.println("============================================");
        System.out.println("Enter the number of plastic items you use daily:\n");

        // 1. Traverse array to take user input
        for (int i = 0; i < items.length; i++) {
            System.out.print("How many " + items[i].name + " do you use? : ");
            items[i].count = scanner.nextInt();
            items[i].calculateFootprint(); // Calculate item footprint immediately
        }

        System.out.println("\nAnalyzing data using DSA concepts...\n");

        // 2. Traverse array to calculate the total overall footprint
        int totalFootprint = 0;
        for (int i = 0; i < items.length; i++) {
            totalFootprint += items[i].totalFootprint;
        }

        // 3. Apply CO1 Algorithm: Sort the items by their footprint to find the highest
        // contributor
        sortItemsByFootprint(items);

        // 4. Output Results
        System.out.println("============================================");
        System.out.println("   YOUR PLASTIC USAGE BREAKDOWN (SORTED)    ");
        System.out.println("============================================");
        // Displaying sorted items shows the user where they need to improve the most
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].name + " -> " + items[i].totalFootprint + " grams");
        }

        System.out.println("--------------------------------------------");
        System.out.println("TOTAL PLASTIC FOOTPRINT: " + totalFootprint + " grams");
        System.out.println("--------------------------------------------");

        // 5. Suggestions / Application Logic
        System.out.print("Suggestion: ");
        if (totalFootprint < 100) {
            System.out.println("Low plastic usage. Good job!");
        } else if (totalFootprint >= 100 && totalFootprint <= 300) {
            System.out.println("Moderate plastic usage. Try to reduce.");
        } else {
            System.out.println("High plastic usage! Reduce plastic consumption.");
        }
        System.out.println("============================================");
        System.out.println("Thank you for using the calculator.");

        scanner.close();
    }
}
