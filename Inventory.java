import java.util.*;

public class Inventory {

    private final List<Seed> seeds;
    private final List<Plant> plants;

    public Inventory() {
        seeds = new ArrayList<>();
        plants = new ArrayList<>();
    }

    // SEED MANAGEMENT

    public void addSeed(Seed seed) {
        if (seed != null) {
            seeds.add(seed);
        }
    }

    public Seed takeSeed(String seedName) {
        for (int i = 0; i < seeds.size(); i++) {
            if (seeds.get(i).getName().equalsIgnoreCase(seedName)) {
                return seeds.remove(i);
            }
        }

        return null;
    }

    public int getSeedCount() {
        return seeds.size();
    }

    public int getSeedCount(String seedName) {
        int count = 0;

        for (Seed seed : seeds) {
            if (seed.getName().equalsIgnoreCase(seedName)) {
                count++;
            }
        }

        return count;
    }

    public List<Seed> getSeeds() {
        return Collections.unmodifiableList(seeds);
    }

    // PLANT MANAGEMENT

    public void addPlant(Plant plant) {
        if (plant != null) {
            plants.add(plant);
        }
    }

    public Plant removePlant(int index) {
        if (index < 0 || index >= plants.size()) {
            return null;
        }

        return plants.remove(index);
    }

    public int getPlantCount() {
        return plants.size();
    }

    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    // INVENTORY DISPLAY

    public void displayInventory() {

        System.out.println("\n==================================================");
        System.out.println("                  PLAYER INVENTORY                ");
        System.out.println("==================================================");

        // DISPLAY SEEDS

        System.out.println("\n[1] SEEDS");
        System.out.println("-----------------------------------------------");

        if (seeds.isEmpty()) {
            System.out.println("No seeds available.");
        } else {

            System.out.printf("%-20s %10s%n", "Seed Type", "Quantity");
            System.out.println("-----------------------------------------------");

            System.out.printf("%-20s %10d%n",
                    "Basic Seed", getSeedCount("Basic Seed"));

            System.out.printf("%-20s %10d%n",
                    "Silver Seed", getSeedCount("Silver Seed"));

            System.out.printf("%-20s %10d%n",
                    "Golden Seed", getSeedCount("Golden Seed"));

            System.out.println("-----------------------------------------------");
            System.out.println("Total Seeds: " + getSeedCount());
        }

        // DISPLAY HARVESTED PLANTS

        System.out.println("\n[2] HARVESTED PLANTS");
        System.out.println("-----------------------------------------------");

        if (plants.isEmpty()) {
            System.out.println("No harvested plants available.");
        } else {

            System.out.printf("%-5s %-18s %-12s %-12s %10s%n",
                    "No.", "Plant Name", "Type", "Rarity", "Sell Price");

            System.out.println("------------------------------------------------------------------");

            for (int i = 0; i < plants.size(); i++) {

                Plant plant = plants.get(i);

                System.out.printf("%-5d %-18s %-12s %-12s %7d Gold%n",
                        i + 1,
                        plant.getName(),
                        plant.getType(),
                        plant.getRarity(),
                        plant.getSellPrice());
            }

            System.out.println("------------------------------------------------------------------");
            System.out.println("Total Harvested Plants: " + getPlantCount());
        }

        System.out.println("\n==================================================");
    }
}
