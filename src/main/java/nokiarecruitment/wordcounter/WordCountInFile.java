package nokiarecruitment.wordcounter;

import lombok.Value;

@Value
class WordCountInFile {

    private final String filename;
    private final long wordCount;

    static WordCountInFile of(String fileName, long wordCount) {
        return new WordCountInFile(fileName, wordCount);
    }

    boolean isCountGreaterThan0() {
        return wordCount > 0;
    }
}
