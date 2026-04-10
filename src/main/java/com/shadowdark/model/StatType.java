package main.java.com.shadowdark.model;
public enum StatType {
    STRENGTH("Сила"),
    DEXTERITY("Ловкость"),
    CONSTITUTION("Телосложение"),
    INTELLECT("Интеллект"),
    WISDOM("Мудрость"),
    CHARISMA("Харизма");

    private final String russianName;

    StatType(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}