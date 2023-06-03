package model;

public class Unit {
	private int id;
	private String label;
	private int ratio;
	private int id_Product;

	public Unit() {

	}

	public Unit(int id, String label, int ratio, int id_Product) {
		this.id = id;
		this.label = label;
		this.ratio = ratio;
		this.id_Product = id_Product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public int getId_Product() {
		return id_Product;
	}

	public void setId_Product(int id_Product) {
		this.id_Product = id_Product;
	}

}
