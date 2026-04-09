public class CharSheet {
    String name;
    int strength;
    int dexterity;
    int constitution;
    int intellect;
    int wisdom;
    int charisma;
    String ancestry;
    String charClass;
    int level;
    int totalXp;
    String charTitle;
    String alignment;
    String background;
    String deity;
    // constructor
    public CharSheet(int strength, int dexterity, int constitution,
                     int intellect, int wisdom, int charisma) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intellect = intellect;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    // Characteristics Getters
    public int getStrength() {return strength;}
    public int getDexterity() {return dexterity;}
    public int getConstitution() {return constitution;}
    public int getIntellect() {return intellect;}
    public int getWisdom() {return wisdom;}
    public int getCharisma() {return charisma;}
    // other Getters
    public String getName() {return name;}
    public String getAncestry() {return ancestry;}
    public String getCharClass() {return charClass;}
    public int getLevel() {return level;}
    public int getTotalXp() {return totalXp;}
    public String getCharTitle() {return charTitle;}
    public String getAlignment() {return alignment;}
    public String getBackground() {return background;}
    public String getDeity() {return deity;}

    // modifier calculator method
    private int calculateModifier(int value) {
        return (int) Math.floor((value - 10) / 2.0);
    }

    // ability modifiers getters
    public int getSTR() {
        return calculateModifier(strength);
    }
    public int getDEX() {
        return calculateModifier(dexterity);
    }
    public int getCON() {
        return calculateModifier(constitution);
    }
    public int getINT() {
        return calculateModifier(intellect);
    }
    public int getWIS() {
        return calculateModifier(wisdom);
    }
    public int getCHA() {
        return calculateModifier(charisma);
    }

    // stats Setters
    public void setStrength(int strength) {this.strength = strength;}
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public void setConstitution(int constitution) {this.constitution = constitution;}
    public void setIntellect(int intellect) {this.intellect = intellect;}
    public void setWisdom(int wisdom) {this.wisdom = wisdom;}
    public void setCharisma(int charisma) {this.charisma = charisma;}
    // string Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }
    public void setCharClass(String charClass) {
        this.charClass = charClass;
    }
    public void setCharTitle(String charTitle) {
        this.charTitle = charTitle;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public void setBackground(String background) {
        this.background = background;
    }
    public void setDeity(String deity) {
        this.deity = deity;
    }
}
