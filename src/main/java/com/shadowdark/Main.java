package main.java.com.shadowdark;

import main.java.com.shadowdark.creator.CharacterBuilder;
import main.java.com.shadowdark.ui.ConsoleUI;
import main.java.com.shadowdark.ui.MessageBundle;

public class Main {
    public static void main(String[] args) {

        MessageBundle msg = new MessageBundle("ru");
        ConsoleUI ui = new ConsoleUI(msg);
        ui.println("welcome");
        CharacterBuilder builder = new CharacterBuilder(ui);
        builder.build();
    }
}
