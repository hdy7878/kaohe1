package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Products;
import until.DBHelper;
import until.Page;

public interface ProductService {
	// ��������Ϣ
	public List<Products> findAllProducts();

	// ��ҳ����Ϣ
	public Page<Products> findProductByPage(Page<Products> page);

	// �õ����м�¼
	public int getTotalRecord();

	// ��
	public Products findProductsRecord(String name);

	// ��
	public Products findProductsRecord(String name, int id);

	// ��
	public Products findProductsByproviderId(int id);

	// ��
	public void chagneProducts(Products products);

	// ��
	public void addProducts(Products products);

	// ɾ
	public void subProductsById(int id);

	public Products findProductsByproductId(int id);

	public Products findProductsBycategoryId(int id);
}
