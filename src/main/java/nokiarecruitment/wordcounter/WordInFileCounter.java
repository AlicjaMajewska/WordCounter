package nokiarecruitment.wordcounter;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;


@RequiredArgsConstructor
class WordInFileCounter {

    private final WordInLineCounter wordInLineCounter;

    WordCountInFile countWordOccurrenceIn(File file, String searchedWord) {
        try {
            long wordCount = countWordOccurrence(file, searchedWord);
            return WordCountInFile.of(file.getName(), wordCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long countWordOccurrence(File file, String word) throws IOException {
        LineIterator iterator = FileUtils.lineIterator(file, "UTF-8");
        long sum = 0;
        try {
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                sum += wordInLineCounter.countWordInLine(line, word);
            }
            return sum;
        } finally {
            LineIterator.closeQuietly(iterator);
        }
    }
}
