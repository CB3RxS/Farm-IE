public class Flower extends Plant{
    public Flower(Rarity rarity) {
        super(chooseName(rarity), rarity, 20);
    }

    private static String chooseName(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return "Daisy";
            case RARE:
                return "Orchid";
            case LEGENDARY:
                return "Moonbloom";
            default:
                return "Unknown";
        }
    }
}