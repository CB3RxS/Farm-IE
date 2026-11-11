import java.util.List;
import java.util.Scanner;

public class Plot {
    private Seed seed;
    private int daysLeft;

    public Plot() {
        seed = null; // "plot is empty"
        daysLeft = 0;
    }

    public boolean isEmpty() {
        if (seed == null) {
            return true;
        }
        return false;
    }

    public boolean isReady() {
        if (seed != null && daysLeft == 0) {
            return true;
        }
        return false;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public boolean plant(Seed newSeed) {
        if (isEmpty() == false) {
            return false;
        } 
        seed = newSeed;
        daysLeft = newSeed.getGrowthDays();
        return true;
    }

    public void nextDay() {
        if (isEmpty() == false && daysLeft > 0) {
            daysLeft -= 1;
        }
    }


    // Harvest: returns the Plant and empties the plot
    // Only call this when isReady() is true (caller checks first)
    public Plant harvest() {
        Plant harvestedPlant = seed.createPlant();
        seed = null;
        daysLeft = 0;
        return harvestedPlant;
    }

    // Returns the seed currently planted in the plot
    public Seed getSeed() {
        return seed;
    }

    // Calculates the growth progress percentage (0% to 100%)
    public double getGrowthPercentage() {
        if (seed == null) {
            return 0.0;
        }
        if (isReady()) {
            return 100.0;
        }
        int totalDays = seed.getGrowthDays();
        int elapsedDays = totalDays - daysLeft;
        return ((double) elapsedDays / totalDays) * 100.0;
    }

    // Handles (1) in Main.java: Manage Farm 
    public static void managePlots(Scanner scanner, List<Plot> plots, InventoryData inventoryData) {
        System.out.println("-------------------------------------------------");
        System.out.println("                   FARM PLOTS                    ");
        System.out.println("-------------------------------------------------\n");
        for (int i = 0; i < plots.size(); i++) {
            Plot p = plots.get(i);
            String status;
            if (p.isEmpty()) {
                status = "Empty";
            } else if (p.isReady()) {
                status = "Ready for Harvest (" + p.getSeed().getName() + ")";
            } else {
                status = String.format("Growing %s - %.0f%% complete (%d days left)",
                        p.getSeed().getName(), p.getGrowthPercentage(), p.getDaysLeft());
            }
            System.out.println("Plot [" + (i + 1) + "]: " + status);
        }

        System.out.print("\nEnter plot number to interact with (or 0 to return): ");
        try {
            int plotIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (plotIndex >= 0 && plotIndex < plots.size()) {
                Plot target = plots.get(plotIndex);
                if (target.isReady()) {
                    Plant harvested = target.harvest();
                    if (harvested != null) {
                        inventoryData.addPlant(harvested);
                        System.out.println("Harvested " + harvested.getRarity() + " " + harvested.getName() + "!");
                    }
                } else if (target.isEmpty()) {
                    List<Seed> seedList = inventoryData.getSeeds(); 
                    if (seedList == null || seedList.isEmpty()) {
                        System.out.println("You have no seeds in your inventory. Visit the shop first.");
                    } else {
                        System.out.println("\n--- AVAILABLE SEEDS ---");
                        for (int s = 0; s < seedList.size(); s++) {
                            Seed seedObj = seedList.get(s);
                            System.out.println("[" + (s + 1) + "] " + seedObj.getName() + " (Growth: " + seedObj.getGrowthDays() + " days)");
                        }
                        System.out.print("Select seed number to plant (0 to cancel): ");
                        int seedIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                        if (seedIndex >= 0 && seedIndex < seedList.size()) {
                            Seed chosenSeed = seedList.get(seedIndex);
                            
                            // Removes seed from inventory data using its name.
                            Seed removedSeed = inventoryData.takeSeed(chosenSeed.getName());
                            if (removedSeed != null) {
                                target.plant(removedSeed);
                                System.out.println("Successfully planted " + removedSeed.getName() + ".");
                            }
                        }
                    }
                } else {
                    System.out.println("This plot is still growing (" + (int)target.getGrowthPercentage() + "%).");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input number.");
        }
    }

    
}
