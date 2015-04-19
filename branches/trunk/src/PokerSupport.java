import java.nio.file.Paths;

import javafx.scene.shape.Path;

public class PokerSupport {
    private PokerDataBase dataBase;
    private FileToRead fileReader;

    public PokerDataBase getDataBase() {
        return PokerDataBase.getInstance();
    }

    public FileToRead getFileReader() {
        return FileToRead.getInstance();
    }

    public PokerSupport() {
        dataBase = PokerDataBase.getInstance();
        fileReader = FileToRead.getInstance();
    }

    public static void main(String[] args) {
        PokerSupport poker = new PokerSupport();
        poker.getFileReader().setPathToFolder(Paths.get("C:\\Users\\adam\\Documents\\HandHistory\\Bcl333"));
        poker.getFileReader().findSummmaryFiles();

    }
}