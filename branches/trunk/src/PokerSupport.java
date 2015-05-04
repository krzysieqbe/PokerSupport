import java.nio.file.Paths;

//import javafx.scene.shape.Path;



public class PokerSupport {
	private PokerDataBase db;
	private FileReader fileReader;
	
	public PokerDataBase getDataBase() {
		return db.getInstance();
	}
	
	public FileReader getFileReader() {
		return FileReader.getInstance();
	}
	
	public PokerSupport() {
		db = PokerDataBase.getInstance();
		fileReader = FileReader.getInstance();
	}


	public static void main(String[] args) {
		PokerSupport poker = new PokerSupport();
		poker.getFileReader().setPathToFolder(Paths.get("C:\\Users\\adam\\Documents\\HandHistory\\Bcl333"));
		poker.getFileReader().findSummmaryFiles();
		
		poker.db = new PokerDataBase();
		
		poker.db.connectToDatabase();
		
	}
}