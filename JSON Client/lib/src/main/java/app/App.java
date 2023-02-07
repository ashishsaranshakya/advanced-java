package app;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) {
		var url="http://localhost:8080/Web_Servlet_JSP_Gradle/user";
		try {
			var json=IOUtils.toString(new URL(url),Charset.forName("UTF-8"));
			System.out.println(json);
			Gson gson=new Gson();
			var users=gson.fromJson(json,User[].class);
			for(var user:users) {
				System.out.println(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
