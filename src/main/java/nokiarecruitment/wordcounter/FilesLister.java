package nokiarecruitment.wordcounter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;

class FilesLister {

    Collection<File> listTextFilesRecursivelyFrom(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            throw new RuntimeException("Given directory does not exist");
        }
        if (!directory.isDirectory()) {
            throw new RuntimeException("Given path is not a directory");
        }
        return FileUtils.listFiles(directory, new String[]{"txt"}, true);
    }

}
