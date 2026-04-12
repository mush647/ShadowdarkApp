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
            System.out.println("   " + (i+1) + " - " + ui.msg().get("ancestry_" + ancestries[i].getAncestryName().toLowerCase() + "_name"));
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
        System.out.println("\n=== " + ui.msg().get("ancestry_" + a.getAncestryName().toLowerCase() + "_name") + " ===");
        System.out.println(ui.msg().get("ancestry_" + a.getAncestryName().toLowerCase() + "_desc"));
        System.out.println(ui.msg().get("ancestry_languages") + String.join(", ", a.getLanguages()));
        if (a.isStout()) ui.println("ancestry_stout", a.getBonusHp());
        if (a.hasKeenSenses()) ui.println("ancestry_keenSenses");
        if (a.isMighty()) ui.println("ancestry_mighty");
        if (a.hasFarsight()) ui.println("ancestry_farsight");
        if (a.isStealthy()) ui.println("ancestry_stealthy");
        if (a.isAmbitious()) ui.println("ancestry_ambitious");
        ui.waitForEnter();// ... остальные особенности
        //ui.readLine(); подумаю, надо ли это здесь
    }

    private Ancestry pickRandom() {
        Ancestry[] all = Ancestry.values();
        Ancestry random = all[new Random().nextInt(all.length)];
        ui.println("ancestry_chosen_randomly", ui.msg().get("ancestry_" + random.getAncestryName().toLowerCase() + "_name"));
        return random;
    }

    private Ancestry getAncestryByNumber(int num) {
        return Ancestry.values()[num - 1];
    }
}
