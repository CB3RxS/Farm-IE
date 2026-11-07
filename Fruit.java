public class Fruit extends Plant{
    public Fruit(Rarity rarity) {
        super(chooseName(rarity), rarity, 40);
    }

    private static String chooseName(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return "Banana";
            case RARE:
                return "Mango";
            case LEGENDARY:
                return "Dragonfruit";
            default:
                return "Unknown";
        }
    }
}
