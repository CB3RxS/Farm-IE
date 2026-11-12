public enum FarmItem {
    BASIC_SPEED_FERTILIZER("Basic Speed Fertilizer", 40,
            "Reduces this plant's growth duration by 1 day. One per plant."),
    BASIC_QUALITY_FERTILIZER("Basic Quality Fertilizer", 50,
            "Increases rare and legendary chances by 10% relative. One per plant.");

    private final String name;
    private final int cost;
    private final String description;

    FarmItem(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}