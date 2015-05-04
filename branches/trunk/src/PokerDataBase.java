import java.sql.*;

public class PokerDataBase {
	@SuppressWarnings("unused")
	public Connection connection;
	private static PokerDataBase instance;
	
	private String dbURL;
	private String userName; //not used
	private String password; //not used
	
	public PokerDataBase() {
		this.connection = null;
		PokerDataBase.instance = null;
		
		dbURL = "jdbc:sqlite:pokerDataBase.db";
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
			connection = DriverManager.getConnection(dbURL);
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		System.out.println("Database opened successfully!");
			
		return true;
	};
	public boolean disconnetFromDatabase() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
        System.out.println("Database opened successfully!");
            
        return true;
	};
	
}
