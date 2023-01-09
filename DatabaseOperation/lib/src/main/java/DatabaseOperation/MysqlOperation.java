package DatabaseOperation;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlOperation {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 
		String dbUrl="jdbc:mysql://localhost:3306/people";
		var con=DriverManager.getConnection(dbUrl,"root","6210");
		var st=con.createStatement();
		con.setAutoCommit(false);
		String sql="";
		
		int ids[]= {11,16};
		String names[]= {"Nancy","Hema"};
		
		sql="insert into user (id,name) values (?,?)";
		var insertSt=con.prepareStatement(sql);
		for(int i=0;i<ids.length;i++) {
			insertSt.setInt(1,ids[i]);
			insertSt.setString(2,names[i]);
			//insertSt.executeUpdate();
		}
		con.commit();
		
		
		var rs=st.executeQuery("select * from user");
		while(rs.next()) {
			System.out.println(rs.getInt("id")+" "+rs.getString("name"));
		}
		
		st.close();
		con.close();
		System.out.println(con);

	}

}
