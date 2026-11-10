# Farm-IE
 
A console farming game written in Java. The player plants seeds of differing rarities and relies on luck to grow rare plants, which they can either sell to get rich or keep to build a collection.
 
Made for CIT 240 (Object-Oriented Programming) as a group final project. Everything runs in memory (no database).
 
## How the game works
 
1. Start with 100 gold and 4 plots.
2. Buy seeds at the shop (Basic, Silver, Golden). Better seeds cost more and have better odds of rare plants.
3. Plant a seed in an empty plot.
4. Sleep to move to the next day. Every planted plot gets closer to ready.
5. Harvest a ready plot to get a random plant: Flower, Vegetable or Fruit, in Common, Rare or Legendary rarity.
6. Sell the plant for gold, or keep it for your Collection Book (goal: all 9 variants).
7. Spend gold on more plots (up to 12) and on items that speed up growth.
## Seeds
 
| Seed | Cost | Days to grow | Rare chance | Legendary chance |
|---|---|---|---|---|
| Basic | 25g | 2 | 10% | 1% |
| Silver | 50g | 3 | 30% | 8% |
| Golden | 150g | 4 | 40% | 25% |
 
Plant value = base value (Flower 20, Vegetable 30, Fruit 40) x rarity multiplier (Common x1, Rare x5, Legendary x20). Seeds sell back for half price.
 
## OOP design
 
| Concept | Where it's used |
|---|---|
| Encapsulation | Private fields with getters and small methods in every class |
| Inheritance | `Plant` -> `Flower`, `Vegetable`, `Fruit`; `Seed` -> `BasicSeed`, `SilverSeed`, `GoldenSeed` |
| Polymorphism | `Sellable` interface implemented by `Plant` and `Seed` |
| Abstraction | Abstract classes `Plant` and `Seed`, interface `Sellable` |
| Custom exceptions | `InsufficientFundsException`, `OutOfSeedsException` |
| Collections | `HashMap` (seed counts), `ArrayList` (plots, kept plants), `HashSet` (collection book) |
 
## Project status
 
- [x] Rarity, Sellable
- [x] Plant, Flower, Vegetable, Fruit
- [x] Seed, BasicSeed, SilverSeed, GoldenSeed
- [x] Player, InsufficientFundsException
- [ ] Plot (written, in testing)
- [ ] Inventory, OutOfSeedsException
- [ ] Items (Fertilizer, Super Fertilizer, Lucky Charm)
- [ ] Farm
- [ ] Shop
- [ ] CollectionBook
- [ ] Main menu connected to the real classes
- [ ] Input validation
- [ ] Testing table and UML diagram
## How to run
 
Requires Java 21 (OpenJDK) or newer. Put all `.java` files in one folder, then:
 
```
javac *.java
java Main
```
 
(The game menu is not fully connected yet. See project status.)
 
## Team
 
- Rico Alimonsurin
- Michael Juan Dy Quiangco
- Rembrandt Monteclaro
- Ralph Justin Roxas
- Franklin Richard Zerrudo Jr.