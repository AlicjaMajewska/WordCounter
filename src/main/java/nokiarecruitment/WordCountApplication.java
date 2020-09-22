package nokiarecruitment;

import nokiarecruitment.wordcounter.WordCounter;
import nokiarecruitment.wordcounter.WordCounterFactory;

public class WordCountApplication {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Provide absolute path to directory and the searched word");
        }
        runApplication(args[0], args[1]);
    }

    private static void runApplication(String path, String searchedWord) {
        WordCounter wordCounter = WordCounterFactory.newInstance().createWordCounter();
        wordCounter.countWordInDirectoryOf(path, searchedWord);
    }
}
