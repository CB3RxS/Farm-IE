import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
            boolean running = true;


            //Initialize game objects
            Player player = new Player();
            InventoryData inventoryData = new InventoryData();
            Inventory inventory = new Inventory();
            Shop shop = new Shop();

            // Initialize farm plots collection (Starts with 4 plots)
            List<Plot> plots = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                plots.add(new Plot());
            }
            
            int presentDay = 1;

            System.out.println("==================================================");
            System.out.println("                     Farm-IE                      ");
            System.out.println("==================================================");

            while (running) {
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.printf("  Day: %d | Gold: %d | Plots: %d | Seeds: %d | Plants: %d",
                        presentDay, player.getGold(), plots.size(), inventoryData.getSeedCount(), inventoryData.getPlantCount());
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
                System.out.println("Commands: ");
                System.out.println("(1) Manage Farm");
                System.out.println("(2) Shop");
                System.out.println("(3) View Inventory / Sell Plants");
                System.out.println("(4) Add Farm Plots [Cost: " + (plots.size() * 50) + " Gold]");
                System.out.println("(5) Sleep [Progress to the next day]");
                System.out.println("(6) Exit Game\n");
                System.out.print("Select an option: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        Plot.managePlots(scanner, plots, inventoryData);
                        break;
                    case "2":
                        System.out.println("=> Option 2 selected: Visit Seed Shop");
                        shop.openShop(scanner, player, inventoryData);
                        break;
                    case "3":
                        System.out.println("=> Option 3 selected: View Inventory and Sell Plants");
                        inventory.openInventory(scanner, inventoryData);
                        break;
                    case "4":
                        try {
                            int cost = plots.size() * 50;
                            player.spendGold(cost);
                            plots.add(new Plot());
                            System.out.println("Farm expanded! Total plots: " + plots.size());
                        } catch (InsufficientFundsException e) {
                            System.out.println("Expansion Error: " + e.getMessage());
                        }
                        break;
                    case "5": 
                        presentDay++;
                        for (Plot p : plots) {
                            p.nextDay();
                        }
                        System.out.println("Advanced to the next day.");
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
