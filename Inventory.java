
import java.util.Scanner;

public class Inventory {

    public void openInventory(Scanner scanner, InventoryData inventory) {
        boolean viewing = true;

        while (viewing) {
            System.out.println("\n==================================================");
            System.out.println("                  PLAYER INVENTORY                ");
            System.out.println("==================================================");
            System.out.println("[1] Seeds");
            System.out.println("[2] Harvested Plants");
            System.out.println("[3] Items / Fertilizers");
            System.out.println("[4] Return to Main Menu");
            System.out.print("\nSelect a category: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displaySeeds(scanner, inventory);
                    break;

                case "2":
                    displayPlants(scanner, inventory);
                    break;

                case "3":
                    displayItems(scanner, inventory);
                    break;

                case "4":
                    viewing = false;
                    System.out.println("\nReturning to Main Menu...");
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private void displaySeeds(Scanner scanner, InventoryData inventory) {
        System.out.println("\n---------------- SEED INVENTORY ----------------");
        System.out.printf("%-22s %10s%n", "Seed", "Quantity");
        System.out.println("-------------------------------------------------");

        System.out.printf("%-22s %10d%n",
                "Basic Seed", inventory.getSeedCount("Basic Seed"));

        System.out.printf("%-22s %10d%n",
                "Silver Seed", inventory.getSeedCount("Silver Seed"));

        System.out.printf("%-22s %10d%n",
                "Golden Seed", inventory.getSeedCount("Golden Seed"));

        System.out.println("-------------------------------------------------");
        System.out.println("Total Seeds: " + inventory.getSeedCount());

        waitForReturn(scanner);
    }

    private void displayPlants(Scanner scanner, InventoryData inventory) {
        System.out.println("\n---------------- HARVESTED PLANTS ----------------");

        if (inventory.getPlants().isEmpty()) {
            System.out.println("You have no harvested plants.");
        } else {
            System.out.printf("%-4s %-19s %-12s %-12s %10s%n",
                    "No.", "Plant", "Category", "Rarity", "Sell Price");

            System.out.println("---------------------------------------------------------------------");

            for (int i = 0; i < inventory.getPlants().size(); i++) {
                Plant plant = inventory.getPlants().get(i);

                System.out.printf("%-4d %-19s %-12s %-12s %7d Gold%n",
                        i + 1,
                        plant.getName(),
                        plant.getType(),
                        plant.getRarity(),
                        plant.getSellPrice()
                );
            }

            System.out.println("---------------------------------------------------------------------");
            System.out.println("Total Plants: " + inventory.getPlantCount());
        }

        waitForReturn(scanner);
    }

    private void displayItems(Scanner scanner, InventoryData inventory) {
        System.out.println("\n---------------- ITEM INVENTORY ----------------");
        System.out.printf("%-30s %10s%n", "Item", "Quantity");
        System.out.println("-------------------------------------------------");

        System.out.printf("%-30s %10d%n",
                "Basic Speed Fertilizer",
                inventory.getItemCount(FarmItem.BASIC_SPEED_FERTILIZER));

        System.out.printf("%-30s %10d%n",
                "Basic Quality Fertilizer",
                inventory.getItemCount(FarmItem.BASIC_QUALITY_FERTILIZER));

        System.out.println("-------------------------------------------------");

        waitForReturn(scanner);
    }

    private void waitForReturn(Scanner scanner) {
        System.out.print("\nPress ENTER to return to Inventory Menu...");
        scanner.nextLine();
    }
}
