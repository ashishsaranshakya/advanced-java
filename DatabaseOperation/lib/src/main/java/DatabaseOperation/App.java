package DatabaseOperation;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		 
		String dbUrl="jdbc:sqlite:people.db";
		var con=DriverManager.getConnection(dbUrl);
		var st=con.createStatement();
		var sql="create table if not exists user (id integer primary key,name text not null)";
		st.execute(sql);
		
		sql="insert into user (id,name) values (2,'Pranjal')";
		st.execute(sql);
		
		var rs=st.executeQuery("select * from user");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+" "+rs.getString("name"));
		}
		
		st.close();
		con.close();
		System.out.println(con);
	}

}
