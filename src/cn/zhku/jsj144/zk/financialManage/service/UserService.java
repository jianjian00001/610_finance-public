package cn.zhku.jsj144.zk.financialManage.service;

import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.User;

public interface UserService{

	User queryUserByUser(User user);//ͨ���û����������ѯ�û��Ƿ����

	User queryUserByUsername(String username);//ͨ���û�����ѯ�û��Ƿ����

	void updatePasswordByUsername(User user);//�޸����루�һ����룩

	void insertUser(User user);//�û�ע�ᣨ����û���

	void editUser(User user);//�û���Ϣ����

	
	PageBean<User> findUsers(User user, Integer currentPage);//��ҳ��ѯ�û��б�

	User queryUserById(Integer uid);//�༭�û���Ϣҳ��

	void editUserAll(User user);//�༭ȫ���û���Ϣ

	void deleteUser(int uid);//ɾ���û�

	int countUser();//��ǰ�����û��ܼ�¼��

}
