package main.java.com.shadowdark.ui;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private MessageBundle msg;

    public ConsoleUI(MessageBundle msg) {
        this.scanner = new Scanner(System.in);
        this.msg = msg;
    }

    public MessageBundle msg() {
        return msg;
    }

    public void print(String key, Object... args) {
        System.out.print(msg.get(key, args));
    }

    public void println(String key, Object... args) {
        System.out.println(msg.get(key, args));
    }

    public void println() {
        System.out.println();
    }

    // Основной метод для чтения int с локализованным приглашением
    public int readInt(String promptKey, int min, int max) {
        while (true) {
            print(promptKey);
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

    // Упрощённый вызов с ключом по умолчанию
    public int readInt(int min, int max) {
        return readInt("enter_choice", min, max);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    private void clearBuffer() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    public void waitForEnter() {
        println("press_enter_to_continue");
        clearBuffer();
        scanner.nextLine();
    }
}