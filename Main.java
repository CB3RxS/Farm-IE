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
            
            // To Test UI Elements
            int presentDay = 1;
            int plotSize = 4; //Initial starting plot size

            System.out.println("==================================================");
            System.out.println("                     Farm-IE                      ");
            System.out.println("==================================================");

            while (running) {
                System.out.println("\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.printf("  Day: %d | Gold: %d | Plots: %d | Seeds: %d | Plants: %d",
                        presentDay, player.getGold(), plotSize, inventoryData.getSeedCount(), inventoryData.getPlantCount());
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
                        shop.openShop(scanner, player, inventoryData);
                        break;
                    case "3":
                        System.out.println("=> Option 3 selected: View Inventory and Sell Plants");
                        inventory.openInventory(scanner, inventoryData);
                        break;
                    case "4":
                        System.out.println("=> Option 4 selected: Expand Farm Plots");
                        plotSize++;
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
