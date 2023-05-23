package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Users;

public class UserDAO {

	public Users getUser(String userName, String passWord) {
		Connection connection = DBConnect.getConnect();
		String sql = "select TA_AUT_USER.I_ID TA_AUT_USER.T_Info_Name  from TA_AUT_USER WHERE T_USERNAME = ? and T_PASSWORD = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, passWord);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Users users = new Users();
				users.setId(rs.getInt(0));
				users.setUserName(rs.getString(3));

				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}
}
