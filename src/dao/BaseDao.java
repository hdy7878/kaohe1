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

	// ͨ�õ���ɾ�ķ���
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

	// ͨ�ò�ѯ����
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
//�ر�����
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
