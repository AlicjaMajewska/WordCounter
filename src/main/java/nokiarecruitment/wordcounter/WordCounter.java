package nokiarecruitment.wordcounter;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class WordCounter {

    private final WordInFileCounter wordInFileCounter;
    private final FilesLister filesLister;

    public void countWordInDirectoryOf(String path, String searchedWord) {
        if (searchedWord.isBlank()) {
            throw new IllegalArgumentException("Searched word cannot be blank");
        }
        Collection<File> files = filesLister.listTextFilesRecursivelyFrom(path);
        List<WordCountInFile> wordCountsInFiles = files.stream()
                .map(it -> wordInFileCounter.countWordOccurrenceIn(it, searchedWord))
                .collect(Collectors.toList());

        WordCountSummarizer wordCountSummarizer = new WordCountSummarizer(wordCountsInFiles, searchedWord);
        String summary = wordCountSummarizer.createSummary();
        System.out.println(summary);
    }
}
