package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

public interface AdminService {
	// ��
	public void addAdmin(Admin admin);

	// ɾ
	public Admin subAdminById();

	// ��
	public Admin changeAdmin();

	// ��
	public Admin findAdminByLogin(Admin admin);

	// ��
	public Admin findAdminByPage();

	// ��
	public Admin findAdminRecord(String name);

}
