package entity;

import java.util.List;

public class Categorys {
	private int categoryID;
	private String category_name;
	private String category_desc;

	public Categorys(int categoryID) {
		super();
		this.categoryID = categoryID;
	}

	public Categorys(String category_name, String category_desc) {
		super();
		this.category_name = category_name;
		this.category_desc = category_desc;
	}

	public Categorys() {
		super();
	}

	public Categorys(String category_name) {
		super();
		this.category_name = category_name;
	}

	public Categorys(int categoryID, String category_name) {
		super();
		this.categoryID = categoryID;
		this.category_name = category_name;
	}

	public Categorys(int categoryID, String category_name, String category_desc) {
		super();
		this.categoryID = categoryID;
		this.category_name = category_name;
		this.category_desc = category_desc;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_desc() {
		return category_desc;
	}

	public void setCategory_desc(String category_desc) {
		this.category_desc = category_desc;
	}

	@Override
	public String toString() {
		return "Catgorys [categoryID=" + categoryID + ", category_name=" + category_name + ", category_desc="
				+ category_desc + "]";
	}
}
