package model;

public class Price {

	private int id;
	private int id_unit;
	private double value;
	private int type;

	public Price() {

	}

	public Price(int id, int id_unit, double value, int type) {
		this.id = id;
		this.id_unit = id_unit;
		this.value = value;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_unit() {
		return id_unit;
	}

	public void setId_unit(int id_unit) {
		this.id_unit = id_unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
