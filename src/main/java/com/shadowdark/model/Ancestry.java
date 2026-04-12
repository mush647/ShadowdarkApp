package main.java.com.shadowdark.model;


public enum Ancestry {
    DWARF("DWARF", new String[]{"common", "dwarvish"}, 2, true, false, false, false, false, false),
    ELF("ELF", new String[]{"common", "elvish", "sylvan"}, 0, false, true, false, false, false, false),
    GOBLIN("GOBLIN", new String[]{"common", "goblin"}, 0, false, false, true, false, false, false),
    HALF_ORC("HALF_ORC", new String[]{"common", "orcish"}, 0, false, false, false, true, false, false),
    HALFLING("HALFLING", new String[]{"common"}, 0, false, false, false, false, true, false),
    HUMAN("HUMAN", new String[]{"common", "bonus_common"}, 0, false, false, false, false, false, true);

    private final String ancestryName;
    private final String[] languages;
    private final int bonusHp;          // дополнительное ОЗ при старте
    private final boolean stout; // преимущество при броске ОЗ за уровень
    private final boolean farsight; // +1 к дальним атакам или магии
    private final boolean keenSenses; // невозможно застать врасплох
    private final boolean mighty; // +1 к рукопашным атакам и урону
    private final boolean stealthy; // раз в день может стать невидимым на 3 раунда
    private final boolean ambitious; // дополнительный бросок таланта на первом уровне

    // Конструктор
    Ancestry(String ancestryName, String[] languages, int bonusHp,
             boolean stout, boolean farsight,
             boolean keenSenses, boolean mighty, boolean stealthy, boolean ambitious) {
        this.ancestryName = ancestryName;
        this.languages = languages;
        this.bonusHp = bonusHp;
        this.stout = stout;
        this.farsight = farsight;
        this.keenSenses = keenSenses;
        this.mighty = mighty;
        this.stealthy = stealthy;
        this.ambitious = ambitious;
    }

    public String getAncestryName() { return ancestryName; }
    public String[] getLanguages() { return languages; }
    public int getBonusHp() { return bonusHp; }
    public boolean isStout() { return stout; }
    public boolean hasFarsight() { return farsight; }
    public boolean hasKeenSenses() { return keenSenses; }
    public boolean isMighty() { return mighty; }
    public boolean isStealthy() { return stealthy;}
    public boolean isAmbitious() { return ambitious; }
}