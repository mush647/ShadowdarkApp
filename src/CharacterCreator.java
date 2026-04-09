import java.util.Scanner;
import java.util.Random;

public class CharacterCreator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать в создание персонажа ShadowDark.");

        int[] stats = null;
        int choice = 0;

        // Основной цикл: пока не получим приемлемого персонажа или пользователь не решит продолжить со слабым
        while (true) {
            // ----- Выбор режима -----
            while (true) {
                System.out.println("Выберите режим: 1 - ввести вручную, 2 - случайная генерация, 3 - выход");
                if (console.hasNextInt()) {
                    choice = console.nextInt();
                    if (choice == 1 || choice == 2) {
                        break;
                    } else if (choice == 3) {
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
            System.out.println("\nНи одна характеристика не достигла 14 или выше. Персонаж будет очень слаб.");
            System.out.println("1 - попробовать снова (вернуться к выбору режима)");
            System.out.println("2 - продолжить с этими значениями (слабый персонаж)");
            int subChoice;
            if (console.hasNextInt()) {
                subChoice = console.nextInt();
                if (subChoice == 1) {
                    // Переход на новую итерацию внешнего цикла: снова выбор режима
                    continue;
                } else if (subChoice == 2) {
                    // Принимаем слабого персонажа
                    break;
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

        // ----- Присваиваем переменным значения из массива (для удобства) -----
        int s = stats[0];
        int d = stats[1];
        int c = stats[2];
        int i = stats[3];
        int w = stats[4];
        int ch = stats[5];
        CharSheet character = new CharSheet(s,d,c,i,w,ch);
        // ----- Финальный вывод -----
        System.out.println("\nПерсонаж успешно создан!");
        System.out.println("Сила: " + character.getStrength() + ", модификатор СИЛ: " + character.getSTR());
        System.out.println("Ловкость: " + character.getDexterity() + ", модификатор ЛОВ: " + character.getDEX());
        System.out.println("Телосложение: " + character.getConstitution() + ", модификатор ТЕЛ: " + character.getCON());
        System.out.println("Интеллект: " + character.getIntellect() + ", модификатор ИНТ: " + character.getINT());
        System.out.println("Мудрость: " + character.getWisdom() + ", модификатор МДР: " + character.getWIS());
        System.out.println("Харизма: " + character.getCharisma() + ", модификатор ХАР: " + character.getCHA());
    }

    // Метод для безопасного ввода одной характеристики
    private static int readStat(Scanner console, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                value = console.nextInt();
                if (value >= 3 && value <= 18) {
                    return value;
                } else {
                    System.out.println("Ошибка: число должно быть от 3 до 18.");
                }
            } else {
                String wrong = console.next();
                System.out.println("Ошибка: введите целое число от 3 до 18, а не '" + wrong + "'.");
            }
        }
    }

    // Проверяет, есть ли в массиве характеристик значение >= 14
    private static boolean hasHighStat(int[] stats) {
        for (int stat : stats) {
            if (stat >= 14) {
                return true;
            }
        }
        return false;
    }

    // Получает массив характеристик в зависимости от режима
    private static int[] getStats(Scanner console, int mode) {
        int[] stats = new int[6];
        if (mode == 1) { // ручной ввод
            System.out.println("Пожалуйста, введите значения 6 основных характеристик (от 3 до 18).");
            stats[0] = readStat(console, "Сила: ");
            stats[1] = readStat(console, "Ловкость: ");
            stats[2] = readStat(console, "Телосложение: ");
            stats[3] = readStat(console, "Интеллект: ");
            stats[4] = readStat(console, "Мудрость: ");
            stats[5] = readStat(console, "Харизма: ");
        } else if (mode == 2) { // случайная генерация
            Random rand = new Random();
            for (int i = 0; i < 6; i++) {
                stats[i] = rand.nextInt(16) + 3; // число от 3 до 18
            }
            System.out.println("Сгенерированные характеристики:");
            System.out.println("Сила: " + stats[0]);
            System.out.println("Ловкость: " + stats[1]);
            System.out.println("Телосложение: " + stats[2]);
            System.out.println("Интеллект: " + stats[3]);
            System.out.println("Мудрость: " + stats[4]);
            System.out.println("Харизма: " + stats[5]);
        }
        return stats;
    }
}