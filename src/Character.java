public class Character {
    private String name;
    private int health;
    private int gold;
    private int agility;
    private int experience;
    private int strength;

    public Character(String name, int health, int gold, int agility, int experience, int strength) {
        this.name = name;
        this.health = health;
        this.gold = gold;
        this.agility = agility;
        this.experience = experience;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getAgility() {
        return agility;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return strength;
    }

    public int attack() {
        int luck = Math.random() * 100 > 70 ? 2 : 1;
        return (agility * 3) > (Math.random() * 100) ? strength * luck : 0;
    }

    public void decreaseHealth(int attack) {
        health -= attack;
    }

    public void takeGold(int gold) {
        this.gold += gold;
    }

    public void takeExperience(int exp) {
        this.experience += exp;
    }

    public void takeHealth(int effect) {
        this.health += effect;
    }
}
