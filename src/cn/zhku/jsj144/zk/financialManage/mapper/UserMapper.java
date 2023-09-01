package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.User;

public interface UserMapper {

	User queryUserByUser(User user);//ͨ���û����������ѯ�û��Ƿ����

	User queryUserByUsername(String username);//ͨ���û�����ѯ�û��Ƿ����

	void updatePasswordByUsername(User user);//�޸����루�һ����룩

	void insertUser(User user);//�û�ע�ᣨ����û���

	void editUser(User user);//�û���Ϣ����

	int findUsersCount(User user);//��ѯ�û��ܼ�¼��

	List<User> findUsers(Map<String, Object> map);//��ҳ��ѯ�û���ǰҳ��¼�б�

	User queryUserById(Integer uid);//�༭�û���Ϣҳ��

	void editUserAll(User user);//�༭ȫ���û���Ϣ

	void deleteUser(int uid);//ɾ���û�

	int countUser();//��ǰ�����û��ܼ�¼��

}
