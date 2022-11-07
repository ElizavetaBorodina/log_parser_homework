package logParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public final class Parser {
    public void parse(String pathToFile) throws IOException {
        final List<String> textResult = readTextFromFile(pathToFile);
    }

    private List<String> readTextFromFile(String pathToFile) throws IOException {
        final List<File> logFiles = new ArrayList<>();
        final List<String> text = new ArrayList<>();

        final Path path = Paths.get(pathToFile);
        final File fileFromPath = path.toFile();
        if (fileFromPath.isDirectory()) {
            for (File file : fileFromPath.listFiles()) {
                if (checkFileExtension(file)) {
                    logFiles.add(file);
                }
            }
        } else if (fileFromPath.isFile()) {
            if (checkFileExtension(fileFromPath)) {
                logFiles.add(fileFromPath);
            }
        } else {
            System.out.println("nope");
            System.exit(-1);
        }

        for (File logFile : logFiles) {
            text.addAll(Files.readAllLines(logFile.toPath()));
        }

        return text;
    }

    private boolean checkFileExtension(File file) {
        return (file.isFile() && file.getName().endsWith(".txt") || file.getName().endsWith(".log"));
    }
}
