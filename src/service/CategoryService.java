package service;

import java.sql.SQLException;

import entity.Categorys;
import until.Page;

public interface CategoryService {
	// ��
	public void addCategorys(Categorys categorys);

	// ��
	public Categorys findCategorysRecord(String name);

	// ��
	public Categorys findCategorysRecord(int id);

	// ��
	public Categorys findCategorysRecord(String name, int id);

	// ��
	public void changeCategorys(Categorys categorys);

	// ɾ
	public void subCategorysById(int id);

	// ��ҳ��
	public Page<Categorys> fingCategorysByPage(Page<Categorys> page);

}
