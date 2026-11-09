import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
            boolean running = true;

            // To Test UI Elements
            int presentDay = 1;
            int gold = 100;
            int plotSize = 4; //Initial starting plot size
            int seedInventory = 2;
            int plantInventory = 0;

            System.out.println("==================================================");
            System.out.println("                     Farm-IE                      ");
            System.out.println("==================================================");

            while (running) {
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.printf("  Day: %d | Gold: %d | Plots: %d | Seeds: %d | Plants: %d",
                        presentDay, gold, plotSize, seedInventory, plantInventory);
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
                System.out.println("Commands: ");
                System.out.println("(1) Manage Farm");
                System.out.println("(2) Shop");
                System.out.println("(3) View Inventory / Sell Plants");
                System.out.println("(4) Add Farm Plots [Cost: " + (plotSize * 50) + " Gold]");
                System.out.println("(5) Sleep [Progress to the next day]");
                System.out.println("(6) Exit Game\n");
                System.out.print("Select an option: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        System.out.println("=> Option 1 selected: View and Manage Farm Plots");
                        break;
                    case "2":
                        System.out.println("=> Option 2 selected: Visit Seed Shop");
                        break;
                    case "3":
                        System.out.println("=> Option 3 selected: View Inventory and Sell Plants");
                        break;
                    case "4":
                        System.out.println("=> Option 4 selected: Expand Farm Plots");
                        plotSize++;
                        gold -= 200; // Testing Gold Reduction
                        break;
                    case "5":
                        System.out.println("=> Option 5 selected: Advance to Next Day");
                        presentDay++;
                        break;
                    case "6":
                        running = false;
                        System.out.println("=> Option 6 selected: Goodbye! Come again soon.");
                        break;
                    default:
                        System.out.println("ERROR!. Please try again.");
                }
            }
        
    }
}
