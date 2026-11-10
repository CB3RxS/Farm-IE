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
}
