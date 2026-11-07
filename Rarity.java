public enum Rarity {
    COMMON(1),
    RARE(5),
    LEGENDARY(20);

    private final int multiplier;

    Rarity(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}

