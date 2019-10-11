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
	// ��һ�����뷽��
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
			System.out.println("�ַ�������Ϊ�գ�����������");
		}else {
			break;
		}
       }
		return string;
	}
	// ��ǰ̨��������װ��һ������
	public void process() {
		// ץȡ�쳣
		try {
			// ʵ����
			as = new AdminServiceImp();
			ps = new ProductServiceImp();
			cs = new CategoryServiceImp();
			pvs = new ProviderServiceImp();
			Scanner sc = new Scanner(System.in);
			// Ϊ��ʵ�ַ�������
			while (true) {
				System.out.println("[-------��ӭʹ�ú��ݴ�۹�����ϵͳ-------]" + " \n" + "����ѡ�������1����¼     2��ע��     3���˳�");
				String op1 = input();
				/**
				 *
				 *
				 *
				 * ��¼����ϵͳ
				 */
				if ("1".equals(op1)) {
					System.out.println("�����������û�����");
					String name = input();
					System.out.println("�������������룺");
					String password = input();
					Admin admin = as.findAdminByLogin(new Admin(name, password));
					// �����ݿ��ѯû������
					if (admin != null) {
						System.out.println("��¼�ɹ�");
						// ��ӡ������Ա��Ϣ
						System.out.println(admin);
						while (true) {
							System.out.println("����ӭ����Ա��" + name + "��ʹ�õ���ϵͳ��" + "\n"
									+ "��ѡ������� 1��������    2����Ӧ�̹���    3����Ʒ����    4���˳�");
							String op2 = input();
							/**
							 * ѡ��������
							 *
							 *
							 *
							 */
							if ("1".equals(op2)) {
								while (true) {
									System.out.println("����ѡ��������� 1���������    2��ɾ�����    3���޸����    4����ҳ��ѯ���     5���˳�");
									String op3 = input();
									/*
									 * ѡ���������
									 */
									if ("1".equals(op3)) {
										System.out.println("��������Ҫ���ӵ�������");
										String cgname = input();
										System.out.println("�������������Ľ���");
										String cgdesc = input();
										// --------�������������������----------
										Categorys categorys = cs.findCategorysRecord(cgname);
										if (categorys == null) {
											cs.addCategorys(new Categorys(cgname, cgdesc));
										} else {
											System.out.println("�������Ѵ��ڣ�������ѡ��");
										}
										System.out.println("�Ƿ����������ࣿ0 ���˳�  1������  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * ѡ��ɾ������
										 **/
									} else if ("2".equals(op3)) {
										int cid = 0;
										while (true) {
											System.out.println("������Ҫɾ��������id");
											try {
												String cidStr = input();
												cid = Integer.parseInt(cidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("����id��int���͵�");
											}
										}


										// ---------��������ɾ����-���û�����������--�е���������Ļ��������ɾ��----------
										Categorys categorys = cs.findCategorysRecord(cid);
										Products products = ps.findProductsBycategoryId(cid);
										if (categorys != null) {
											if (products == null) {
												cs.subCategorysById(cid);
											} else {
												System.out.println("������������࣬����ɾ��");
											}
										} else {
											System.out.println("û���������id");
										}
										System.out.println("�Ƿ����ɾ�����ࣿ0 ���˳�  1������  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * ѡ���޸�����
										 */
									} else if ("3".equals(op3)) {
										System.out.println("������Ҫ�޸ĵ�������");
										String cgname = input();
										System.out.println("�������޸ĺ��������");
										String cgname1 = input();
										System.out.println("�������޸ĵĽ���");
										String cgdesc = input();
										// ------------�����������޸����ݰ������ܣ�û���򲻴���---------------
										Categorys categorys = cs.findCategorysRecord(cgname);
										if (categorys != null) {
											Categorys categorys1 = cs.findCategorysRecord(cgname1,
													categorys.getCategoryID());
											if (categorys1 == null) {
												cs.changeCategorys(
														new Categorys(categorys.getCategoryID(), cgname1, cgdesc));
											} else {
												System.out.println("���������Ѵ���");
											}
										} else {
											System.out.println("û�����������");
										}
										System.out.println("�Ƿ����ɾ�����ࣿ0 ���˳�  1������  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
										/**
										 *
										 *
										 *
										 * ѡ�� ��ҳ��ѯ����
										 */
									} else if ("4".equals(op3)) {

										// ---------------��ҳ������������Ϣ��������-------------
										int currentPage = 1;
										int pageSize = 3;

										Page<Categorys> page = cs
												.fingCategorysByPage(new Page<Categorys>(currentPage, pageSize));
										List<Categorys> list = page.getList();
										System.out.println("��ǰҳ" + page.getCurrentPage());
										System.out.println("ҳ��С" + page.getPageSize());
										System.out.println("�ܼ�¼" + page.getTotalRecord());
										System.out.println("��ҳ��" + page.getTotalPage());
										for (Categorys cg : list) {
											System.out.println(cg);
										}
										while (true) {
											System.out.println("1����һҳ  2����һҳ  3���˳�");
											String op4 = input();
											if ("1".equals(op4)) {
												page = cs.fingCategorysByPage(
														new Page<Categorys>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Categorys cg : list) {
													System.out.println(cg);
												}
											} else if ("2".equals(op4)) {
												page = cs.fingCategorysByPage(
														new Page<Categorys>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Categorys cg : list) {
													System.out.println(cg);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("����ȷ����ѡ��1-3");
											}
										}
										/**
										 *
										 *
										 *
										 *
										 * ������һ��
										 */
									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("ѡ�����������ѡ����һ������");
										break;
									}
								}

							} else if ("2".equals(op2)) {
								while (true) {
									System.out.println("��ѡ��Ӧ�̲��� 1�����ӹ�Ӧ��  2��ɾ����Ӧ��  3���޸Ĺ�Ӧ��  4����ҳ��ѯ��Ӧ��   5���˳�");
									String op3 = input();
									if ("1".equals(op3)) {
										System.out.println("���������ӵĹ�Ӧ����");
										String pvname = input();
										System.out.println("�����빩Ӧ�̵ĵ�ַ");
										String pvadd = input();
										System.out.println("������绰");
										String pvtel = input();
										System.out.println("�������˻�");
										String pvcount = input();
										System.out.println("����������");
										String pvemail = input();
										// ----------���ݹ�Ӧ��������������������Ϣ-----------
										Providers providers = pvs.findProvidersRecord(name);
										if (providers == null) {
											pvs.addProviders(new Providers(pvname, pvadd, pvtel, pvcount, pvemail));
										} else {
											System.out.println("��Ӧ�̼�¼�Ѿ�����");
										}

									} else if ("2".equals(op3)) {
										int pvid = 0;
										while (true) {
											System.out.println("��������Ҫɾ���Ĺ�Ӧ��id");
											try {
												String pvidStr = input();
												pvid = Integer.parseInt(pvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("��Ӧ��id��int���͵�");
											}
										}
										// --------------���ݹ�Ӧ����������ɾ�������û���򲻴��ڣ����������Ͳ�����ɾ��-----------
										Providers providers = pvs.findProvidersRecord(pvid);
										Products products = ps.findProductsByproviderId(pvid);
										if (providers != null) {
											if (products == null) {
												pvs.subProvidersById(pvid);

											} else {
												System.out.println("�����������Ӧ�̣�����ɾ��");
											}
										} else {
											System.out.println("û�������Ӧ��id");
										}
										System.out.println("�Ƿ����ɾ�����ࣿ0 ���˳�  1������  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}
									} else if ("3".equals(op3)) {
										System.out.println("������Ҫ�޸ĵĹ�Ӧ����");
										String pvname = input();
										System.out.println("�������޸ĺ�Ĺ�Ӧ����");
										String pvname1 = input();
										System.out.println("�����빩Ӧ�̵ĵ�ַ");
										String pvadd = input();
										System.out.println("������绰");
										String pvtel = input();
										System.out.println("�������˻�");
										String pvcount = input();
										System.out.println("����������");
										String pvemail = input();
										// ------���ж���û�������Ӧ�������еĻ����޸ģ�û�оͲ�����-------------

										Providers providers = pvs.findProvidersRecord(pvname);
										if (providers != null) {
											Providers providers1 = pvs.findProvidersRecord(pvname1,
													providers.getProviderID());
											if (providers1 == null) {
												pvs.chagneProviders(new Providers(providers.getProviderID(), pvname1,
														pvadd, pvtel, pvcount, pvemail));
											} else {
												System.out.println("�����Ӧ���Ѿ�����");
											}
										} else {
											System.out.println("û�������Ӧ��");
										}

									} else if ("4".equals(op3)) {
										// ----------��ҳ��ѯ��Ӧ�̵���Ϣ���еĹ�Ӧ��-------------

										int currentPage = 1;
										int pageSize = 3;
										Page<Providers> page = pvs
												.fingProvidersByPage(new Page<Providers>(currentPage, pageSize));
										List<Providers> list = page.getList();
										System.out.println("��ǰҳ" + page.getCurrentPage());
										System.out.println("ҳ��С" + page.getPageSize());
										System.out.println("�ܼ�¼" + page.getTotalRecord());
										System.out.println("��ҳ��" + page.getTotalPage());
										for (Providers pv : list) {
											System.out.println(pv);
										}
										while (true) {
											System.out.println("1����һҳ  2����һҳ  3���˳�");
											String op4 = input();
											if ("1".equals(op4)) {
												page = pvs.fingProvidersByPage(
														new Page<Providers>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Providers pv : list) {
													System.out.println(pv);
												}
											} else if ("2".equals(op4)) {
												page = pvs.fingProvidersByPage(
														new Page<Providers>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Providers pv : list) {
													System.out.println(pv);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("����ȷ����ѡ��1-3");
											}
										}

									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("����ȷ����ѡ��1-5");

									}
								}
							} else if ("3".equals(op2)) {
								while (true) {
									System.out.println("��ѡ���Ʒ������0����ҳ��ʾ��Ʒ  1����ʾ���в�Ʒ  2��������Ʒ  3��ɾ����Ʒ  4���޸Ĳ�Ʒ  5������");
									String op3 = input();

									if ("0".equals(op3)) {
										int currentPage = 1;
										int pageSize = 3;

										Page<Products> page = ps
												.findProductByPage(new Page<Products>(currentPage, pageSize));
										List<Products> list = page.getList();
										page.setTotalRecord(ps.getTotalRecord());

										System.out.println("��ǰҳ" + page.getCurrentPage());
										System.out.println("ҳ��С" + page.getPageSize());
										System.out.println("�ܼ�¼" + page.getTotalRecord());
										System.out.println("��ҳ��" + page.getTotalPage());
										if (list == null || list.size() == 0) {
											break;
										}
										for (Products p : list) {
											System.out.println(p);
										}
										while (true) {
											System.out.println("1����һҳ  2����һҳ  3���˳�");
											String op4 = input();
											if ("1".equals(op4)) {
												page = ps.findProductByPage(
														new Page<Products>(page.getPreviousPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Products p : list) {
													System.out.println(p);
												}
											} else if ("2".equals(op4)) {
												page = ps.findProductByPage(
														new Page<Products>(page.getNextPage(), pageSize));
												list = page.getList();
												System.out.println("��ǰҳ" + page.getCurrentPage());
												System.out.println("ҳ��С" + page.getPageSize());
												System.out.println("�ܼ�¼" + page.getTotalRecord());
												System.out.println("��ҳ��" + page.getTotalPage());
												for (Products p : list) {
													System.out.println(p);
												}
											} else if ("3".equals(op4)) {
												break;
											} else {
												System.out.println("����ȷ����1-3");
											}
										}
									} else if ("1".equals(op3)) {
										List<Products> list = ps.findAllProducts();
										for (Products p : list) {
											System.out.println(p);
										}

									} else if ("2".equals(op3)) {
										System.out.println("������Ҫ���ӵĲ�Ʒ��");
										String pname = input();
										double pinprice = 0;
										while (true) {
											System.out.println("����������۸�");
											try {
												String pinpricestr = input();
												pinprice = Double.parseDouble(pinpricestr);
												if (pinprice > 0) {
													break;
												} else {
													System.out.println("���为���ˣ�������");
												}
											} catch (NumberFormatException e) {
												System.out.println("�����۸�������");
											}

										}
										int ppvid = 0;
										while (true) {
											System.out.println("�����빩Ӧ��ID");
											try {
												String ppvidStr = input();
												ppvid = Integer.parseInt(ppvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("��Ӧ��id������");
											}
										}
										int pquantity = 0;
										while (true) {
											System.out.println("����������");
											try {
												String pquantitystr = input();
												pquantity = Integer.parseInt(pquantitystr);
												if (pquantity > 0) {
													break;
												} else {
													System.out.println("���为���ˣ�������");
												}
											} catch (NumberFormatException e) {
												System.out.println("����������");
											}
										}
										double poutprice = 0;
										while (true) {
											System.out.println("������Ҫ���ļ۸�");
											try {
												String poutpriceStr = input();
												poutprice = Double.parseDouble(poutpriceStr);
												if (poutprice > 0 && poutprice > pinprice) {
													break;
												} else {
													System.out.println("������С�ڽ����۸񶼲�����Ŷ~~~");
												}
											} catch (NumberFormatException e) {
												System.out.println("�����۸�������");
											}
										}
										int pcgid = 0;
										while (true) {
											System.out.println("����������ID");
											try {
												String pcgidStr = input();
												pcgid = Integer.parseInt(pcgidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("����id������");
											}
										}

										// -----�����ʱ�����ж��Ƿ���������û�еĻ���������Щ���ݣ�������������ж�һ���������ű��Ƿ������id�еĲ�������--------
										Categorys categorys = cs.findCategorysRecord(pcgid);
										Providers providers = pvs.findProvidersRecord(ppvid);
										if (categorys != null && providers != null) {
											Products products = ps.findProductsRecord(pname);
											if (products == null) {
												ps.addProducts(new Products(pname, pinprice, new Providers(ppvid),
														pquantity, poutprice, new Categorys(pcgid)));
											} else {
												System.out.println("�����Ʒ���Ѿ�����");
											}
										} else {
											System.out.println("��Ӧ��id��������id����һ��������");
										}

									} else if ("3".equals(op3)) {
										int pid = 0;
										while (true) {
											System.out.println("������Ҫɾ���Ĳ�Ʒid");
											try {
												String pidStr = input();
												pid = Integer.parseInt(pidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("��Ʒid������");
											}
										}
										// -----------ɾ֮ǰ���ж���û�У��о�ɾ����û�оͲ�����--------------


										Products products = ps.findProductsByproductId(pid);
										if (products != null) {

											ps.subProductsById(pid);
										} else {
											System.out.println("�Ҳ���Ҫɾ���Ĳ�Ʒid");
										}
										System.out.println("�Ƿ����ɾ�����ࣿ0 ���˳�  1������  ");
										String goon = input();
										if ("0".equals(goon)) {
											break;
										}

									} else if ("4".equals(op3)) {

										System.out.println("����Ҫ�޸ĵĲ�Ʒ��");
										String pname1 = input();

										System.out.println("�����޸ĺ�Ĳ�Ʒ��");
										String pname = input();

										double pinprice = 0;
										while (true) {
											System.out.println("����������۸�");
											try {
												String pinpricestr = input();
												pinprice = Double.parseDouble(pinpricestr);
												if (pinprice > 0) {
													break;
												} else {
													System.out.println("���为���ˣ�������");
												}

											} catch (NumberFormatException e) {
												System.out.println("�����۸���������");
											}

										}
										int ppvid = 0;
										while (true) {
											System.out.println("�����빩Ӧ��ID");
											try {
												String ppvidStr = input();
												ppvid = Integer.parseInt(ppvidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("��Ӧ��id��������");
											}
										}
										int pquantity = 0;
										while (true) {
											System.out.println("����������");
											try {
												String pquantitystr = input();
												pquantity = Integer.parseInt(pquantitystr);
												if (pquantity > 0) {
													break;
												} else {
													System.out.println("���为���ˣ�������");
												}
											} catch (NumberFormatException e) {
												System.out.println("����������");
											}
										}
										double poutprice = 0;
										while (true) {
											System.out.println("������Ҫ���ļ۸�");
											try {
												String poutpriceStr = input();
												poutprice = Double.parseDouble(poutpriceStr);
												if (poutprice > 0 && poutprice > pinprice) {
													break;
												} else {
													System.out.println("������С�ڽ����۸񶼲�����Ŷ~~~");
												}
											} catch (NumberFormatException e) {
												System.out.println("�����۸�������");
											}
										}
										int pcgid = 0;
										while (true) {
											System.out.println("����������ID");
											try {
												String pcgidStr = input();
												pcgid = Integer.parseInt(pcgidStr);
												break;
											} catch (NumberFormatException e) {
												System.out.println("����id������");
											}
										}


										// ----����ʱ�����ж��Ƿ��������Ʒ�����ж����ȥ�Ĺ�Ӧ��id������id�����ڣ��еĻ����޸ģ�û�еĻ�������-----
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
													System.out.println("�Ѿ��������Ʒ����");
												}
											} else {
												System.out.println("û���Ҫ�޸ĵĲ�Ʒ");
											}
										} else {
											System.out.println("û�ҵ���Ӧ�Ĺ�����id��������id");
										}

									} else if ("5".equals(op3)) {
										break;
									} else {
										System.out.println("����ȷ����1-5");
									}
								}
							} else if ("4".equals(op2)) {
								break;
							} else {
								System.out.println("����ȷ����1-4");
							}
						}
					} else {
						System.out.println("�û������������������ ������ѡ��");
					}
				} else if ("2".equals(op1)) {


					System.out.println("����Ҫע����û���");
					String name = input();

					System.out.println("����Ҫע������룺");
					String password = input();

					Admin admin = as.findAdminRecord(name);
					// ������ھ���ʾ�Ѵ���-----���û�о������û��������룬��ʾע��ɹ����ӵ����ݿ���


					if (admin == null) {
						as.addAdmin(new Admin(name, password));
					} else {
						System.out.println("�û����Ѵ���");
					}

				} else if ("3".equals(op1)) {
					break;
				} else {
					System.out.println("����ȷ����1-3");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("�������ʹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Test t = new Test();
		t.process();
	}
}
