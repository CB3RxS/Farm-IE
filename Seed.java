import java.util.Random;

public abstract class Seed implements Sellable {
    private final String name;
    private final int cost;
    private final int growthDays;
    private final double rareChance;
    private final double legendaryChance;

    private final Random random = new Random();

    public Seed(String name, int cost, int growthDays,
                int rareChance, int legendaryChance) {

        this.name = name;
        this.cost = cost;
        this.growthDays = growthDays;
        this.rareChance = rareChance;
        this.legendaryChance = legendaryChance;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getGrowthDays() {
        return growthDays;
    }

    @Override
    public int getSellPrice() {
        return cost / 2;
    }

    public Rarity rollRarity() {
        return rollRarity(false);
    }

    // Quality fertilizer increases higher rarity chances by 10%. The increase is deducted from the Common probability.
    public Rarity rollRarity(boolean useQualityFertilizer) {
        double rare = rareChance;
        double legendary = legendaryChance;

        if (useQualityFertilizer) {
            rare += rareChance * 0.10;
            legendary += legendaryChance * 0.10;
        }

        double roll = random.nextDouble() * 100.0;

        if (roll < legendary) {
            return Rarity.LEGENDARY;
        }

        if (roll < legendary + rare) {
            return Rarity.RARE;
        }

        return Rarity.COMMON;
    }

    public Plant createPlant() {
        return createPlant(false);
    }

    public Plant createPlant(boolean useQualityFertilizer) {
        Rarity rarity = rollRarity(useQualityFertilizer);

        // Randomly selects Flower, Vegetable, or Fruit.
        int type = random.nextInt(3);

        if (type == 0) {
            return new Flower(rarity);
        }

        if (type == 1) {
            return new Vegetable(rarity);
        }

        return new Fruit(rarity);
    }
}
