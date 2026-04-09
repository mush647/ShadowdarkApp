import java.util.Scanner;
import java.util.Random;

public class CharacterCreator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Добро пожаловать в создание персонажа ShadowDark.");
        int choice;
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

        int s, d, c, i, w, ch;

        if (choice == 1) {
            System.out.println("Пожалуйста, введите значения 6 основных характеристик (от 3 до 18).");
            s = readStat(console, "Сила: ");
            System.out.println("Введённая сила: " + s);
            d = readStat(console, "Ловкость: ");
            System.out.println("Введённая ловкость: " + d);
            c = readStat(console, "Телосложение: ");
            System.out.println("Введённое телосложение: " + c);
            i = readStat(console, "Интеллект: ");
            System.out.println("Введённый интеллект: " + i);
            w = readStat(console, "Мудрость: ");
            System.out.println("Введённая мудрость: " + w);
            ch = readStat(console, "Харизма: ");
            System.out.println("Введённая харизма: " + ch);
        } else if (choice == 2) {
            Random rand = new Random();
            s = rand.nextInt(16) + 3;
            System.out.println("Сгенерированная сила: " + s);
            d = rand.nextInt(16) + 3;
            System.out.println("Сгенерированная ловкость: " + d);
            c = rand.nextInt(16) + 3;
            System.out.println("Сгенерированное телосложение: " + c);
            i = rand.nextInt(16) + 3;
            System.out.println("Сгенерированный интеллект: " + i);
            w = rand.nextInt(16) + 3;
            System.out.println("Сгенерированная мудрость: " + w);
            ch = rand.nextInt(16) + 3;
            System.out.println("Сгенерированная харизма: " + ch);
        } else {
            System.out.println("Ошибка. Завершение программы.");
            return;
        }
    }

    // Метод для безопасного ввода одной характеристики
    private static int readStat(Scanner console, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (console.hasNextInt()) {
                value = console.nextInt();
                if (value >= 3 && value <= 18) {
                    break;
                } else {
                    System.out.println("Ошибка: число должно быть от 3 до 18.");
                }
            } else {
                String wrong = console.next();
                System.out.println("Ошибка: введите целое число, а не '" + wrong + "'.");
            }
        }
        return value;
    }
}