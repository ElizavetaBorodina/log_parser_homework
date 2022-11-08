package logParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Parser {
    public void parse(String pathToFile, String mask) throws IOException {
        final List<String> text = readTextFromFile(pathToFile);
        final List<String> foundStrings = findStringsThatContains(text, mask);

    }

    private List<String> readTextFromFile(String pathToFile) throws IOException {
        final List<File> logFiles = new ArrayList<>();

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

        return textFromFiles(logFiles);
    }

    private List<String> textFromFiles(List<File> logFiles) throws IOException {
        final List<String> text = new ArrayList<>();
        int fileCounter = 1;
        for (File logFile : logFiles) {
            BufferedReader reader = new BufferedReader(new FileReader(logFile));
            String line = reader.readLine();

            long size = logFile.length();
            long i = 0;

            while(line != null) {
                text.add(line);
                i += line.length() + System.lineSeparator().length();
                System.out.println(i + " out of " + size + " (file " + fileCounter + " out of " + logFiles.size() + ")");
                line = reader.readLine();
            }
            fileCounter++;
        }
        return text;
    }

    private boolean checkFileExtension(File file) {
        return (file.isFile() && file.getName().endsWith(".txt") || file.getName().endsWith(".log"));
    }

    private List<String> findStringsThatContains(List<String> text, String mask) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(mask);
        for (String s : text) {
            if (pattern.matcher(s).matches()) {
                result.add(s);
            }
        }
        return result;
    }
}
