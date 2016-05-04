package dt;

public class User {
private int id;
private String ussername;
private String password;

public User(String username, String pass) {
this.ussername= username;
this.password= pass;

}
public User() {
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUssername() {
	return ussername;
}
public void setUssername(String ussername) {
	this.ussername = ussername;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
