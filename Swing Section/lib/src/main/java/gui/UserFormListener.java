package gui;

@FunctionalInterface
public interface UserFormListener {
	void formSubmitted(String user,String password);
}
