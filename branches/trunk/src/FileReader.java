import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileReader {
	private static FileReader instance;
	private Path pathToFolder;
	private List listOfSummaryFiles;
	
	private FileReader() {
		FileReader.instance = null;
		listOfSummaryFiles = new LinkedList<String>();
		pathToFolder = null;
	}
	
	public static FileReader getInstance() {
		if (null == FileReader.instance) {
			instance = new FileReader();
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
			} 
		} catch (IOException e)  {
			System.err.println("Error!\nDirectory not exist!");
			return false;
		}
		return true;
	}
	
	public boolean addFileToDataBase(Path pathToFile) {
		
		return true;
	}
};
