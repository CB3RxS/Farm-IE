public abstract class Plant implements Sellable {
    private final String name;
    private final Rarity rarity;
    private final int baseValue;

    public Plant(String name, Rarity rarity, int baseValue) {
        this.name = name;
        this.rarity = rarity;
        this.baseValue = baseValue;
    }
        @Override 
        public String getName() {
            return name;
        }

        public Rarity getRarity() {
            return rarity;
        }

        public int getBaseValue() {
            return baseValue;
        }

    @Override 
    public int getSellPrice() {
        return getBaseValue() * rarity.getMultiplier();
    }
}
