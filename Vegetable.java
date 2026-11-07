public class Vegetable extends Plant{
    public Vegetable(Rarity rarity) {
        super(chooseName(rarity), rarity, 30);
    }

    private static String chooseName(Rarity rarity) {
        switch (rarity) {
            case COMMON:
                return "Carrot";
            case RARE:
                return "Purple Yam";
            case LEGENDARY:
                return "Golden Pumpkin";
            default:
                return "Unknown";
        }
    }
}
