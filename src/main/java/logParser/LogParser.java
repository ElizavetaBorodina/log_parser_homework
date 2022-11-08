package logParser;

import java.io.IOException;

public final class LogParser {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parse(args[0], args[1]);

    }
}
