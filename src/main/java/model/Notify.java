package model;

public class Notify {
	private String notify;
	private int id;
	private Object data;
	private String content;

	public Notify() {

	}

	public Notify(String notify, Object data, String content) {
		this.notify = notify;
		this.data = data;
		this.content = content;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
