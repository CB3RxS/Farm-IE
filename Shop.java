import java.util.Scanner;

public class Shop {

    public void openShop(Scanner scanner, Player player, Inventory inventory) {

        boolean shopping = true;

        while (shopping) {

            System.out.println("\n==================================================");
            System.out.println("                    SEED SHOP                     ");
            System.out.println("==================================================");

            System.out.println("Available Gold: " + player.getGold());

            System.out.println("\nAvailable Seeds:");
            System.out.println("-----------------------------------------------");

            System.out.println("[1] Basic Seed   - 25 Gold  | Growth: 2 Days");
            System.out.println("[2] Silver Seed  - 50 Gold  | Growth: 3 Days");
            System.out.println("[3] Golden Seed  - 150 Gold | Growth: 4 Days");

            System.out.println("-----------------------------------------------");
            System.out.println("[4] Return to Main Menu");

            System.out.print("\nSelect a seed to purchase: ");

            String choice = scanner.nextLine().trim();

            Seed selectedSeed = null;

            switch (choice) {

                case "1":
                    selectedSeed = new BasicSeed();
                    break;

                case "2":
                    selectedSeed = new SilverSeed();
                    break;

                case "3":
                    selectedSeed = new GoldenSeed();
                    break;

                case "4":
                    shopping = false;
                    System.out.println("\nReturning to Main Menu...");
                    continue;

                default:
                    System.out.println("\nInvalid selection. Please try again.");
                    continue;
            }

            // Process the purchase.

            try {

                player.spendGold(selectedSeed.getCost());

                inventory.addSeed(selectedSeed);

                System.out.println("\nPurchase successful!");
                System.out.println("Purchased: " + selectedSeed.getName());
                System.out.println("Cost: " + selectedSeed.getCost() + " Gold");
                System.out.println("Remaining Gold: " + player.getGold());

                System.out.println("Updated Seed Quantity: "
                        + inventory.getSeedCount(selectedSeed.getName()));

            } catch (InsufficientFundsException e) {

                System.out.println("\nPurchase failed!");
                System.out.println(e.getMessage());

            }
        }
    }
}
