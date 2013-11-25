package annas.misc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides a means of persistence, providing the ability to
 * push/pull a graph to a SQL (Sqlite) database.
 * 
 * 
 * @see java.sql.Connection
 * 
 * @author Sam Wilson
 * @version v1.0
 */
public class GraphDatabase {

	/**
	 * Connection to the database server
	 */
	private Connection conn;

	/**
	 * Statement to execute at the server.
	 */
	private Statement stmt;

	/**
	 * Table name
	 */
	private static final String table_name = "annas_graph";
	
	private  String filename;

	/**
	 * Statement to create a table suitable of storing graphs.
	 */
	private static final String create_tbl = "CREATE TABLE IF NOT EXISTS "
			+ GraphDatabase.table_name
			+ " (id INT PRIMARY KEY, name TEXT, sgraph LONGBLOB, num_vertices INT, num_edges INT, encoding TINYTEXT); ";

	public GraphDatabase(String filename) {
		super();
		this.filename = filename;
		try {
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			this.stmt = this.conn.createStatement();
			this.stmt.setQueryTimeout(30);
			this.setupTable();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
this.conn.toString();
	}

	public boolean insert(String name, String sgraph, int order, int size)
			throws SQLException {
		PreparedStatement pstmt = this.conn
				.prepareStatement("INSERT INTO "
						+ GraphDatabase.table_name
						+ " (name,sgraph,num_vertices,num_edges,encoding) VALUES (?,?,?,?,?); ");

		pstmt.setString(1, name);
		pstmt.setString(2, sgraph);
		pstmt.setInt(3, order);
		pstmt.setInt(4, size);
		pstmt.setString(5, "g6");
		return pstmt.execute();
	}

	public GraphIterator getAllGraphs() throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + ";"));
	}

	public GraphIterator getGraphOfOrder(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_vertices = " + n
				+ " ;"));
	}

	public GraphIterator getGraphOfOrderGreaterThan(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_vertices > " + n
				+ " ;"));

	}

	public GraphIterator getGraphOfOrderLessThan(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_vertices < " + n
				+ " ;"));

	}

	public GraphIterator getGraphOfSize(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_edges = " + n + " ;"));
	}

	public GraphIterator getGraphOfSizeGreaterThan(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_edges > " + n + " ;"));

	}

	public GraphIterator getGraphOfSizeLessThan(int n) throws SQLException {
		return new GraphIterator(this.stmt.executeQuery("SELECT * FROM "
				+ GraphDatabase.table_name + " WHERE num_edges < " + n + " ;"));

	}

	private boolean setupTable() throws SQLException {
		return this.stmt.execute(GraphDatabase.create_tbl);
	}
	
	@Override
	public String toString(){
		return "SQLite: Connected to " + this.filename;
	}

}
