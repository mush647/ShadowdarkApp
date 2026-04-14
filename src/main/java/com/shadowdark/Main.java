package main.java.com.shadowdark;  // значит принадлежит основному пакету данных

import main.java.com.shadowdark.creator.CharacterBuilder; // main.java. тут везде может и лишнее, но я пока не уверен
import main.java.com.shadowdark.ui.ConsoleUI;  // консоль, возможно я натыкал там лишних методов
import main.java.com.shadowdark.ui.MessageBundle; // тут живёт вся наша локализация, если я надумаю её переключать
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String lang = determineLanguage(args);
        MessageBundle msg = new MessageBundle(lang); // передаём в конструктор тэг русской локали, создаём объект MessageBundle под
                                                                 // названием msg, именно его мы будем использовать для вызова методов.
        ConsoleUI ui = new ConsoleUI(msg);  // передаём в конструктор нашей консоли объект МессаджБандл, создаём объект ConsoleUI,
                                            // через него будут идти все методы вызова консоли. Он отвечает за ввод и вывод в консоль.
        ui.println("welcome");         // используем метод с поиском по ключу и выводом текста с переводом строки, прописанный в ConsoleUI.
        CharacterBuilder builder = new CharacterBuilder(ui); // создаём объект генератора персонажа, мы передаём ему ui не просто так, ведь
                                       // генератору надо взаимодействовать с пользователем, он будет делать это через ui.print ui.read итд.
                                       // таким образом мы отделяем логику создания от привязки к конкретному способу ввода-вывода
        builder.build();                    // запускаем процесс создания персонажа.
        //    Метод build() возвращает готовый объект CharSheet (или null, если пользователь вышел).
        //    Здесь мы его покуда никуда не сохраняем, но в будущем можно сохранить в файл или продолжить работу.
        // CharSheet character = builder.build(); // например можно будет сделать так
        //if (character != null) { место для кода сохранения персонажа итд }
    }
    private static String determineLanguage(String[] args) {
        // Если передан аргумент и он корректен
        if (args.length > 0 && (args[0].equals("en") || args[0].equals("ru"))) {
            return args[0];
        }
        // Иначе запрашиваем у пользователя
        return askLanguage();
    }

    private static String askLanguage() {
        System.out.println("Select language / Выберите язык:");
        System.out.println("1 - English");
        System.out.println("2 - Русский");
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1) return "en";
                if (choice == 2) return "ru";
            } else {
                scanner.next(); // сброс неверного ввода
            }
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }
    }
}
