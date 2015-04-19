import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Pattern;

public class FileToRead {
    private static FileToRead instance;
    private Path pathToFolder;
    private List listOfSummaryFiles;

    private FileToRead() {
        FileToRead.instance = null;
        listOfSummaryFiles = new LinkedList<String>();
        pathToFolder = null;
    }

    public static FileToRead getInstance() {
        if (null == FileToRead.instance) {
            instance = new FileToRead();
        }
        return instance;
    }

    public Path getPathToFolder() {
        return pathToFolder;
    }

    public void setPathToFolder(Path path) {
        this.pathToFolder = path;
    }

    public boolean findSummmaryFiles() {
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(pathToFolder, "* - Summary.txt")) {
            for (Path entry : entries) {
                listOfSummaryFiles.add(entry);
                /**
                 * TODO:
                 * Ogólnie dobrze by by³o wszystkie pliki wpisywac do DB.
                 * Do tabeli z turniejami trzeba doda zmienna string, ktora odnosic sie bedzie
                 * do nazwy pliku.
                 */
            }
        } catch (IOException e) {
            System.err.println("Error!\nDirectory is not exist!");
            return false;
        }
        
        try {
            addFileContentToDatabase((Path) listOfSummaryFiles.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private String readFromFile(Path pathToFile) {
        try (FileChannel channel = FileChannel.open(pathToFile)) {
            int lengthOfByteInFile = (int) channel.size();
            MappedByteBuffer fileContentInByte = channel.map(FileChannel.MapMode.READ_ONLY, 0, lengthOfByteInFile);
            return Charset.defaultCharset().decode(fileContentInByte).toString();
        } catch (IOException e) {
            System.err.println("Read from file failed!");
        } 
        return null;
    }
    
    private String deleteUselessChar(String source) {
        String destiny = source.replace("\0", "");
        return destiny.substring(2);
    }
    
    /**
     * to jest zjebane, ale spoko Pattern trzeba ogarnac
     * i powinno zabanglac
     * 
     * @param pathToFile
     * @throws IOException
     */
//    private void filterTournamentId(String source) {
//        Pattern pattern = new Pattern();
//        pattern.matches("( \\d+)", source);
//        System.out.println(source);
//    }
//    
    public void addFileContentToDatabase(Path pathToFile)  throws IOException {
        String fileContent = readFromFile(pathToFile);
        fileContent = deleteUselessChar(fileContent);
        filterTournamentId(fileContent);
     }
};
