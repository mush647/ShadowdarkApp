package main.java.com.shadowdark.ui;
import java.text.MessageFormat; // утилита для вставки переменных в текст, как System.out.printf("Hello %s!", "World"); но для работы с шаблонами
import java.util.Locale; // класс, который описывает языковой и региональный стандарт (русский ru, английский en)
import java.util.ResourceBundle; // механизм для загрузки текстов из файлов в зависимости от языка

public class MessageBundle {
    private ResourceBundle bundle; //поле хранит загруженные строки из файла локализации (напр. messages_ru.properties).
                                   // Приватное, доступ только внутри класса.

    public MessageBundle(String languageTag) { //Конструктор вызывается с параметром languageTag. Это строка, определяющая язык.
                                               // Например, "ru" для русского, "en" для английского.
        Locale locale = Locale.forLanguageTag(languageTag); // превращаем строку "ru", "en" в объект локали
        bundle = ResourceBundle.getBundle("com.shadowdark.messages.messages", locale); //загружает файл с переводами,
                                               // вторая messages в пути значит, что будут использоваться файлы, начинающиеся с
                                               // messages и заканчивающиеся на _ru.properties или аналогично
    }

    public String get(String key, Object... args) {    // метод принимает ключ, по которому ищется текст в файле локализации,
        // например "welcome". Object... args — это varargs (переменное количество аргументов). Можно передать ноль, один, два
        // и более дополнительных параметров. Они будут подставлены в текст вместо {0}, {1} и т.д.
        String pattern = bundle.getString(key); // Получает из bundle строку по ключу. Например, если ключ "welcome", то в файле
                                                // messages_ru.properties может быть строка welcome=Добро пожаловать!
        if (args.length > 0) {  //Если переданы доп. параметры (например, имена, минимумы и максимумы значений), то нужно их вставить в шаблон.
            return MessageFormat.format(pattern, args); //Берёт шаблон (например, "Раса {0} выбрана!"), и заменяет {0} на первый аргумент из args
        }
        return pattern; //Если аргументов нет, просто возвращает строку из bundle.
    }
}
