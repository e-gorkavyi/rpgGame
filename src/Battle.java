public class Battle {
    public void fight(Character hero, Character monster, BattleResult battleResult) {

        Runnable battleThread = () -> {

            System.out.printf("\nНа этот раз вам противостоит %s. Бой начался!\n", monster.getName());

            int attackCount = 1;
            while (hero.getHealth() > 0 && monster.getHealth() > 0) {
                System.out.printf("--- Удар %d\n", attackCount);
                if (attackCount++ % 2 == 0) {
                    doAttack(hero, monster);
                } else {
                    doAttack(monster, hero);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (hero.getHealth() > 0) {
                hero.takeGold(monster.getGold());
                hero.takeExperience(monster.getExperience());
                System.out.printf("Вы победили в бою! Вы получили %d золота и %d опыта\n",
                        monster.getGold(),
                        monster.getExperience());
                        battleResult.win();
            } else {
                System.out.println("Вы повержены. Мир суров.\n");
                battleResult.lost();
            }

        };

        Thread thread = new Thread(battleThread);
        thread.start();
        }

    private void doAttack(Character attacker, Character defensive) {
        int attackLevel = attacker.attack();
        if (attackLevel == 0)
            System.out.printf("%s промахнулся.%n\n", attacker.getName());
        else {
            defensive.decreaseHealth(attackLevel);
            System.out.printf("%s нанёс удар с силой %d. У %s осталось %d здоровья.\n",
                    attacker.getName(),
                    attackLevel,
                    defensive.getName(),
                    defensive.getHealth());
        }
    }
}
