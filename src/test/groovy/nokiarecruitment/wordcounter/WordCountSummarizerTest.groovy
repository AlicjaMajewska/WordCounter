package nokiarecruitment.wordcounter

import spock.lang.Specification

class WordCountSummarizerTest extends Specification {

    def "summary for no files to search found"() {
        given:
        def wordCountsInFiles = []
        def searchedWord = "mama"
        def wordCountSummarizer = new WordCountSummarizer(wordCountsInFiles, searchedWord)

        when:
        def summary = wordCountSummarizer.createSummary()

        then:
        summary == "1) Sumaryczna liczba wystąpień wyrazu \"mama\" w plikach .txt wynosi: 0\n" +
                "2) Liczba plików, które maja ten wyraz: 0\n" +
                "3) Liczba wystąpień w pliku - brak\n"
    }


    def "summary for 8 files with 22 words found"() {
        given:
        def wordCountsInFiles = List.of(
                WordCountInFile.of("fileWith5mamaOccurrences.txt", 2),
                WordCountInFile.of("b.txt", 3),
                WordCountInFile.of("c.txt", 7),
                WordCountInFile.of("d.txt", 0),
                WordCountInFile.of("f.txt", 1),
                WordCountInFile.of("y.txt", 2),
                WordCountInFile.of("k.txt", 3),
                WordCountInFile.of("r.txt", 4),
        )
        def searchedWord = "mama"
        def wordCountSummarizer = new WordCountSummarizer(wordCountsInFiles, searchedWord)

        when:
        def summary = wordCountSummarizer.createSummary()

        then:
        summary == "1) Sumaryczna liczba wystąpień wyrazu \"mama\" w plikach .txt wynosi: 22 (2+3+7+1+2+3+4)\n" +
                "2) Liczba plików, które maja ten wyraz: 7\n" +
                "3) Liczba wystąpień w pliku\n" +
                "\n" +
                "plik fileWith5mamaOccurrences.txt - 2 wyrazy mama\n" +
                "plik b.txt - 3 wyrazy mama\n" +
                "plik c.txt - 7 wyrazów mama\n" +
                "plik d.txt - 0 wyrazów mama\n" +
                "plik f.txt - 1 wyraz mama\n" +
                "plik y.txt - 2 wyrazy mama\n" +
                "plik k.txt - 3 wyrazy mama\n" +
                "plik r.txt - 4 wyrazy mama"
    }

}
