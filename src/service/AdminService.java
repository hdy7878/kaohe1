package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Admin;

public interface AdminService {
	// 增
	public void addAdmin(Admin admin);

	// 删
	public Admin subAdminById();

	// 改
	public Admin changeAdmin();

	// 查
	public Admin findAdminByLogin(Admin admin);

	// 查
	public Admin findAdminByPage();

	// 查
	public Admin findAdminRecord(String name);

}
