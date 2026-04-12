package main.java.com.shadowdark.model;
public enum StatType {
    STRENGTH("strength"),
    DEXTERITY("dexterity"),
    CONSTITUTION("constitution"),
    INTELLECT("intelligence"),
    WISDOM("wisdom"),
    CHARISMA("charisma");

    private final String statName;

    StatType(String statName) {
        this.statName = statName;
    }

    public String getStatName() {
        return statName;
    }
}