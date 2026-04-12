package main.java.com.shadowdark.creator;

import main.java.com.shadowdark.model.CharSheet;
import main.java.com.shadowdark.model.Ancestry;
import main.java.com.shadowdark.model.StatType;
import main.java.com.shadowdark.ui.ConsoleUI;

import java.util.Map;

public class CharacterBuilder {
    private ConsoleUI ui;
    private StatsGenerator statsGenerator;
    private AncestrySelector ancestrySelector;

    public CharacterBuilder(ConsoleUI ui) {
        this.ui = ui;
        this.statsGenerator = new StatsGenerator(ui);
        this.ancestrySelector = new AncestrySelector(ui);
    }

    public CharSheet build() {
        Map<StatType, Integer> stats = null;
        while (true) {
            // выбор режима
            ui.println("choose_mode");
            System.out.println("   " + ui.msg().get("manual_mode"));//ui.println("manual_mode");
            System.out.println("   " + ui.msg().get("random_mode"));//ui.println("random_mode");
            System.out.println("   " + ui.msg().get("exit"));//ui.println("exit");
            int mode = ui.readInt(0, 2);
            if (mode == 0) {
                ui.println("exit_program");
                return null;
            }

            if (mode == 1) stats = statsGenerator.generateManual();
            else stats = statsGenerator.generateRandom();
            ui.waitForEnter();

            if (statsGenerator.hasHighStat(stats)) break;

            ui.println("no_high_stat", statsGenerator.getHighStatThreshold());
            ui.println("try_again");
            ui.println("continue_weak");
            int sub = ui.readInt(1, 2);
            if (sub == 1) continue;
            else break;
        }

        CharSheet character = new CharSheet(stats);
        ui.println("stats_generated");
        for (StatType type : StatType.values()) {
            int val = character.getStat(type);
            int mod = character.getModifier(type);
            System.out.println(type.getStatName() + ": " + val + ", модификатор: " + mod);
        }
        ui.waitForEnter();

        Ancestry ancestry = ancestrySelector.select();
        if (ancestry == null) return null;
        character.setAncestry(ancestry);
        //character.recalculateMaxHp(); // нужно реализовать этот метод в CharSheet
        ui.println("ancestry_chosen", ui.msg().get("ancestry_" + ancestry.getAncestryName().toLowerCase() + "_name"));

        return character;
    }
}