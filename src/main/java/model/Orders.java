package model;

public class Orders {
	private int id;
	private String date;
	private int id_user;
	private int type;

	public Orders() {

	}

	public Orders(int id, String date, int id_user, int type) {
		this.id = id;
		this.date = date;
		this.id_user = id_user;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
