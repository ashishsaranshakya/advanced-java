/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Annotaions;

public class Library {
    public static void main(String args[]) {
    	var user=new User("Hash",12l);
    	var rep=new Repo<User>();
    	rep.save(user);
    }
}
