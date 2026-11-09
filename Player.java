public class Player {
    private int gold;

    public Player() {
        gold = 100;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void spendGold(int amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot spend a negative amount of gold!: " + amount);
        }
        
        if (amount > gold) {
            throw new InsufficientFundsException("Not enough gold! You have " + gold + " but need " + amount + ".");
        }
        gold -= amount;
    }
}
