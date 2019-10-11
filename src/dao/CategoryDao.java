package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Categorys;

import until.Page;

public interface CategoryDao {
	// 增
	public int insertCategorys(Connection conn, Categorys categorys) throws SQLException;

	// 查
	public ResultSet selectCategorysRecord(Connection conn, String name) throws SQLException;

	// 查
	public ResultSet selectCategorysRecord(Connection conn, int id) throws SQLException;

	// 查
	public int getTotalRecord(Connection conn) throws SQLException;

	// 查
	public ResultSet selectCategorysByPage(Connection conn, Page<Categorys> page) throws SQLException;

	// 查
	public ResultSet selectCategorysRecord(Connection conn, String cgname, int id) throws SQLException;

	// 改
	public int updateCategorys(Connection conn, Categorys categorys) throws SQLException;

	// 删
	public int deleteCategorysById(Connection conn, int id) throws SQLException;
}
