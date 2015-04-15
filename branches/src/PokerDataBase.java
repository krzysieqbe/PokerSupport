import java.sql.*;

public class PokerDataBase {
	@SuppressWarnings("unused")
	private Connection connection;
	private static PokerDataBase instance;
	
	private PokerDataBase() {
		this.connection = null;
		PokerDataBase.instance = null;
	}
	
	public static PokerDataBase getInstance() {
		if (null == instance) {
			instance = new PokerDataBase();
		}
		
		return instance;
	}
	
	public boolean connectToDatabase() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:pokerDataBase.db");
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		System.out.println("Database opened successfully!");
		
		return true;
	};
}
