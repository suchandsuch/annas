import java.sql.SQLException;

import annas.graph.GraphInterface;
import annas.misc.GraphDatabase;
import annas.misc.GraphIterator;


public class SQLiteHarness {

	public static void main(String args[]) throws SQLException{
		GraphDatabase gdb = new GraphDatabase("sample.db");
		
		GraphIterator gi = gdb.getGraphOfOrder(3);
		
		while(gi.hasNext()){
			GraphInterface gg = gi.next();
			System.out.println("Order: " + gg.getOrder() + " Size: " + gg.getSize());
		}
	}
}






