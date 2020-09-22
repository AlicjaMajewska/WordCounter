package nokiarecruitment.wordcounter

import spock.lang.Specification

class WordCounterTest extends Specification {

    def wordInFileCounter = Mock(WordInFileCounter)
    def filesLister = Mock(FilesLister)

    def wordCounter = new WordCounter(wordInFileCounter, filesLister)

    def "path does not exist"() {
        given:
        def nonExistingPath = "non-existing"
        def searchedWord = "mama"
        filesLister.listTextFilesRecursivelyFrom(nonExistingPath) >> {
            throw new IllegalArgumentException("Given directory does not exist")
        }

        when:
        wordCounter.countWordInDirectoryOf(nonExistingPath, searchedWord)

        then:
        def e = thrown(IllegalArgumentException)
        e.message.contains("Given directory does not exist")
    }

    def "searched word is empty"() {
        given:
        def nonExistingPath = "files"
        def searchedWord = ""

        when:
        wordCounter.countWordInDirectoryOf(nonExistingPath, searchedWord)

        then:
        def e = thrown(IllegalArgumentException)
        e.message.contains("Searched word cannot be blank")
    }

    def "word in files is counted and summarized"() {
        given:
        def path = "files"
        def searchedWord = "mama"
        def file = new File("file")
        def bo = new ByteArrayOutputStream()
        System.setOut(new PrintStream(bo))

        when:
        wordCounter.countWordInDirectoryOf(path, searchedWord)

        then:
        1 * filesLister.listTextFilesRecursivelyFrom(path) >> List.of(file)
        1 * wordInFileCounter.countWordOccurrenceIn(file, searchedWord) >> WordCountInFile.of(file.getName(), 2)
        String allWrittenLines = getAllLinesWrittenToSystemOut(bo)
        allWrittenLines.contains("1) Sumaryczna liczba wystąpień wyrazu \"mama\" w plikach .txt wynosi: 2 (2)")
        allWrittenLines.contains("2) Liczba plików, które maja ten wyraz: 1")
        allWrittenLines.contains("3) Liczba wystąpień w pliku")
        allWrittenLines.contains("plik file - 2 wyrazy mama")
    }

    private static String getAllLinesWrittenToSystemOut(ByteArrayOutputStream bo) {
        bo.flush()
        def allWrittenLines = new String(bo.toByteArray())
        allWrittenLines
    }
}
