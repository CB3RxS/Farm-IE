public class Player {
    private int gold;

    public Player() {
        gold = 100;
    }

    public int getGold() {
        return gold;
    }

    public void spendGold(int amount) throws InsufficientFundsException {
        if (amount > gold) {
            throw new InsufficientFundsException("Not enough gold! You have " + gold + " but need " + amount + ".");
        }
        else {
            gold -= amount;
        }
    }
}
