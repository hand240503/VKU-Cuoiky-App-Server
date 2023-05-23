package controller;

import dao.UserDAO;
import model.Users;

public class UsersController {

	private UserDAO userDAO;

	public UsersController() {
		userDAO = new UserDAO();
	}

	public Users getUsers(String userName, String passWord) {
		return userDAO.getUser(userName, passWord);
	}

}
