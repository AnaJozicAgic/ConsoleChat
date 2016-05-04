package dao;

import java.sql.SQLException;

import dt.User;

public interface UserInterfaceDao {

	public void addUser(User user)throws SQLException;
	public boolean checkExist (String username);
	public boolean validate (String username, String password);
	
	
}
