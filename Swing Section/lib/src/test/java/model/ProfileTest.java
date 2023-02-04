package model;

import org.junit.Assert;
import org.junit.Test;

public class ProfileTest {
	
	@Test
	public void testLoadDBConfig() {
		var props=Profile.getProperties("db");
		Assert.assertNotNull("Cannot load profile",props);
		var db=props.getProperty("database");
		Assert.assertEquals("dbName incorrect","swingdb",db);
	}
	
}
