package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entity.Products;
import until.DBHelper;
import until.Page;

public interface ProductService {
	// 查所有信息
	public List<Products> findAllProducts();

	// 分页查信息
	public Page<Products> findProductByPage(Page<Products> page);

	// 得到所有记录
	public int getTotalRecord();

	// 查
	public Products findProductsRecord(String name);

	// 查
	public Products findProductsRecord(String name, int id);

	// 查
	public Products findProductsByproviderId(int id);

	// 改
	public void chagneProducts(Products products);

	// 增
	public void addProducts(Products products);

	// 删
	public void subProductsById(int id);

	public Products findProductsByproductId(int id);

	public Products findProductsBycategoryId(int id);
}
