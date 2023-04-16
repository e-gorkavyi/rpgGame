public class StorePosition implements Cloneable {
    private final String name;
    private final int effect;
    private final int price;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public int getEffect() {
        return effect;
    }

    public int getPrice() {
        return price;
    }

    public StorePosition(String name, int effect, int price) {
        this.name = name;
        this.effect = effect;
        this.price = price;
    }
}
