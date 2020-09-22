package nokiarecruitment.wordcounter;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class WordCountSummarizer {

    private final List<WordCountInFile> wordCountsInFiles;
    private final String searchedWord;
    private final StringBuilder stringBuilder = new StringBuilder();

    String createSummary() {
        appendSummarizingLine();
        appendFilesCountLine();
        appendOccurrencesInFiles();
        return stringBuilder.toString();
    }

    private void appendSummarizingLine() {
        long occurrenceSum = wordCountsInFiles.stream().mapToLong(WordCountInFile::getWordCount).sum();
        stringBuilder.append("1) Sumaryczna liczba wystąpień wyrazu \"")
                .append(searchedWord)
                .append("\" w plikach .txt wynosi: ")
                .append(occurrenceSum);

        if (occurrenceSum > 0) {
            stringBuilder.append(getJoinedOccurrencesAsSumInBrackets());
        }
        stringBuilder.append("\n");
    }

    private String getJoinedOccurrencesAsSumInBrackets() {
        return wordCountsInFiles.stream()
                .filter(WordCountInFile::isCountGreaterThan0)
                .map(WordCountInFile::getWordCount)
                .map(String::valueOf)
                .collect(Collectors.joining("+", " (", ")"));
    }

    private void appendFilesCountLine() {
        long fileWithSearchedWordCount = wordCountsInFiles.stream().filter(WordCountInFile::isCountGreaterThan0).count();
        stringBuilder.append("2) Liczba plików, które maja ten wyraz: ")
                .append(fileWithSearchedWordCount)
                .append("\n");
    }

    private void appendOccurrencesInFiles() {
        stringBuilder.append("3) Liczba wystąpień w pliku");
        if (wordCountsInFiles.isEmpty()) {
            stringBuilder.append(" - brak");
        }
        stringBuilder.append("\n");
        wordCountsInFiles.forEach(this::appendFileOccurrencesLine);
    }

    private void appendFileOccurrencesLine(WordCountInFile wordCountInFile) {
        stringBuilder
                .append("\n")
                .append("plik ")
                .append(wordCountInFile.getFilename())
                .append(" - ")
                .append(wordCountInFile.getWordCount())
                .append(" ")
                .append(getWyrazyWordFormDependingOnCount(wordCountInFile.getWordCount()))
                .append(" ")
                .append(searchedWord);
    }

    private String getWyrazyWordFormDependingOnCount(long wordCount) {
        if (wordCount == 1) {
            return "wyraz";
        }
        if (wordCount >= 2 && wordCount <= 4) {
            return "wyrazy";
        }
        return "wyrazów";
    }

}
