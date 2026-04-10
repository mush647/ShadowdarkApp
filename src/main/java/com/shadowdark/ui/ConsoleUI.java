package main.java.com.shadowdark.ui;

import java.util.Scanner;   // импорт сканера для чтения ввода с клавиатуры

public class ConsoleUI {
    private Scanner scanner;   // для чтения ввода
    private MessageBundle msg; // для вывода локализованных строк

    public ConsoleUI(MessageBundle msg) { // это наш конструктор, тут мы создаём сканер и сохраняем msg, с пользователем мы общаемся через него
        this.scanner = new Scanner(System.in);
        this.msg = msg;
    }

    public MessageBundle msg() {
        return msg;
    } // Геттер для доступа к msg из других классов (например, если понадобится получить строку напрямую). ни разу не использовали, не уверен, сработает ли

    public void print(String key, Object... args) {
        System.out.print(msg.get(key, args));
    }  // Печатает локализованную строку по ключу без перевода строки. Добавленные аргументы можно вставить в строку через {0}, {1}, {2} итд

    public void println(String key, Object... args) {
        System.out.println(msg.get(key, args));
    } // Печатает локализованную строку по ключу с переводом строки. Добавленные аргументы можно вставить в строку через {0}, {1}, {2} итд

    public void println() {
        System.out.println();
    } // перегрузка метода с пустой строкой, на случай если нужно просто перевести строку

    // Основной метод для чтения int с локализованным приглашением
    public int readInt(String promptKey, int min, int max) {   // хм, надо разобраться почему мы чистим буфер только в одном случае
        while (true) {
            print(promptKey); // выводим приглашение к вводу тут
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                if (val >= min && val <= max) return val;
                else println("invalid_choice");
            } else {
                scanner.next();
                println("invalid_choice");
                scanner.nextLine();
            }
        }
    }

    // Упрощённый вызов с заранее заданным ключом по умолчанию
    public int readInt(int min, int max) {
        return readInt("enter_choice", min, max);
    } // вообще readInt скорей всего не самое удачно название, если мы собираемся использовать класс Консоли для чего-то кроме чтения генератора персонажа.
    public String readLine() {
        return scanner.nextLine();
    } // читает всю строку ввода до нажатия Enter. может, использовать для ввода потом где-то


    public void waitForEnter() {  // метод для ожидания ввода Enter, чтобы текст не выводился стеной, иногда срабатывает дважды,
                                  // но не могу найти где остаётся перенос строки
        println("press_enter_to_continue");
        scanner.nextLine(); //убираем остаточный \n
        scanner.nextLine(); // ждём реального нажатия Enter
    }
}