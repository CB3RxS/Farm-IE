public class TestPlot {
    public static void main(String[] args) {
        Plot p = new Plot();
        System.out.println("Empty? " + p.isEmpty());          // true
        System.out.println("Ready? " + p.isReady());          // false

        System.out.println("Planted: " + p.plant(new BasicSeed()));   // true
        System.out.println("Planted again: " + p.plant(new BasicSeed())); // false
        System.out.println("Days left: " + p.getDaysLeft());  // 2
        System.out.println("Ready? " + p.isReady());          // false

        p.nextDay();
        System.out.println("Days left: " + p.getDaysLeft());  // 1
        p.nextDay();
        System.out.println("Days left: " + p.getDaysLeft());  // 0
        System.out.println("Ready? " + p.isReady());          // true
        p.nextDay();
        System.out.println("Days left: " + p.getDaysLeft());  // still 0, never negative

        Plant plant = p.harvest();
        System.out.println("Got: " + plant.getRarity() + " " + plant.getName());  // random result
        System.out.println("Empty? " + p.isEmpty());          // true

        Plot empty = new Plot();
        empty.nextDay();                                      // must not crash
        System.out.println("Empty plot days: " + empty.getDaysLeft()); // 0
    }
}