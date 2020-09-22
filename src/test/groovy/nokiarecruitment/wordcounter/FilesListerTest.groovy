package nokiarecruitment.wordcounter


import org.apache.commons.io.FilenameUtils
import spock.lang.Specification

class FilesListerTest extends Specification {

    def filesLister = new FilesLister()

    def "throws exception when given directory does not exist"() {
        given:
        def directory = "src/test/resources/not-existing-files"

        when:
        filesLister.listTextFilesRecursivelyFrom(directory)

        then:
        def e = thrown(RuntimeException)
        e.message == "Given directory does not exist"
    }

    def "throws exception when given directory is a file"() {
        given:
        def directory = "src/test/resources/files/fileWith5mamaOccurrences.txt"

        when:
        filesLister.listTextFilesRecursivelyFrom(directory)

        then:
        def e = thrown(RuntimeException)
        e.message == "Given path is not a directory"
    }

    def "lists only txt files"() {
        given:

        def directory = "src/test/resources/files"

        when:
        def files = filesLister.listTextFilesRecursivelyFrom(directory)

        then:
        files.stream().allMatch({ file -> ("txt" == FilenameUtils.getExtension(file.getName())) })
    }

    def "lists all files - includes subdirectories"() {
        given:
        def directory = "src/test/resources/files"
        def numberOfTxtFilesInDirectory = 5

        when:
        def files = filesLister.listTextFilesRecursivelyFrom(directory)

        then:
        files.size() == numberOfTxtFilesInDirectory
    }


}
