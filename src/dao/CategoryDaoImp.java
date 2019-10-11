package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Categorys;
import until.Page;

public class CategoryDaoImp extends BaseDao implements CategoryDao {

	@Override
	public int insertCategorys(Connection conn, Categorys categorys) throws SQLException {
		String sql = "insert into categorys(category_name,category_desc) value(?,?)";
		return super.updateDate(conn, sql, categorys.getCategory_name(), categorys.getCategory_desc());
	}

	@Override
	public ResultSet selectCategorysRecord(Connection conn, String cgname) throws SQLException {
		String sql = "select * from categorys where category_name=?";
		return super.selectDate(conn, sql, cgname);
	}

	@Override
	public ResultSet selectCategorysRecord(Connection conn, int categoryID) throws SQLException {
		String sql = "select * from categorys where categoryID=?";
		return super.selectDate(conn, sql, categoryID);
	}

	@Override
	public int updateCategorys(Connection conn, Categorys categorys) throws SQLException {
		String sql = "update categorys set category_name=?,category_desc=? where categoryID=?";
		return super.updateDate(conn, sql, categorys.getCategory_name(), categorys.getCategory_desc(),
				categorys.getCategoryID());
	}

	@Override
	public int getTotalRecord(Connection conn) throws SQLException {
		String sql = "select count(categoryID) from categorys";
		ResultSet set = super.selectDate(conn, sql, null);
		if (set.next()) {
			return set.getInt(1);
		}
		return 0;
	}

	@Override
	public ResultSet selectCategorysByPage(Connection conn, Page<Categorys> page) throws SQLException {
		String sql = "select * from categorys limit ?,?";
		Object[] objects = { (page.getCurrentPage() - 1) * page.getPageSize(), page.getPageSize() };
		return super.selectDate(conn, sql, objects);
	}

	@Override
	public int deleteCategorysById(Connection conn, int id) throws SQLException {
		String sql = "delete from categorys where categoryID = ?";
		return super.updateDate(conn, sql, id);

	}

	@Override
	public ResultSet selectCategorysRecord(Connection conn, String cgname, int id) throws SQLException {
		String sql = "select * from categorys where category_name=?and categoryID!=?";
		return super.selectDate(conn, sql, cgname, id);
	}

}
