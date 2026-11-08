public class TestRun {
    public static void main(String[] args) {
        Seed s = new GoldenSeed();
        int common = 0, rare = 0, legendary = 0;
        for (int i = 0; i < 1000; i++) {
            Rarity r = s.rollRarity();
            
            if (r == Rarity.COMMON) {
                common += 1;
            } else if (r == Rarity.RARE) {
                rare += 1;
            } else {
                legendary += 1;
            }
        }
        System.out.println(common + " / " + rare + " / " + legendary);
        // expected roughly: 350 / 400 / 250 (it won't be exact, it's random)

        Plant p = s.createPlant();
        System.out.println("Grew a " + p.getName() + " worth " + p.getSellPrice());

        Player pl = new Player();
        try {
            pl.spendGold(30);
            System.out.println("This line SHOULD NOT print.");
        } catch (InsufficientFundsException e){
            System.out.println("Gold is still " + pl.getGold());
        }
    }
}