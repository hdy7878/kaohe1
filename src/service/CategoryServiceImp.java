package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDaoImp;
import entity.Categorys;
import entity.Providers;
import until.DBHelper;
import until.Page;

public class CategoryServiceImp implements CategoryService {
    private CategoryDaoImp cd;
    public CategoryServiceImp() {
    	cd=new CategoryDaoImp();
    }
	@Override
	public void addCategorys(Categorys categorys) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			int i=cd.insertCategorys(conn, categorys);
		    if(i==1) {
		    	System.out.println("���ӳɹ�");
			}else {
				System.out.println("����ʧ��");
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}   
	}
	@Override
	public Categorys findCategorysRecord(int id) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			ResultSet set=cd.selectCategorysRecord(conn,id);
			if(set.next()) {
				int categoryID=set.getInt("categoryID");
				String category_name=set.getString("category_name");
				String category_desc=set.getString("category_desc");
				return new Categorys(categoryID,category_name,category_desc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cd.close();
		}
		return null;
	}
	@Override
	public Categorys findCategorysRecord(String name) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			ResultSet set=cd.selectCategorysRecord(conn,name);
			if(set.next()) {
				int categoryID=set.getInt("categoryID");
				String category_name=set.getString("category_name");
				String category_desc=set.getString("category_desc");
				return new Categorys(categoryID,category_name,category_desc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cd.close();
		}
		return null;
	}
	@Override
	public void changeCategorys(Categorys categorys) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			int i=cd.updateCategorys(conn, categorys);
			if(i==1) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cd.close();
		}
		
	}
	@Override
	public void subCategorysById(int id) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			conn.setAutoCommit(false);
			int i=cd.deleteCategorysById(conn, id);
			conn.commit();
			conn.setAutoCommit(true);
			if(i==1) {
				System.out.println("ɾ��Ŀ¼�ɹ�");
			}else {
				System.out.println("ɾ��ʧ��");
			}
		}catch(SQLException e) {
			try {
			conn.rollback();
			conn.setAutoCommit(true);
				System.out.println("���������Ŀ¼��ɾ��ʧ�ܣ����ݻع�");
		}catch(Exception e1) {
			System.out.println("�����쳣");
		}finally {
			cd.close();
		}
	}
	}
	@Override
	public Page<Categorys> fingCategorysByPage(Page<Categorys> page) {
		List<Categorys> list=new ArrayList<Categorys>();
		Connection conn=null;
		try{
			conn=DBHelper.getConnection();
			ResultSet set=cd.selectCategorysByPage(conn, page);
			int totalRecord = cd.getTotalRecord(conn);
			while(set.next()) {
				int categoryID=set.getInt("categoryID");
				String category_name=set.getString("category_name");
				String category_desc=set.getString("category_desc");
				list.add(new Categorys(categoryID,category_name,category_desc));
			}
			page.setList(list);
			page.setTotalRecord(totalRecord);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			cd.close();
		}
		return page;
	}
	@Override
	public Categorys findCategorysRecord(String name, int id) {
		Connection conn=null;
		try {
			conn=DBHelper.getConnection();
			ResultSet set=cd.selectCategorysRecord(conn,name,id);
			if(set.next()) {
				int categoryID=set.getInt("categoryID");
				String category_name=set.getString("category_name");
				String category_desc=set.getString("category_desc");
				return new Categorys(categoryID,category_name,category_desc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			cd.close();
		}
		return null;
	}
	
}
	
