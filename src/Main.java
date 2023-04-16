import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String playerName = "";
        System.out.println("Введите имя игрока:");
        try {
            playerName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        World world = new World(playerName);
        world.start();
    }
}
