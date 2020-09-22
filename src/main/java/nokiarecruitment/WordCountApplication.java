package nokiarecruitment;

import nokiarecruitment.wordcounter.WordCounter;
import nokiarecruitment.wordcounter.WordCounterFactory;

import java.io.File;

public class WordCountApplication {

    public static void main(String[] args) {
        if (args.length != 2 || !isPathDirectory(args[0])) {
            System.out.println("Provide absolute path to directory and the searched word");
            System.exit(1);
        }
        runApplication(args[0], args[1]);
    }

    private static void runApplication(String path, String searchedWord) {
        WordCounter wordCounter = WordCounterFactory.newInstance().createWordCounter();
        wordCounter.countWordInDirectoryOf(path, searchedWord);
    }

    private static boolean isPathDirectory(String path) {
        File file = new File(path);
        return file.isDirectory();
    }
}
