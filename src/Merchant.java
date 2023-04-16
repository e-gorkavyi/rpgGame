import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Merchant implements Seller {
    private final List<StorePosition> staff = new ArrayList<>();

    public void addStaffPosition (String name, int effect, int price) {
        staff.add(new StorePosition(name, effect, price));
    }

    @Override
    public Optional<StorePosition> sell(String positionName) {
        return staff.stream()
                .filter(st -> st.getName().equals(positionName))
                .findFirst();
    }

    public HashMap<String, Integer> showStaff() {
        HashMap<String, Integer> result = new HashMap<>();
        for (StorePosition position : staff) {
            int count = result.containsKey(position.getName()) ? result.get(position.getName()) + 1 : 1;
            result.put(position.getName(), count);
        }
        return result;
    }

    public Optional<StorePosition> trading() throws IOException, CloneNotSupportedException {
        System.out.println("Торговец: '- Добрый день, путник!" +
                "\nЧего желаешь? Назови товар и убедись что у тебя достаточно монет.'");
        HashMap<String, Integer> staff = showStaff();
        int index = 1;
        for (String name : staff.keySet()) {
            System.out.println(index + ". '" + name + "'" + " силою в " + this.staff.stream()
                    .filter(st -> st.getName().equals(name))
                    .findFirst()
                    .get().getEffect()
                    + " осталось "
                    + staff.get(name)
                    + " штук по цене "
                    + this.staff.stream()
                    .filter(st -> st.getName().equals(name))
                    .findFirst()
                    .get().getPrice());
            index++;
        }
        System.out.println(index + ".... - Покинуть лавку.");

        Optional<StorePosition> result = Optional.empty();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int command = Integer.parseInt(reader.readLine());

            if (command == index)
                return result;

            index = 1;
            for (String name : staff.keySet()) {
                if (index == command) {
                    return sell(name);
                }
                index++;
            }

        } catch (NumberFormatException e) {
            System.out.println("'- Выбирай внимательнее!'");
        }
        return result;
    }
}
