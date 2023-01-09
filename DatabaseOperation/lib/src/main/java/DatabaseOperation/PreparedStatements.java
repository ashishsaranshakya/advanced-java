package DatabaseOperation;

import java.sql.DriverManager;
import java.sql.SQLException;

public class PreparedStatements {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		 
		String dbUrl="jdbc:sqlite:people.db";
		var con=DriverManager.getConnection(dbUrl);
		var st=con.createStatement();
		con.setAutoCommit(false);
		String sql="create table if not exists user (id integer primary key,name text not null)";
		st.execute(sql);
		
		int ids[]= {11,16};
		String names[]= {"Nancy","Hema"};
		
		sql="insert into user (id,name) values (?,?)";
		var insertSt=con.prepareStatement(sql);
		for(int i=0;i<ids.length;i++) {
			insertSt.setInt(1,ids[i]);
			insertSt.setString(2,names[i]);
			insertSt.executeUpdate();
		}
		
		
		var rs=st.executeQuery("select * from user");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+" "+rs.getString("name"));
		}
		
		st.close();
		con.close();
		System.out.println(con);
	}

}
