package main.java.com.shadowdark.ui;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageBundle {
    private ResourceBundle bundle;

    public MessageBundle(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag); // "ru", "en"
        bundle = ResourceBundle.getBundle("com.shadowdark.messages.messages", locale);
    }

    public String get(String key, Object... args) {
        String pattern = bundle.getString(key);
        if (args.length > 0) {
            return MessageFormat.format(pattern, args);
        }
        return pattern;
    }
}
