package view;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entity.Admin;
import entity.Categorys;
import entity.Products;
import entity.Providers;
import service.AdminServiceImp;
import service.CategoryServiceImp;
import service.ProductServiceImp;
import service.ProviderServiceImp;
import until.Page;

public class Test {
	private AdminServiceImp as;
	private CategoryServiceImp cs;
	private ProductServiceImp ps;
	private ProviderServiceImp pvs;
	// 给一个输入方法
	public String input() {
		String string = null;
		while(true) {
		byte[] buf = new byte[50];
		try {		
			System.in.read(buf);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		string  = new String(buf).trim();
		if(string.equals("")) {
			System.out.println("字符串不能为空，请重新输入");
		}else {
			break;
		}
       }
		return string;
	}
	// 把前台交互都封装成一个方法
	public void process() {
		// 抓取异常
		try {
			// 实例化
			as = new AdminServiceImp();
			ps = new ProductServiceImp();
			cs = new CategoryServiceImp();
			pvs = new ProviderServiceImp();
			Scanner sc = new Scanner(System.in);
			// 为了实现反复操作
			while (true) {
				System.out.println("[-------欢迎使用杭州大帝国电商系统-------]" + " \n" + "请您选择操作：1：登录     2：注册     3：退出");
				String op1 = input();
				/**
				 *
				 *
				 *
				 * 登录电商系统
				 */
				if ("1".equals(op1)) {
					System.out.println("请输入您的用户名：");
					String name = input();
					System.out.println("请输入您的密码：");
					String password = input();
					Admin admin = as.findAdminByLogin(new Admin(name, password));
					// 到数据库查询没有重名
					if (admin != null) {
						System.out.println("登录成功");
						// 打印出管理员信息
						System.out.println(admin);
						while (true) {
							System.out.println("【欢迎管理员：" + name + "，使用电商系统】" + "\n"
									+ "请选择操作： 1：类别管理    2：供应商管理    3：产品管理    4：退出");
							String op2 = input();
							/**
							 * 选择类别管理
							 *
							 *
							 *
							 */
							if ("1".equals(op2)) {
								while (true) {
									System.out.println("请您选择种类操作 1：增加类别    2：删除类别    3：修改类别    4：分页查询类别     5：退出");
									String op3 = input();
									/*
									 * 选择增加类别
									 */
									if ("1".equals(op3)) {
										System.out.println("请输入你要增加的种类名");
										String cgname = input();
										System.out.println("请输入这个种类的介绍");
										String cgdesc = input();
										// --------种类根据名字有无增加----------
										Categorys categorys = cs.findCategorysRecord(cgname);
										if (categorys == null) {
											cs.addCategorys(new Categorys(cgname, cgdesc));
										} else {
											System.out.println("该种类已存在，请重新选择");
										}
										System.out.println("是否继续添加种类？0 ：退出  1：继续  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * 选择删除种类
										 **/
									} else if ("2".equals(op3)) {
										int cid = 0;
										while (true) {
											System.out.println("请输入要删除的种类id");
											try {
												String cidStr = input();
												cid = Integer.parseInt(cidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("种类id是int类型的");
											}
										}


										// ---------根据名字删种类-如果没有输出不存在--有但是有外键的话输出不能删除----------
										Categorys categorys = cs.findCategorysRecord(cid);
										Products products = ps.findProductsBycategoryId(cid);
										if (categorys != null) {
											if (products == null) {
												cs.subCategorysById(cid);
											} else {
												System.out.println("有外键关联种类，不能删除");
											}
										} else {
											System.out.println("没有这个种类id");
										}
										System.out.println("是否继续删除种类？0 ：退出  1：继续  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * 选择修改种类
										 */
									} else if ("3".equals(op3)) {
										System.out.println("请输入要修改的种类名");
										String cgname = input();
										System.out.println("请输入修改后的种类名");
										String cgname1 = input();
										System.out.println("请输如修改的介绍");
										String cgdesc = input();
										// ------------根据名字有无改数据包括介绍，没有则不存在---------------
										Categorys categorys = cs.findCategorysRecord(cgname);
										if (categorys != null) {
											Categorys categorys1 = cs.findCategorysRecord(cgname1,
													categorys.getCategoryID());
											if (categorys1 == null) {
												cs.changeCategorys(
														new Categorys(categorys.getCategoryID(), cgname1, cgdesc));
											} else {
												System.out.println("该种类名已存在");
											}
										} else {
											System.out.println("没有这个种类名");
										}
										System.out.println("是否继续删除种类？0 ：退出  1：继续  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * 选择 分页查询种类
										 */
									} else if ("4".equals(op3)) {

										// ---------------分页出所有种类信息包括介绍-------------
										int currentPage = 1;
										int pageSize = 3;

										Page<Categorys> page = cs
												.fingCategorysByPage(new Page<Categorys>(currentPage, pageSize));
										List<Categorys> list = page.getList();
										System.out.println("当前页" + page.getCurrentPage());
										System.out.println("页大小" + page.getPageSize());
										System.out.println("总记录" + page.getTotalRecord());
										System.out.println("总页数" + page.getTotalPage());
										for (Categorys cg : list) {
											System.out.println(cg);
										}
										while (true) {
											System.out.println("1：上一页  2：下一页  3：退出");
											String op4 = input();
											if ("1".equals(op4)) {
												page = cs.fingCategorysByPage(
														new Page<Categorys>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Categorys cg : list) {
													System.out.println(cg);
												}
											} else if ("2".equals(op4)) {
												page = cs.fingCategorysByPage(
														new Page<Categorys>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Categorys cg : list) {
													System.out.println(cg);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("请正确输入选择1-3");
											}
										}
										/**
										 *
										 *
										 *
										 *
										 * 返回上一层
										 */
									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("选择错误，请重新选择上一步操作");
										break;
									}
								}

							} else if ("2".equals(op2)) {
								while (true) {
									System.out.println("请选择供应商操作 1：增加供应商  2：删除供应商  3：修改供应商  4：分页查询供应商   5：退出");
									String op3 = input();
									if ("1".equals(op3)) {
										System.out.println("请输入增加的供应商名");
										String pvname = input();
										System.out.println("请输入供应商的地址");
										String pvadd = input();
										System.out.println("请输入电话");
										String pvtel = input();
										System.out.println("请输入账户");
										String pvcount = input();
										System.out.println("请输入邮箱");
										String pvemail = input();
										// ----------根据供应商名的有无增加所有信息-----------
										Providers providers = pvs.findProvidersRecord(name);
										if (providers == null) {
											pvs.addProviders(new Providers(pvname, pvadd, pvtel, pvcount, pvemail));
										} else {
											System.out.println("供应商记录已经存在");
										}

									} else if ("2".equals(op3)) {
										int pvid = 0;
										while (true) {
											System.out.println("请输入你要删除的供应商id");
											try {
												String pvidStr = input();
												pvid = Integer.parseInt(pvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("供应商id是int类型的");
											}
										}
										// --------------根据供应商名的有无删除，如果没有则不存在，如果有外键就不饿能删除-----------
										Providers providers = pvs.findProvidersRecord(pvid);
										Products products = ps.findProductsByproviderId(pvid);
										if (providers != null) {
											if (products == null) {
												pvs.subProvidersById(pvid);

											} else {
												System.out.println("有外键关联供应商，不能删除");
											}
										} else {
											System.out.println("没有这个供应商id");
										}
										System.out.println("是否继续删除种类？0 ：退出  1：继续  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
									} else if ("3".equals(op3)) {
										System.out.println("请输入要修改的供应商名");
										String pvname = input();
										System.out.println("请输入修改后的供应商名");
										String pvname1 = input();
										System.out.println("请输入供应商的地址");
										String pvadd = input();
										System.out.println("请输入电话");
										String pvtel = input();
										System.out.println("请输入账户");
										String pvcount = input();
										System.out.println("请输入邮箱");
										String pvemail = input();
										// ------先判断又没有这个供应商名，有的话就修改，没有就不存在-------------

										Providers providers = pvs.findProvidersRecord(pvname);
										if (providers != null) {
											Providers providers1 = pvs.findProvidersRecord(pvname1,
													providers.getProviderID());
											if (providers1 == null) {
												pvs.chagneProviders(new Providers(providers.getProviderID(), pvname1,
														pvadd, pvtel, pvcount, pvemail));
											} else {
												System.out.println("这个供应商已经存在");
											}
										} else {
											System.out.println("没有这个供应商");
										}

									} else if ("4".equals(op3)) {
										// ----------分页查询供应商的信息所有的供应商-------------

										int currentPage = 1;
										int pageSize = 3;
										Page<Providers> page = pvs
												.fingProvidersByPage(new Page<Providers>(currentPage, pageSize));
										List<Providers> list = page.getList();
										System.out.println("当前页" + page.getCurrentPage());
										System.out.println("页大小" + page.getPageSize());
										System.out.println("总记录" + page.getTotalRecord());
										System.out.println("总页数" + page.getTotalPage());
										for (Providers pv : list) {
											System.out.println(pv);
										}
										while (true) {
											System.out.println("1：上一页  2：下一页  3：退出");
											String op4 = input();
											if ("1".equals(op4)) {
												page = pvs.fingProvidersByPage(
														new Page<Providers>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Providers pv : list) {
													System.out.println(pv);
												}
											} else if ("2".equals(op4)) {
												page = pvs.fingProvidersByPage(
														new Page<Providers>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Providers pv : list) {
													System.out.println(pv);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("请正确输入选项1-3");
											}
										}

									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("请正确输入选项1-5");

									}
								}
							} else if ("3".equals(op2)) {
								while (true) {
									System.out.println("请选择产品操作：0：分页显示产品  1：显示所有产品  2：新增产品  3：删除产品  4：修改产品  5：返回");
									String op3 = input();

									if ("0".equals(op3)) {
										int currentPage = 1;
										int pageSize = 3;

										Page<Products> page = ps
												.findProductByPage(new Page<Products>(currentPage, pageSize));
										List<Products> list = page.getList();
										page.setTotalRecord(ps.getTotalRecord());

										System.out.println("当前页" + page.getCurrentPage());
										System.out.println("页大小" + page.getPageSize());
										System.out.println("总记录" + page.getTotalRecord());
										System.out.println("总页数" + page.getTotalPage());
										if (list == null || list.size() == 0) {
											break;
										}
										for (Products p : list) {
											System.out.println(p);
										}
										while (true) {
											System.out.println("1：上一页  2：下一页  3：退出");
											String op4 = input();
											if ("1".equals(op4)) {
												page = ps.findProductByPage(
														new Page<Products>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Products p : list) {
													System.out.println(p);
												}
											} else if ("2".equals(op4)) {
												page = ps.findProductByPage(
														new Page<Products>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("当前页" + page.getCurrentPage());
												System.out.println("页大小" + page.getPageSize());
												System.out.println("总记录" + page.getTotalRecord());
												System.out.println("总页数" + page.getTotalPage());
												for (Products p : list) {
													System.out.println(p);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("请正确输入1-3");
											}
										}
									} else if ("1".equals(op3)) {
										List<Products> list = ps.findAllProducts();
										for (Products p : list) {
											System.out.println(p);
										}

									} else if ("2".equals(op3)) {
										System.out.println("请输入要增加的产品名");
										String pname = input();
										double pinprice = 0;
										while (true) {
											System.out.println("请输入进货价格");
											try {
												String pinpricestr = input();
												pinprice = Double.parseDouble(pinpricestr);
												if (pinprice > 0) {
													break;
												} else {
													System.out.println("别输负数了，求你了");
												}
											} catch (NumberFormatException e) {
												System.out.println("进货价格是正数");
											}

										}
										int ppvid = 0;
										while (true) {
											System.out.println("请输入供应商ID");
											try {
												String ppvidStr = input();
												ppvid = Integer.parseInt(ppvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("供应商id是整数");
											}
										}
										int pquantity = 0;
										while (true) {
											System.out.println("请输入数量");
											try {
												String pquantitystr = input();
												pquantity = Integer.parseInt(pquantitystr);
												if (pquantity > 0) {
													break;
												} else {
													System.out.println("别输负数了，求你了");
												}
											} catch (NumberFormatException e) {
												System.out.println("数量是整数");
											}
										}
										double poutprice = 0;
										while (true) {
											System.out.println("请输入要卖的价格");
											try {
												String poutpriceStr = input();
												poutprice = Double.parseDouble(poutpriceStr);
												if (poutprice > 0 && poutprice > pinprice) {
													break;
												} else {
													System.out.println("负数和小于进货价格都不可以哦~~~");
												}
											} catch (NumberFormatException e) {
												System.out.println("卖货价格是正数");
											}
										}
										int pcgid = 0;
										while (true) {
											System.out.println("请输入种类ID");
											try {
												String pcgidStr = input();
												pcgid = Integer.parseInt(pcgidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("种类id是整数");
											}
										}

										// -----输入的时候先判断是否有重名，没有的话就输入这些内容，碰到外键的再判断一下另外两张表是否有这个id有的才能输入--------
										Categorys categorys = cs.findCategorysRecord(pcgid);
										Providers providers = pvs.findProvidersRecord(ppvid);
										if (categorys != null && providers != null) {
											Products products = ps.findProductsRecord(pname);
											if (products == null) {
												ps.addProducts(new Products(pname, pinprice, new Providers(ppvid),
														pquantity, poutprice, new Categorys(pcgid)));
											} else {
												System.out.println("这个产品名已经存在");
											}
										} else {
											System.out.println("供应商id或者种类id其中一个不存在");
										}

									} else if ("3".equals(op3)) {
										int pid = 0;
										while (true) {
											System.out.println("输入你要删除的产品id");
											try {
												String pidStr = input();
												pid = Integer.parseInt(pidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("产品id是整数");
											}
										}
										// -----------删之前先判断又没有，有就删除，没有就不存在--------------


										Products products = ps.findProductsByproductId(pid);
										if (products != null) {

											ps.subProductsById(pid);
										} else {
											System.out.println("找不到要删除的产品id");
										}
										System.out.println("是否继续删除种类？0 ：退出  1：继续  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}

									} else if ("4".equals(op3)) {

										System.out.println("输入要修改的产品名");
										String pname1 = input();

										System.out.println("输入修改后的产品名");
										String pname = input();

										double pinprice = 0;
										while (true) {
											System.out.println("请输入进货价格");
											try {
												String pinpricestr = input();
												pinprice = Double.parseDouble(pinpricestr);
												if (pinprice > 0) {
													break;
												} else {
													System.out.println("别输负数了，求你了");
												}

											} catch (NumberFormatException e) {
												System.out.println("进货价格是正数的");
											}

										}
										int ppvid = 0;
										while (true) {
											System.out.println("请输入供应商ID");
											try {
												String ppvidStr = input();
												ppvid = Integer.parseInt(ppvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("供应商id是正整数");
											}
										}
										int pquantity = 0;
										while (true) {
											System.out.println("请输入数量");
											try {
												String pquantitystr = input();
												pquantity = Integer.parseInt(pquantitystr);
												if (pquantity > 0) {
													break;
												} else {
													System.out.println("别输负数了，求你了");
												}
											} catch (NumberFormatException e) {
												System.out.println("数量是整数");
											}
										}
										double poutprice = 0;
										while (true) {
											System.out.println("请输入要卖的价格");
											try {
												String poutpriceStr = input();
												poutprice = Double.parseDouble(poutpriceStr);
												if (poutprice > 0 && poutprice > pinprice) {
													break;
												} else {
													System.out.println("负数和小于进货价格都不可以哦~~~");
												}
											} catch (NumberFormatException e) {
												System.out.println("卖货价格是正数");
											}
										}
										int pcgid = 0;
										while (true) {
											System.out.println("请输入种类ID");
											try {
												String pcgidStr = input();
												pcgid = Integer.parseInt(pcgidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("种类id是整数");
											}
										}


										// ----输入时候先判断是否有这个产品而且判断输进去的供应商id和种类id都存在，有的话就修改，没有的话不存在-----
										Categorys categorys = cs.findCategorysRecord(pcgid);
										Providers providers = pvs.findProvidersRecord(ppvid);
										if (categorys != null && providers != null) {
											Products products = ps.findProductsRecord(pname1);
											if (products != null) {
												Products products1 = ps.findProductsRecord(pname,
														products.getProductID());
												if (products1 == null) {
													ps.chagneProducts(new Products(products.getProductID(), pname,
															pinprice, new Providers(ppvid), pquantity, poutprice,
															new Categorys(pcgid)));
												} else {
													System.out.println("已经有这个产品名了");
												}
											} else {
												System.out.println("没这个要修改的产品");
											}
										} else {
											System.out.println("没找到对应的供货商id或者种类id");
										}

									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("请正确输入1-5");
									}
								}
							} else if ("4".equals(op2)) {
								break;
							} else {
								System.out.println("请正确输入1-4");
							}
						}
					} else {
						System.out.println("用户名或者密码输入错误！ 请重新选择");
					}
				} else if ("2".equals(op1)) {


					System.out.println("输入要注册的用户名");
					String name = input();

					System.out.println("输入要注册的密码：");
					String password = input();

					Admin admin = as.findAdminRecord(name);
					// 如果存在就提示已存在-----如果没有就输入用户名和密码，提示注册成功，加到数据库里


					if (admin == null) {
						as.addAdmin(new Admin(name, password));
					} else {
						System.out.println("用户名已存在");
					}

				} else if ("3".equals(op1)) {
					break;
				} else {
					System.out.println("请正确输入1-3");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("输入类型错误");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Test t = new Test();
		t.process();
	}
}
