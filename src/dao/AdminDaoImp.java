package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

public class AdminDaoImp extends BaseDao implements AdminDao {

	@Override
	public int insertAdmin(Connection conn, Admin admin) throws SQLException {
		String sql = "insert into admin (admin_name,admin_password,date) value(?,?,now())";

		return super.updateDate(conn, sql, admin.getAdmin_name(), admin.getAdmin_password());
	}

	@Override
	public int deleteAdminById(Connection conn, int id) throws SQLException {

		return 0;
	}

	@Override
	public int updateAdmin(Connection conn, Admin admin) throws SQLException {

		return 0;
	}

	@Override
	public ResultSet selectAdminByLogin(Connection conn, Admin admin) throws SQLException {
		String sql = "select * from admin where admin_name=? and admin_password=?";
		return super.selectDate(conn, sql, admin.getAdmin_name(), admin.getAdmin_password());
	}

	@Override
	public ResultSet selectAdminByPage(Connection conn) throws SQLException {
		String sql = "select * from admin";
		return super.selectDate(conn, sql, null);
	}

	@Override
	public ResultSet selectAdminRecord(Connection conn, String name) throws SQLException {
		String sql = "select * from admin where admin_name=?";
		return super.selectDate(conn, sql, name);
	}

}
