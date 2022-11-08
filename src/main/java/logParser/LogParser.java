package logParser;

import java.io.IOException;

public final class LogParser {
    // использование:
    // первый параметр - путь
    // второй параметр - регулярное выражение с типами файлов
    // третий параметр - маска для поиска строк
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse(args[0], args[1], args[2]);
    }
}
