import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class World {
    private final List<String> navMenu = new ArrayList<>();
    private final Battle battle = new Battle();
    private final BufferedReader commandBuffer = new BufferedReader(new InputStreamReader(System.in));
    private final Merchant merchant = new Merchant();
    private final Character player;

    public World(String playerName) {
        this.player = new Hero(playerName, 100, 20, 20, 0, 20);
        navMenu.add("К торговцу");
        navMenu.add("В тёмный лес");
        navMenu.add("На выход");
        merchant.addStaffPosition("Драконье зелье", 50, 30);
        merchant.addStaffPosition("Колдовской отвар", 25, 20);
        merchant.addStaffPosition("Травяной чай", 10, 15);
    }

    public void start() throws IOException, CloneNotSupportedException {
        System.out.printf("%s, добро пожаловать в сказочный мир! Вперёд, к приключениям!\n\n", player.getName());
        showNavMenu(navMenu);

        while (true) {
            String playerCommand;
            playerCommand = commandBuffer.readLine();

            switch (playerCommand) {
                case "1" -> {
                    Optional<StorePosition> purchased = merchant.trading();
                    purchased.ifPresentOrElse(position -> {
                                player.takeHealth(position.getEffect());
                                System.out.printf("Вы купили '%s', ваше здоровье окрепло до %d.\n",
                                        position.getName(),
                                        player.getHealth());
                                        showStatus();
                                        showNavMenu(navMenu);
                            },
                            () -> {
                                System.out.println("Вы ничего не купили.");
                                showNavMenu(navMenu);
                            });
                }
                case "2" -> fightBegin();
                case "3" -> System.exit(1);
            }
        }
    }

    private void fightBegin() {
        battle.fight(player, newMonster(), new BattleResult() {
            @Override
            public void win() {
                showStatus();
                showNavMenu(navMenu);
            }

            @Override
            public void lost() {
                System.exit(1);
            }
        });
    }

    private void showStatus() {
        System.out.println("Положение таково:");
        System.out.printf("Опыт %d, золото %d, здоровье %d\n",
                player.getExperience(),
                player.getGold(),
                player.getHealth());
    }

    private Character newMonster() {
        if (Math.round(Math.random()) == 1)
            return new Goblin("Гоблин", 50, 10, 10, 100, 20);
        else
            return new Skeleton("Скелет", 25, 20, 20, 100, 10);
    }

    public void showNavMenu(List<String> navMenu) {
        System.out.println("Куда желаете проследовать?");
        for (int i = 1; i <= navMenu.size(); i++) {
            System.out.println(i + ". " + navMenu.get(i - 1));
        }
    }
}

interface BattleResult {
    void win();
    void lost();
}
