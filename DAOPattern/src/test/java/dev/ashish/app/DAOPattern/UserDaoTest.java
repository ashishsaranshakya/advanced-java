package dev.ashish.app.DAOPattern;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.*;

public class UserDaoTest {
	
	private Connection conn;
	private List<User> users;
	
	private List<User> loadUsers() throws IOException{
		 return Files
				.lines(Paths.get("D:\\Programing\\Advanced Java\\advanced-java\\DAOPattern\\src\\test\\resources\\users.txt"))
				.map(line->line.split("[^A-Za-z]"))
				.map(Arrays::asList)
				.flatMap(list->list.stream())
				.map(word->new User(word))
				.collect(Collectors.toList());
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		users=loadUsers();
		var props=Profile.getProperties("db");
		var db=Database.instance();
		db.connect(props);
		conn=db.getConnection();
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		Database.instance().close();
	}

	@Test
	public void testSave() throws SQLException {
		User user=new User("Jupiter");
		UserDao dao=new UserDao();
		dao.save(user);
		var stm=conn.createStatement();
		var rs=stm.executeQuery("select * from user order by id desc");
		var res=rs.next();
		Assert.assertTrue("cannot retrive user data",res);
		Assert.assertEquals("used data did not match",user.getName(), rs.getString("name"));
		stm.close();
	}
	
	@Test
	public void testSaveMultiple() throws SQLException {
		UserDao dao=new UserDao();
		for(var u:users) {
			dao.save(u);
		}
		var m=getMaxId();
		System.out.println(m);
		var l=getUsers();
		int i=0;
		for(var u:users) {
			Assert.assertEquals(u.getName(),l.get(i++).getName());
		}
	}
	
	@Test
	public void testFindAndUpdate() throws SQLException {
		var user=users.get(0);
		UserDao dao=new UserDao();
		dao.save(user);
		var ruser=dao.findById(getMaxId());
		Assert.assertTrue(ruser.isPresent());
		Assert.assertEquals(ruser.get().getName(),user.getName());
		user=ruser.get();
		user.setName("Ashish");
		dao.update(user);
		ruser=dao.findById(user.getId());
		Assert.assertEquals(ruser.get().getName(),user.getName());
	}
	
	private int getMaxId() throws SQLException {
		var stm=conn.createStatement();
		var rs=stm.executeQuery("select max(id) as id from user");
		var res=rs.next();
		var id=rs.getInt("id");
		stm.close();
		return id;
	}
	
	private List<User> getUsers() throws SQLException{
		var stm=conn.createStatement();
		
		var rs=stm.executeQuery("select * from user");
		List<User> list=new ArrayList<User>();
		while(rs.next()) {
			int id=rs.getInt("id");
			String n=rs.getString("name");
			list.add(new User(id,n));
		}
		
		stm.close();
		return list;
	}
}



