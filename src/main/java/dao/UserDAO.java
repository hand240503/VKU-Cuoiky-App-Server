package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	public User getUser(String userName, String passWord) {
		Connection connection = DBConnect.getConnect();
		String sql = "select TA_AUT_USER.I_ID , TA_AUT_USER.T_Info_Name  from TA_AUT_USER WHERE T_USERNAME = ? and T_PASSWORD = ?";
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, userName);
			statement.setString(2, passWord);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				User users = new User();
				users.setId(rs.getInt(1));
				users.setUserName(rs.getString(2));

				return users;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}
}
