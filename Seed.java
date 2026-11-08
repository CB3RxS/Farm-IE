import java.util.Random;

public abstract class Seed implements Sellable {
    private final String name;
    private final int cost;
    private final int growthDays;
    private final int rareChance;
    private final int legendaryChance;

    private final Random random = new Random();

    public Seed(String name, int cost, int growthDays, int rareChance, int legendaryChance) {
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
        int roll = random.nextInt(100) + 1;

        if (roll <= legendaryChance) {
            return Rarity.LEGENDARY;
        } else if (roll <= legendaryChance + rareChance) {
            return Rarity.RARE;
        } else {
            return Rarity.COMMON;
        }
    }

    public Plant createPlant() {
        Rarity rarity = rollRarity();
        int type = random.nextInt(3);

        if (type == 0) {
            return new Flower(rarity);
        } else if (type == 1) {
            return new Vegetable(rarity);
        } else {
            return new Fruit(rarity);
        }
    }
}
