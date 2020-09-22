package nokiarecruitment.wordcounter;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

class WordInLineCounter {

    long countWordInLine(String line, String searchedWord) {
        String[] splitLine = StringUtils.split(line);
        return Arrays.stream(splitLine).filter(searchedWord::equals).count();
    }
}
