package Annotaions;

@Entity(tableName="User")
public class User {
	@Field(columnName="name",isKey=false)
	private String name;
	@Field(isKey=true)
	private long id;
	@Field
	public String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}
	public User(String name, long id) {
		super();
		this.name = name;
		this.id = id;
	}
}
