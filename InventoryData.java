import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryData {
    private final List<Seed> seeds = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    private int speedFertilizerCount;
    private int qualityFertilizerCount;

    // Seed management
    public void addSeed(Seed seed) {
        if (seed != null) {
            seeds.add(seed);
        }
    }

    public Seed findSeed(String seedName) {
        for (Seed seed : seeds) {
            if (seed.getName().equalsIgnoreCase(seedName)) {
                return seed;
            }
        }

        return null;
    }

    public boolean removeSeed(Seed seed) {
        return seeds.remove(seed);
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

    // Harvested plant management
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

    // Fertilizer management
    public void addItem(FarmItem item) {
        if (item == FarmItem.BASIC_SPEED_FERTILIZER) {
            speedFertilizerCount++;
        }

        if (item == FarmItem.BASIC_QUALITY_FERTILIZER) {
            qualityFertilizerCount++;
        }
    }

    public int getItemCount(FarmItem item) {
        if (item == FarmItem.BASIC_SPEED_FERTILIZER) {
            return speedFertilizerCount;
        }

        if (item == FarmItem.BASIC_QUALITY_FERTILIZER) {
            return qualityFertilizerCount;
        }

        return 0;
    }

    public boolean hasItems(boolean useSpeed, boolean useQuality) {
        return (!useSpeed || speedFertilizerCount > 0)
                && (!useQuality || qualityFertilizerCount > 0);
    }

    public void consumeItems(boolean useSpeed, boolean useQuality) {
        if (useSpeed && speedFertilizerCount > 0) {
            speedFertilizerCount--;
        }

        if (useQuality && qualityFertilizerCount > 0) {
            qualityFertilizerCount--;
        }
    }
}
