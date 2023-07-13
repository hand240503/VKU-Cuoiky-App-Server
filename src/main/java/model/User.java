package model;

public class User {
	private int id;
	private String userName;
	private int mode;
	public User() {

	}

	public User(int id, String userName) {
		this.id = id;
		this.userName = userName;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	

}