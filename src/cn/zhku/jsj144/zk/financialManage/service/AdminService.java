package cn.zhku.jsj144.zk.financialManage.service;

import cn.zhku.jsj144.zk.financialManage.pojo.Admin;

public interface AdminService {

	Admin findAdmin(Admin admin);//��̨��¼

	int countShouzhiRecord(int uid);//��֧��¼

	int countBudget(int uid);//Ԥ���¼

	int countWishList(int uid);//��Ը����¼

	int countMemorandum(int uid);//����¼��¼

}
