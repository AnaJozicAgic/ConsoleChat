package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dt.User;

public class UserDaoImpl implements UserInterfaceDao {
	Connection connection = ConnectionManager.getInstance().getConnection();

	@Override
	public void addUser(User user) throws SQLException {
		// Should add user
		// create an SELECT SQL query
		String query = "INSERT INTO users( username, password, id) VALUES (?, ?, ?)";

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			// fill in the placeholders/parameters
			statement.setString(1, user.getUssername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getId());

			// execute the query
			statement.executeUpdate();

			System.out.println("User added.");
		}

	}

	@Override
	public boolean checkExist(String username) {
		String query = "select * from users;";
		ResultSet rs = null;
		String test = "";

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// check for users

			while (rs.next()) {
				String usernameDB = rs.getString("username");
				String passwordDB = rs.getString("password");
				int id = rs.getInt("id");
				if (username.equals(usernameDB)) {
					test = usernameDB;

				}

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		if (test.equals(username)) {
			return true;
		} else {
			return false;

		}
	}

	@Override
	public boolean validate(String username, String password) {
		String query = "select * from users;";
		ResultSet rs = null;
		String test = "";

		try (
				// java.sql.Statement
				Statement statement = connection.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// check for users

			while (rs.next()) {
				String usernameDB = rs.getString("username");
				String passwordDB = rs.getString("password");
				int id = rs.getInt("id");
				if (username.equals(usernameDB)&&password.equals(passwordDB)) {
					test = usernameDB;

				}

			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		if (test.equals(username)) {
			return true;
		} else {
			return false;

		}

	}

}
