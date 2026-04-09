import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class CharacterCreator {
    private static final int MIN_STAT = 3;
    private static final int MAX_STAT = 18;
    private static final int HIGH_STAT_THRESHOLD = 14;
    private static final int STAT_COUNT = StatType.values().length; // 6
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Добро пожаловать в создание персонажа ShadowDark.");

        Map<StatType, Integer> stats = null;
        int choice;

        // Основной цикл: пока не получим приемлемого персонажа или пользователь не решит продолжить со слабым
        while (true) {
            // ----- Выбор режима -----
            while (true) {
                System.out.println("Выберите режим определения характеристик персонажа:");
                System.out.println("   1 - ввести вручную;");
                System.out.println("   2 - случайная генерация;");
                System.out.println("   0 - выход из создания персонажа.");
                if (console.hasNextInt()) {
                    choice = console.nextInt();
                    if (choice == 1 || choice == 2) {
                        break;
                    } else if (choice == 0) {
                        System.out.println("Выходим из программы.");
                        return;
                    } else {
                        System.out.println("Некорректный выбор. Попробуйте снова.");
                    }
                } else {
                    String wrong = console.next();
                    System.out.println("Ошибка: введите число 1, 2 или 3, а не '" + wrong + "'.");
                }
            }

            // Получаем характеристики
            stats = getStats(console, choice);

            // Проверяем, есть ли характеристика >= 14
            if (hasHighStat(stats)) {
                break; // персонаж хорош, выходим из основного цикла
            }

            // Если нет высокой характеристики
            System.out.println("\nНи одна характеристика не достигла " + HIGH_STAT_THRESHOLD + " или выше. Персонаж будет очень слаб.");
            System.out.println("   1 - попробовать заново;");
            System.out.println("   2 - продолжить с этими значениями;");
            System.out.println("   0 - выход из создания персонажа.");
            int subChoice;
            if (console.hasNextInt()) {
                subChoice = console.nextInt();
                if (subChoice == 1) {
                    // Переход на новую итерацию внешнего цикла: снова выбор режима
                    continue;
                } else if (subChoice == 2) {
                    // Принимаем слабого персонажа
                    break;
                } else if (subChoice == 0) {
                    System.out.println("Выходим из программы.");
                    return;
                } else {
                    System.out.println("Некорректный выбор. Продолжаем со слабым персонажем.");
                    break;
                }
            } else {
                String wrong = console.next();
                System.out.println("Ошибка: требовалось ввести 1 или 2, а не '" + wrong + "'. Будешь играть слабым персонажем, козёл.");
                break;
            }
        }

        CharSheet character = new CharSheet(stats);
        System.out.println("\nХарактеристики успешно присвоены!");
        for (StatType type : StatType.values()) {
            int value = character.getStat(type);
            int modifier = character.getModifier(type);
            System.out.println(type.getRussianName() + ": " + value + ", модификатор: " + modifier);
        }
        // --- Выбор расы ---
        Ancestry selectedAncestry = null;
        while (selectedAncestry == null) {
            printAncestryMenu(); // выводим меню
            int choiceAncestry = getIntInput(console, 0, 8); // свой метод для безопасного ввода
            if (choiceAncestry == 0) {
                System.out.println("Выход из создания персонажа.");
                return;
            } else if (choiceAncestry == 7) {
                // Показать описание расы
                showAncestryDetails(console);
                continue; // после показа возвращаемся в меню
            } else if (choiceAncestry == 8) {
                // Случайный выбор
                Ancestry[] all = Ancestry.values();
                selectedAncestry = all[new Random().nextInt(all.length)];
                System.out.println("Случайно выбрана раса: " + selectedAncestry.getRussianName());
            } else {
                // Выбор по номеру (1-6)
                selectedAncestry = getAncestryByNumber(choiceAncestry);
            }
        }

        character.setAncestry(selectedAncestry);
        //character.recalculateMaxHp(); // добавим позже пересчет ОЗ с учётом бонуса расы
        System.out.println("\nРаса " + selectedAncestry.getRussianName() + " выбрана!");
        // Дополнительно: если человек, запросить дополнительный язык и т.д. (пока опустим)
    }
    // Метод для безопасного ввода одной характеристики
    private static int readStat(Scanner console, String prompt) {
            int value;
            while (true) {
                System.out.print(prompt);
                if (console.hasNextInt()) {
                    value = console.nextInt();
                    if (value >= MIN_STAT && value <= MAX_STAT) {
                        return value;
                    } else {
                        System.out.println("Ошибка: число должно быть от " + MIN_STAT + " до " + MAX_STAT + ".");
                    }
                } else {
                    String wrong = console.next();
                    System.out.println("Ошибка: введите целое число от " + MIN_STAT + " до " + MAX_STAT + ", а не '" + wrong + "'.");
                }
            }
        }

    // Проверяет, есть ли в массиве характеристик значение >= 14
        private static boolean hasHighStat(Map<StatType, Integer> stats) {
            for (int value : stats.values()) {
                if (value >= HIGH_STAT_THRESHOLD) {
                    return true;
                }
            }
            return false;
        }

    // Получает массив характеристик в зависимости от режима
        private static Map<StatType, Integer> getStats(Scanner console, int mode) {
            Map<StatType, Integer> statsMap = new EnumMap<>(StatType.class);
            Random rand = new Random();

            if (mode == 1) { // ручной ввод
                System.out.println("Пожалуйста, введите значения характеристик (от " + MIN_STAT + " до " + MAX_STAT + ").");
                for (StatType type : StatType.values()) {
                    int value = readStat(console, type.getRussianName() + ": ");
                    statsMap.put(type, value);
                }
            } else if (mode == 2) { // случайная генерация
                for (StatType type : StatType.values()) {
                    int value = rand.nextInt(MAX_STAT - MIN_STAT + 1) + MIN_STAT; // от 3 до 18
                    statsMap.put(type, value);
                }
                System.out.println("Сгенерированные характеристики:");
                for (StatType type : StatType.values()) {
                    System.out.println(type.getRussianName() + ": " + statsMap.get(type));
                }
            }
            return statsMap;
        }
    private static void printAncestryMenu() {
        System.out.println("\nВыберите расу персонажа:");
        Ancestry[] races = Ancestry.values();
        for (int i = 0; i < races.length; i++) {
            System.out.println("   " + (i+1) + " - " + races[i].getRussianName());
        }
        System.out.println("   7 - Я хочу узнать о расах больше");
        System.out.println("   8 - Выбрать случайно");
        System.out.println("   0 - выход из создания персонажа");
    }

    private static int getIntInput(Scanner console, int min, int max) {
        while (true) {
            if (console.hasNextInt()) {
                int val = console.nextInt();
                if (val >= min && val <= max) return val;
                else System.out.println("Ошибка: введите число от " + min + " до " + max + ".");
            } else {
                String wrong = console.next();
                System.out.println("Ошибка: введите число, а не '" + wrong + "'.");
            }
        }
    }

    private static void showAncestryDetails(Scanner console) {
        System.out.println("Введите номер расы, чтобы узнать подробности (1-" + Ancestry.values().length + "), или 0 для возврата:");
        int num = getIntInput(console, 0, Ancestry.values().length);
        if (num == 0) return;
        Ancestry a = getAncestryByNumber(num);
        System.out.println("\n=== " + a.getRussianName() + " ===");
        System.out.println(a.getShortDescription());
        System.out.println("Языки: " + String.join(", ", a.getLanguages()));
        System.out.println("Особенности: ");
        if (a.getBonusHp() > 0) System.out.println("  +" + a.getBonusHp() + " к ОЗ");
        if (a.hasAdvantageOnHp()) System.out.println("  Преимущество на броски ОЗ при получении уровня");
        if (a.cannotBeSurprised()) System.out.println("  Невозможно застать врасплох");
        if (a.hasMeleeBonus()) System.out.println("  +1 к броскам атаки и урона в рукопашном бою");
        if (a.hasRangedOrMagicBonus()) System.out.println("  +1 к дальнобойным атакам или проверкам магии");
        if (a.canTurnInvisible()) System.out.println("  1 раз в день может стать невидимым на 3 раунда");
        if (a.hasBonusTalent()) System.out.println("   Дополнительный талант на первом уровне");
        System.out.println("Нажмите Enter, чтобы продолжить...");
        console.nextLine(); // чистим буфер
        console.nextLine(); // ждём ввод
    }

    private static Ancestry getAncestryByNumber(int number) {
        return Ancestry.values()[number - 1];
    }
}