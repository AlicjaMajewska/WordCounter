package nokiarecruitment.wordcounter

import spock.lang.Specification

class WordInFileCounterTest extends Specification {

    def wordInLineCounter = new WordInLineCounter()
    def wordInFileCounter = new WordInFileCounter(wordInLineCounter)

    def "count word in file"() {
        given:
        def file = new File("src/test/resources/files/fileWith5mamaOccurrences.txt")
        def searchedWord = "mama"

        when:
        def wordCount = wordInFileCounter.countWordOccurrenceIn(file, searchedWord)

        then:
        wordCount.getWordCount() == 5
    }

    def "throw when given file does not exists"() {
        given:
        def file = new File("non-existing.txt")
        def searchedWord = "mama"

        when:
        wordInFileCounter.countWordOccurrenceIn(file, searchedWord)

        then:
        def e = thrown(RuntimeException)
        e.message.contains("File 'non-existing.txt' does not exist")
    }
}
