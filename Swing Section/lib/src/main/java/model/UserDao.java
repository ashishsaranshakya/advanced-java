package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User>{

	@Override
	public void save(User t){
		var con=Database.instance().getConnection();
		try {
			var stm=con.prepareStatement("insert into user (name,password) values (?,?)");
			stm.setString(1, t.getName());
			stm.setString(2, t.getPassword());
			stm.executeUpdate();
			stm.close();
		} 
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public Optional<User> findById(int id) {
		var con=Database.instance().getConnection();
		try {
			var stm=con.prepareStatement("select * from user where id=?");
			stm.setInt(1, id);
			var rs=stm.executeQuery();
			
			if(rs.next()) {
				var name=rs.getString("name");
				var password=rs.getString("name");
				return Optional.of(new User(id,name,password));
			}
			
			stm.close();
		} 
		catch (SQLException e) {
			throw new DaoException(e);
		}
		return Optional.empty();
	}

	@Override
	public void update(User t) {
		var con=Database.instance().getConnection();
		try {
			var stm=con.prepareStatement("update user set name=? where id=?");
			stm.setString(1, t.getName());
			stm.setInt(2, t.getId());
			if(stm.executeUpdate()!=0)
				System.out.println("Updated");
			stm.close();
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(User t) {
		var con=Database.instance().getConnection();
		try {
			var stm=con.prepareStatement("delete from user where id=?");
			stm.setInt(1, t.getId());
			if(stm.executeUpdate()!=0)
				System.out.println("Deleted");
			stm.close();
		} 
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<User> getAll() {
		List<User> users=new ArrayList<>();
		var con=Database.instance().getConnection();
		try {
			var stm=con.createStatement();
			var rs=stm.executeQuery("select * from user");
			while(rs.next()) {
				var id=rs.getInt("id");
				var name=rs.getString("name");
				var password=rs.getString("name");
				users.add(new User(id,name,password));
			}
			
			stm.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return users;
	}

}
