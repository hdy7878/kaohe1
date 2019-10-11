package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import until.DBHelper;

import java.sql.PreparedStatement;

public class BaseDao {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet set;

	// 通用的增删改方法
	public int updateDate(Connection conn, String sql, Object... objects) throws SQLException {
		this.conn = conn;
		ps = conn.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
		}

		return ps.executeUpdate();
	}

	// 通用查询方法
	public ResultSet selectDate(Connection conn, String sql, Object... objects) throws SQLException {
		this.conn = DBHelper.getConnection();
		ps = conn.prepareStatement(sql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
		}
		return ps.executeQuery();
	}
//关闭连接
	public void close() {

		try {
			if (set != null) {
				set.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
