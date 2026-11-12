import java.util.List;
import java.util.Scanner;

public class Plot {
    private Seed seed;
    private double daysLeft;
    private double growthDuration;

    private boolean speedFertilizerApplied;
    private boolean qualityFertilizerApplied;

    public Plot() {
        seed = null; // "plot is empty"
        daysLeft = 0;
        growthDuration = 0.0;
        speedFertilizerApplied = false;
        qualityFertilizerApplied = false;
    }

    public boolean isEmpty() {
        return seed == null;
    }

    public boolean isReady() {
        return seed != null && daysLeft <= 0.0;
    }

    // Returns the remaining time rounded up to a whole day.
    public int getDaysLeft() {
        return (int) Math.ceil(daysLeft);
    }

    // Returns the precise remaining growth time.
    public double getExactDaysLeft() {
        return daysLeft;
    }

    public boolean plant(Seed newSeed) {
        if (isEmpty() == false) {
            return false;
        } 
        seed = newSeed;
        growthDuration = newSeed.getGrowthDays();
        daysLeft = growthDuration;
        speedFertilizerApplied = false;
        qualityFertilizerApplied = false;
        return true;
    }

    public void nextDay() {
        if (isEmpty() == false && daysLeft > 0) {
            daysLeft = Math.max(0.0, daysLeft - 1.0);
        }
    }

    // Applies Speed Fertilizer once, reducing growth duration by one full day.
    public boolean applySpeedFertilizer(InventoryData inventoryData) {
    if (isEmpty() || isReady() || speedFertilizerApplied || !inventoryData.hasItems(true, false)) {
        return false;
    }

    inventoryData.consumeItems(true, false);
    growthDuration = Math.max(0.0, growthDuration - 1.0);
    daysLeft = Math.max(0.0, daysLeft - 1.0);
    speedFertilizerApplied = true;

    return true;
}
    // Applies Quality Fertilizer once. Its effect is used during harvesting.
    public boolean applyQualityFertilizer(InventoryData inventoryData) {
        if (isEmpty() || isReady() || qualityFertilizerApplied
                || !inventoryData.hasItems(false, true)) {
            return false;
        }

        inventoryData.consumeItems(false, true);
        qualityFertilizerApplied = true;

        return true;
    }

    public boolean hasSpeedFertilizerApplied() {
        return speedFertilizerApplied;
    }

    public boolean hasQualityFertilizerApplied() {
        return qualityFertilizerApplied;
    }


    public Plant harvest() {
        if (!isReady()) {
            return null;
        }

        Plant harvestedPlant = seed.createPlant(qualityFertilizerApplied);

        clearPlot();

        return harvestedPlant;
    }

    private void clearPlot() {
        seed = null;
        daysLeft = 0.0;
        growthDuration = 0.0;

        speedFertilizerApplied = false;
        qualityFertilizerApplied = false;
    }

    public Seed getSeed() {
        return seed;
    }

    public double getGrowthPercentage() {
        if (isEmpty()) {
            return 0.0;
        }

        if (isReady()) {
            return 100.0;
        }

        double elapsedDays = growthDuration - daysLeft;

        return (elapsedDays / growthDuration) * 100.0;
    }

    // Handles farm plot interaction from the main menu.
    public static void managePlots(Scanner scanner, List<Plot> plots, InventoryData inventoryData) 
    
    {
        System.out.println("-------------------------------------------------");
        System.out.println("                   FARM PLOTS                    ");
        System.out.println("-------------------------------------------------");

        for (int i = 0; i < plots.size(); i++) {
            Plot plot = plots.get(i);
            String status;

            if (plot.isEmpty()) {
                status = "Empty";
            } else if (plot.isReady()) {
                status = "Ready for Harvest (" + plot.getSeed().getName() + ")";
            } else {
                status = String.format(
                        "Growing %s - %.0f%% complete (%.1f days left)",
                        plot.getSeed().getName(),
                        plot.getGrowthPercentage(),
                        plot.getExactDaysLeft()
                );
            }

            System.out.println("Plot [" + (i + 1) + "]: " + status);
        }

        System.out.print("\nEnter plot number to interact with (or 0 to return): ");

        try {
            int plotIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (plotIndex < 0 || plotIndex >= plots.size()) {
                return;
            }

            Plot target = plots.get(plotIndex);

            if (target.isReady()) {
                Plant harvested = target.harvest();

                if (harvested != null) {
                    inventoryData.addPlant(harvested);

                    System.out.println("Harvested " + harvested.getRarity()
                            + " " + harvested.getName() + "!");
                }

            } else if (target.isEmpty()) {
                List<Seed> seedList = inventoryData.getSeeds();

                if (seedList.isEmpty()) {
                    System.out.println(
                            "You have no seeds in your inventory. Visit the shop first."
                    );
                    return;
                }

                System.out.println("\n--- AVAILABLE SEEDS ---");

                for (int i = 0; i < seedList.size(); i++) {
                    Seed seed = seedList.get(i);

                    System.out.println("[" + (i + 1) + "] " + seed.getName()
                            + " (Growth: " + seed.getGrowthDays() + " days)");
                }

                System.out.print("Select seed number to plant (0 to cancel): ");

                int seedIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

                if (seedIndex >= 0 && seedIndex < seedList.size()) {
                    Seed chosenSeed = seedList.get(seedIndex);
                    Seed removedSeed = inventoryData.takeSeed(chosenSeed.getName());

                    if (removedSeed != null && target.plant(removedSeed)) {
                        System.out.println("Successfully planted "
                                + removedSeed.getName() + ".");
                    }
                }

            } else {
                interactWithGrowingPlot(scanner, target, inventoryData);
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input number.");
        }
    }

    // Allows the player to apply fertilizers to a growing plant.
    private static void interactWithGrowingPlot(Scanner scanner, Plot plot,
                                                InventoryData inventoryData) {

        boolean interacting = true;

        while (interacting) {
            System.out.println("\n---------------- GROWING PLOT ----------------");
            System.out.println("Planted Seed: " + plot.getSeed().getName());

            System.out.printf("Growth: %.1f%% | %.1f days remaining%n",
                    plot.getGrowthPercentage(),
                    plot.getExactDaysLeft());

            System.out.println("\nFertilizers:");

            System.out.println("[1] Apply Basic Speed Fertilizer"
                    + (plot.hasSpeedFertilizerApplied() ? " (Applied)" : ""));

            System.out.println("[2] Apply Basic Quality Fertilizer"
                    + (plot.hasQualityFertilizerApplied() ? " (Applied)" : ""));

            System.out.println("[0] Return");

            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    if (plot.hasSpeedFertilizerApplied()) {
                        System.out.println("Speed Fertilizer has already been applied to this plant."
                        );

                    } else if (inventoryData.getItemCount(
                            FarmItem.BASIC_SPEED_FERTILIZER) == 0) {

                        System.out.println("You do not have any Basic Speed Fertilizer.");

                    } else if (plot.applySpeedFertilizer(inventoryData)) {
                        System.out.println("Speed Fertilizer applied! Growth duration reduced by 1 day.");
                    }
                    break;

                case "2":
                    if (plot.hasQualityFertilizerApplied()) {
                        System.out.println("Quality Fertilizer has already been applied to this plant.");

                    } else if (inventoryData.getItemCount(
                            FarmItem.BASIC_QUALITY_FERTILIZER) == 0) {
                        System.out.println("You do not have any Basic Quality Fertilizer.");

                    } else if (plot.applyQualityFertilizer(inventoryData)) {
                        System.out.println("Quality Fertilizer applied! Rare and Legendary chances increased by 10% relative.");
                    }
                    break;

                case "0":
                    interacting = false;
                    break;

                default:
                    System.out.println("Invalid selection. Please try again.");
            }

            if (plot.isReady()) {
                interacting = false;
            }
        }
    }
}
