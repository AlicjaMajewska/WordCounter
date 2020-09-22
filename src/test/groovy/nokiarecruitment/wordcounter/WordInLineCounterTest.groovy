package nokiarecruitment.wordcounter

import spock.lang.Specification

class WordInLineCounterTest extends Specification {

    def searchedWord = "mama"
    def wordInLineCounter = new WordInLineCounter()

    def "count word mama in line"(String line, int count) {
        expect:
        wordInLineCounter.countWordInLine(line, searchedWord) == count

        where:
        line                     | count
        "mama"                   | 1
        "mamam"                  | 0
        "mama mama"              | 2
        "mama dog mama"          | 2
        "dog not-mama cat"       | 0
        "mama mama mama"         | 3
        "mama sth mama sth mama" | 3
        ""                       | 0
    }

}
