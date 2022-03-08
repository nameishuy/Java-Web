package Model;

public class Book {
	public String id;
	private String tensach;
	private String anh;
	private String tenTG;
	private String moTa;
	private long daBan;

	public Book() {
		super();
	}

	public Book(String id, String tensach, String anh, String tenTG, String moTa, long daBan) {
		super();
		this.id = id;
		this.tensach = tensach;
		this.anh = anh;
		this.tenTG = tenTG;
		this.moTa = moTa;
		this.daBan = daBan;
	}

	public String getMota() {
		return moTa;
	}

	public void setMota(String value) {
		this.moTa = value;
	}

	public String getID() {
		return id;
	}

	public void setID(String value) {
		this.id = value;
	}

	public String getTensach() {
		return tensach;
	}

	public void setTensach(String value) {
		this.tensach = value;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String value) {
		this.anh = value;
	}

	public String getTenTG() {
		return tenTG;
	}

	public void setTenTG(String value) {
		this.tenTG = value;
	}

	public long getDaBan() {
		return daBan;
	}

	public void setDaBan(long value) {
		this.daBan = value;
	}
}
