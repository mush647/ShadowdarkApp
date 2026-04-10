package main.java.com.shadowdark.model;

public enum Ancestry {
    DWARF("Дварф", new String[]{"Всеобщий", "Дварфский"}, 2, true, false, false, false, false, false,
            "Храбрый и стойкий народ, такой же крепкий, как каменные королевства, которые они высекают внутри гор."),
    ELF("Эльф", new String[]{"Всеобщий", "Эльфийский", "Сильванский"}, 0, false, true, false, false, false, false,
            "Изящный, неземной красоты народ, почитающий знания и красоту. Эльфы видят далеко и живут долго."),
    GOBLIN("Гоблин", new String[]{"Всеобщий", "Гоблинский"}, 0, false, false, true, false, false, false,
            "Зелёнокожие умные существа, которые прекрасно чувствуют себя в тёмных, тесных местах. Столь же свирепые, сколь и крошечные."),
    HALF_ORC("Полуорк", new String[]{"Всеобщий", "Орочий"}, 0, false, false, false, true, false, false,
            "Высокие воины с клыками, такие же отважные, как люди, и такие же могучие, как орки. Их внешность обманчива, а культура часто игнорируется другими."),
    HALFLING("Полурослик", new String[]{"Всеобщий"}, 0, false, false, false, false, true, false,
            "Маленькие, весёлые и с озорным нравом. Они наслаждаются простыми радостями жизни и предпочитают держаться вместе, живя в своих деревнях."),
    HUMAN("Человек", new String[]{"Всеобщий", "Один дополнительный язык"}, 0, false, false, false, false, false, true,
            "Легко приспосабливающийся, смелый, многочисленный и разносторонний народ. Быстро учатся и совершают великие дела.");

    private final String russianName;
    private final String[] languages;
    private final int bonusHp;          // дополнительное ОЗ при старте
    private final boolean advantageOnHp; // преимущество при броске ОЗ за уровень
    private final boolean rangedOrMagicBonus; // +1 к дальним атакам или магии
    private final boolean cannotBeSurprised; // невозможно застать врасплох
    private final boolean meleeBonus; // +1 к рукопашным атакам и урону
    private final boolean turnInvisible; // раз в день может стать невидимым на 3 раунда
    private final boolean bonusTalent; // дополнительный бросок таланта на первом уровне
    private final String shortDescription;  // краткое описание расы

    // Конструктор
    Ancestry(String russianName, String[] languages, int bonusHp,
             boolean advantageOnHp, boolean rangedOrMagicBonus,
             boolean cannotBeSurprised, boolean meleeBonus, boolean turnInvisible, boolean bonusTalent, String shortDescription) {
        this.russianName = russianName;
        this.languages = languages;
        this.bonusHp = bonusHp;
        this.advantageOnHp = advantageOnHp;
        this.rangedOrMagicBonus = rangedOrMagicBonus;
        this.cannotBeSurprised = cannotBeSurprised;
        this.meleeBonus = meleeBonus;
        this.turnInvisible = turnInvisible;
        this.bonusTalent = bonusTalent;
        this.shortDescription = shortDescription;
    }

    public String getRussianName() { return russianName; }
    public String[] getLanguages() { return languages; }
    public int getBonusHp() { return bonusHp; }
    public boolean hasAdvantageOnHp() { return advantageOnHp; }
    public boolean hasRangedOrMagicBonus() { return rangedOrMagicBonus; }
    public boolean cannotBeSurprised() { return cannotBeSurprised; }
    public boolean hasMeleeBonus() { return meleeBonus; }
    public boolean canTurnInvisible() { return turnInvisible;}
    public boolean hasBonusTalent() { return bonusTalent; }
    public String getShortDescription() { return shortDescription; }
}