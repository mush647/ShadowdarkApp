package main.java.com.shadowdark.creator;

import main.java.com.shadowdark.model.Ancestry;
import main.java.com.shadowdark.ui.ConsoleUI;

import java.util.Random;

public class AncestrySelector {
    private ConsoleUI ui;

    public AncestrySelector(ConsoleUI ui) {
        this.ui = ui;
    }

    public Ancestry select() {
        while (true) {
            showMenu();
            int choice = ui.readInt(0, Ancestry.values().length + 2); // +2 для пунктов "узнать больше" и "случайно"
            if (choice == 0) {
                return null; // выход
            } else if (choice == Ancestry.values().length + 1) {
                showAncestryDetails();
                continue;
            } else if (choice == Ancestry.values().length + 2) {
                return pickRandom();
            } else {
                return getAncestryByNumber(choice);
            }
        }
    }

    private void showMenu() {
        ui.println("select_ancestry");
        Ancestry[] ancestries = Ancestry.values();
        for (int i = 0; i < ancestries.length; i++) {
            System.out.println("   " + (i+1) + " - " + ancestries[i].getRussianName());
        }
        System.out.println("   " + (ancestries.length+1) + " - " + ui.msg().get("ancestry_info"));
        System.out.println("   " + (ancestries.length+2) + " - " + ui.msg().get("random_ancestry"));
        System.out.println("   0 - " + ui.msg().get("exit"));
    }

    private void showAncestryDetails() {
        ui.println("select_ancestry_for_info",Ancestry.values().length);
        int num = ui.readInt(0, Ancestry.values().length);
        if (num == 0) return;
        Ancestry a = getAncestryByNumber(num);
        System.out.println("\n=== " + a.getRussianName() + " ===");
        System.out.println(a.getShortDescription());
        System.out.println(ui.msg().get("ancestry_languages") + String.join(", ", a.getLanguages()));
        if (a.getBonusHp() > 0) ui.println("ancestry_hp_bonus", a.getBonusHp());
        if (a.hasAdvantageOnHp()) ui.println("ancestry_hp_advantage");
        if (a.cannotBeSurprised()) ui.println("ancestry_cannot_surprised");
        if (a.hasMeleeBonus()) ui.println("ancestry_melee_bonus");
        if (a.hasRangedOrMagicBonus()) ui.println("ancestry_ranged_or_magic_bonus");
        if (a.canTurnInvisible()) ui.println("ancestry_can_turn_invisible");
        if (a.hasBonusTalent()) ui.println("ancestry_bonus_talent");
        ui.waitForEnter();// ... остальные особенности
        //ui.readLine(); подумаю, надо ли это здесь
    }

    private Ancestry pickRandom() {
        Ancestry[] all = Ancestry.values();
        Ancestry random = all[new Random().nextInt(all.length)];
        ui.println("ancestry_chosen_randomly", random.getRussianName());
        return random;
    }

    private Ancestry getAncestryByNumber(int num) {
        return Ancestry.values()[num - 1];
    }
}
