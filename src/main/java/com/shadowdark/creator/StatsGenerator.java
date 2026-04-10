package main.java.com.shadowdark.creator;

import main.java.com.shadowdark.model.StatType;
import main.java.com.shadowdark.ui.ConsoleUI;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

public class StatsGenerator {
    private static final int MIN_STAT = 3;
    private static final int MAX_STAT = 18;
    private static final int HIGH_STAT_THRESHOLD = 14;

    private ConsoleUI ui;

    // Конструктор, принимающий ConsoleUI
    public StatsGenerator(ConsoleUI ui) {
        this.ui = ui;
    }

    // Генерация ручным вводом (использует ui)
    public Map<StatType, Integer> generateManual() {
        Map<StatType, Integer> map = new EnumMap<>(StatType.class);
        ui.println("please_make_stat_input", MIN_STAT, MAX_STAT); //"no_high_stat", statsGenerator.getHighStatThreshold()
        for (StatType type : StatType.values()) {
            System.out.print(type.getRussianName() + ": ");
            int value = ui.readInt(MIN_STAT, MAX_STAT);
            map.put(type, value);
        }
        return map;
    }

    // Случайная генерация
    public Map<StatType, Integer> generateRandom() {
        Map<StatType, Integer> map = new EnumMap<>(StatType.class);
        Random rand = new Random();
        for (StatType type : StatType.values()) {
            int value = rand.nextInt(MAX_STAT - MIN_STAT + 1) + MIN_STAT;
            map.put(type, value);
        }
        ui.println("generated_stats");
        for (StatType type : StatType.values()) {
            System.out.println(type.getRussianName() + ": " + map.get(type));
        }
        return map;
    }

    // Проверка наличия высокой характеристики
    public boolean hasHighStat(Map<StatType, Integer> stats) {
        for (int val : stats.values()) {
            if (val >= HIGH_STAT_THRESHOLD) return true;
        }
        return false;
    }

    // Геттер для доступа к порогу из других классов
    public int getHighStatThreshold() {
        return HIGH_STAT_THRESHOLD;
    }
}