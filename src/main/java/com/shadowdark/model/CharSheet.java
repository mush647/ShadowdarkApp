package main.java.com.shadowdark.model;

import java.util.EnumMap;
import java.util.Map;

public class CharSheet {
    // Константы для характеристик
    private static final int MIN_STAT = 3;
    private static final int MAX_STAT = 18;

    // Поля (теперь private)
    private String name;
    private Ancestry ancestry;
    private String charClass;
    private int level;
    private int totalXp;
    private String charTitle;
    private String alignment;
    private String background;
    private String deity;

    // Хранилище характеристик: теперь используется EnumMap
    private final Map<StatType, Integer> stats = new EnumMap<>(StatType.class);

    // Конструктор, принимающий значения характеристик (в виде Map)
    public CharSheet(Map<StatType, Integer> initialStats) {
        setStats(initialStats);
        // Остальные поля останутся null или будут установлены сеттерами позже
    }

    // Альтернативный конструктор для удобства (если передаёшь 6 int)
    public CharSheet(int strength, int dexterity, int constitution,
                     int intellect, int wisdom, int charisma) {
        Map<StatType, Integer> map = new EnumMap<>(StatType.class);
        map.put(StatType.STRENGTH, strength);
        map.put(StatType.DEXTERITY, dexterity);
        map.put(StatType.CONSTITUTION, constitution);
        map.put(StatType.INTELLECT, intellect);
        map.put(StatType.WISDOM, wisdom);
        map.put(StatType.CHARISMA, charisma);
        setStats(map);
    }

    // Приватный метод для установки характеристик с валидацией
    private void setStats(Map<StatType, Integer> statsMap) {
        for (StatType type : StatType.values()) {
            Integer val = statsMap.get(type);
            if (val == null) {
                throw new IllegalArgumentException("Missing stat: " + type);
            }
            if (val < MIN_STAT || val > MAX_STAT) {
                throw new IllegalArgumentException(
                        type.getRussianName() + " должно быть от " + MIN_STAT + " до " + MAX_STAT);
            }
            stats.put(type, val);
        }
    }

    // Геттер для отдельной характеристики
    public int getStat(StatType type) {
        return stats.get(type);
    }

    // Геттеры для каждой характеристики (для удобства, чтобы не писать везде StatType)
    public int getStrength() { return getStat(StatType.STRENGTH); }
    public int getDexterity() { return getStat(StatType.DEXTERITY); }
    public int getConstitution() { return getStat(StatType.CONSTITUTION); }
    public int getIntellect() { return getStat(StatType.INTELLECT); }
    public int getWisdom() { return getStat(StatType.WISDOM); }
    public int getCharisma() { return getStat(StatType.CHARISMA); }

    // Модификаторы
    private int calculateModifier(int value) {
        return (int) Math.floor((value - 10) / 2.0);
    }
    // универсальный геттер для модификатора
    public int getModifier(StatType type) {
        return calculateModifier(getStat(type));
    }

    public int getSTR() { return calculateModifier(getStrength()); }
    public int getDEX() { return calculateModifier(getDexterity()); }
    public int getCON() { return calculateModifier(getConstitution()); }
    public int getINT() { return calculateModifier(getIntellect()); }
    public int getWIS() { return calculateModifier(getWisdom()); }
    public int getCHA() { return calculateModifier(getCharisma()); }

    // Сеттеры для характеристик (с валидацией)
    public void setStrength(int strength) { setStat(StatType.STRENGTH, strength); }
    public void setDexterity(int dexterity) { setStat(StatType.DEXTERITY, dexterity); }
    public void setConstitution(int constitution) { setStat(StatType.CONSTITUTION, constitution); }
    public void setIntellect(int intellect) { setStat(StatType.INTELLECT, intellect); }
    public void setWisdom(int wisdom) { setStat(StatType.WISDOM, wisdom); }
    public void setCharisma(int charisma) { setStat(StatType.CHARISMA, charisma); }

    private void setStat(StatType type, int value) {
        if (value < MIN_STAT || value > MAX_STAT) {
            throw new IllegalArgumentException(
                    type.getRussianName() + " должно быть от " + MIN_STAT + " до " + MAX_STAT);
        }
        stats.put(type, value);
    }

    // Геттеры и сеттеры для остальных полей (строковые)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Ancestry getAncestry() {return ancestry;}
    public void setAncestry(Ancestry ancestry) { this.ancestry = ancestry; }

    public String getCharClass() { return charClass; }
    public void setCharClass(String charClass) { this.charClass = charClass; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getTotalXp() { return totalXp; }
    public void setTotalXp(int totalXp) { this.totalXp = totalXp; }

    public String getCharTitle() { return charTitle; }
    public void setCharTitle(String charTitle) { this.charTitle = charTitle; }

    public String getAlignment() { return alignment; }
    public void setAlignment(String alignment) { this.alignment = alignment; }

    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }

    public String getDeity() { return deity; }
    public void setDeity(String deity) { this.deity = deity; }


    // Языки, которые знает персонаж (с учётом расы)
    public String[] getLanguages() {
        return ancestry != null ? ancestry.getLanguages() : new String[]{"Всеобщий"};
    }

    // Дополнительные ОЗ от расы (например, +2 для дварфа)
    public int getRacialBonusHp() {
        return ancestry != null ? ancestry.getBonusHp() : 0;
    }

    // Есть ли преимущество при броске ОЗ за уровень
    public boolean hasAdvantageOnHitDice() {
        return ancestry != null && ancestry.hasAdvantageOnHp();
    }

    // +1 к дальним атакам или магии
    public boolean hasRangedOrMagicBonus() {
        return ancestry != null && ancestry.hasRangedOrMagicBonus();
    }

    // Нельзя застать врасплох
    public boolean cannotBeSurprised() {
        return ancestry != null && ancestry.cannotBeSurprised();
    }

    // +1 к рукопашным атакам и урону
    public boolean hasMeleeBonus() {
        return ancestry != null && ancestry.hasMeleeBonus();
    }
}