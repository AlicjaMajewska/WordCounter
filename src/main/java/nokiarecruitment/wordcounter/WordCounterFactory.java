package nokiarecruitment.wordcounter;

public class WordCounterFactory {

    public static WordCounterFactory newInstance() {
        return new WordCounterFactory();
    }

    public WordCounter createWordCounter() {
        WordInLineCounter wordInLineCounter = new WordInLineCounter();
        WordInFileCounter wordInFileCounter = new WordInFileCounter(wordInLineCounter);
        FilesLister filesLister = new FilesLister();
        return new WordCounter(wordInFileCounter, filesLister);
    }

}
