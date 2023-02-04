package model;

import java.io.IOException;
import java.util.Properties;

public class Profile {
	public static Properties getProperties(String name) {
		Properties props=new Properties();
    	String env=System.getProperty("env");
    	if(env==null) env="dev";
    	String propsFile=String.format("/config/%s.%s.properties", name, env);
//    	System.out.println(propsFile);
    	try {
			props.load(Profile.class.getResourceAsStream(propsFile));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	return props;
	}
}
