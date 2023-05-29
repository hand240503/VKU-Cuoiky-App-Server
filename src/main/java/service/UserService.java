package service;

import dao.UserDAO;
import model.User;

public class UserService {

	private UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}

	public User getUsers(String userName, String passWord) {
		return userDAO.getUser(userName, passWord);
	}

}
