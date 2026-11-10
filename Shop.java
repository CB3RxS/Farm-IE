import java.util.Scanner;

public class Shop {

    public void openShop(Scanner scanner, Player player, InventoryData inventory) {
        String currentMenu = "MAIN";

        while (!currentMenu.equals("EXIT")) {

            // Main Shop Menu
            if (currentMenu.equals("MAIN")) {
                System.out.println("\n==================================================");
                System.out.println("                      SHOP                        ");
                System.out.println("==================================================");
                System.out.println("Available Gold: " + player.getGold());

                System.out.println("\n[1] Seeds");
                System.out.println("[2] Items / Fertilizers");
                System.out.println("[3] Sell Harvested Plants");
                System.out.println("[4] Exit Shop");

                System.out.print("\nSelect a shop category: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        currentMenu = "SEEDS";
                        break;

                    case "2":
                        currentMenu = "ITEMS";
                        break;

                    case "3":
                        sellPlants(scanner, player, inventory);
                        break;

                    case "4":
                        currentMenu = "EXIT";
                        System.out.println("\nReturning to Main Menu...");
                        break;

                    default:
                        System.out.println("Invalid selection. Please try again.");
                }
            }

            // Seed Shop Menu
            else if (currentMenu.equals("SEEDS")) {
                System.out.println("\n==================================================");
                System.out.println("                     SEED SHOP                      ");
                System.out.println("==================================================");
                System.out.println("Available Gold: " + player.getGold());

                System.out.println("[1] Basic Seed  - 25 Gold | 2 days | Rare 10%, Legendary 1%");
                System.out.println("[2] Silver Seed - 50 Gold | 3 days | Rare 30%, Legendary 8%");
                System.out.println("[3] Golden Seed - 150 Gold | 4 days | Rare 40%, Legendary 25%");
                System.out.println("[4] Items");
                System.out.println("[5] Exit");

                System.out.print("Select an option: ");

                String choice = scanner.nextLine().trim();
                Seed seed = null;

                switch (choice) {
                    case "1":
                        seed = new BasicSeed();
                        break;

                    case "2":
                        seed = new SilverSeed();
                        break;

                    case "3":
                        seed = new GoldenSeed();
                        break;

                    case "4":
                        currentMenu = "ITEMS";
                        break;

                    case "5":
                        currentMenu = "MAIN";
                        System.out.println("\nReturning to Shop Menu...");
                        break;

                    default:
                        System.out.println("Invalid selection.");
                        break;
                }

                // Process seed purchase
                if (seed != null) {
                    try {
                        player.spendGold(seed.getCost());
                        inventory.addSeed(seed);

                        System.out.println("\nPurchased " + seed.getName()
                                + " for " + seed.getCost() + " Gold.");

                        System.out.println("Owned quantity: "
                                + inventory.getSeedCount(seed.getName()));

                        System.out.println("Remaining Gold: "
                                + player.getGold());

                    } catch (InsufficientFundsException e) {
                        System.out.println("Purchase failed: " + e.getMessage());
                    }
                }
            }

            // Item Shop Menu
            else if (currentMenu.equals("ITEMS")) {
                System.out.println("\n==================================================");
                System.out.println("                     ITEM SHOP                      ");
                System.out.println("==================================================");
                System.out.println("Available Gold: " + player.getGold());

                System.out.println("[1] Basic Speed Fertilizer   - 40 Gold");
                System.out.println("[2] Basic Quality Fertilizer - 50 Gold");
                //Will add more options later

                System.out.println("[3] Seeds");
                System.out.println("[4] Exit");

                System.out.print("Select an option: ");

                String choice = scanner.nextLine().trim();
                FarmItem item = null;

                switch (choice) {
                    case "1":
                        item = FarmItem.BASIC_SPEED_FERTILIZER;
                        break;

                    case "2":
                        item = FarmItem.BASIC_QUALITY_FERTILIZER;
                        break;

                    case "3":
                        currentMenu = "SEEDS";
                        break;

                    case "4":
                        currentMenu = "MAIN";
                        System.out.println("\nReturning to Shop Menu...");
                        break;

                    default:
                        System.out.println("Invalid selection.");
                        break;
                }

                // Process item purchase
                if (item != null) {
                    try {
                        player.spendGold(item.getCost());
                        inventory.addItem(item);

                        System.out.println("\nPurchased " + item.getName() + ".");

                        System.out.println("Owned quantity: "
                                + inventory.getItemCount(item));

                        System.out.println("Remaining Gold: "
                                + player.getGold());

                    } catch (InsufficientFundsException e) {
                        System.out.println("Purchase failed: " + e.getMessage());
                    }
                }
            }
        }
    }

    private void sellPlants(Scanner scanner, Player player, InventoryData inventory) {

        boolean selling = true;

        while (selling) {
                System.out.println("\n==================================================");
                System.out.println("                       SELL                       ");
                System.out.println("==================================================");

            if (inventory.getPlants().isEmpty()) {
                System.out.println("You have no harvested plants to sell.");
                return;
            }

            for (int i = 0; i < inventory.getPlants().size(); i++) {
                Plant plant = inventory.getPlants().get(i);

                System.out.printf("[%d] %s | %s | %s | %d Gold%n",
                        i + 1,
                        plant.getName(),
                        plant.getType(),
                        plant.getRarity(),
                        plant.getSellPrice()
                );
            }

            System.out.println("[0] Return");
            System.out.print("Select a plant to sell: ");

            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                selling = false;
                continue;
            }

            try {
                int selection = Integer.parseInt(input) - 1;
                Plant plant = inventory.removePlant(selection);

                if (plant == null) {
                    System.out.println("Invalid plant selection.");

                } else {
                    int earnings = plant.getSellPrice();

                    player.addGold(earnings);

                    System.out.println("Sold " + plant.getName()
                            + " for " + earnings + " Gold.");

                    System.out.println("Remaining plants: "
                            + inventory.getPlantCount());

                    System.out.println("Current Gold: "
                            + player.getGold());
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
