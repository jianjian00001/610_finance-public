package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.UserMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.User;

//�������
@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;// ע��UserMapper

	// ͨ���û����������ѯ�û��Ƿ����
	@Override
	public User queryUserByUser(User user) {
		return userMapper.queryUserByUser(user);
	}

	// ͨ���û�����ѯ�û��Ƿ����
	@Override
	public User queryUserByUsername(String username) {
		return userMapper.queryUserByUsername(username);
	}

	// �޸����루�һ����룩
	@Override
	public void updatePasswordByUsername(User user) {
		userMapper.updatePasswordByUsername(user);

	}

	// �û�ע�ᣨ����û���
	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	// �û���Ϣ����
	@Override
	public void editUser(User user) {
		userMapper.editUser(user);
	}

	// ��ҳ��ѯ�û��б� --��װ��ҳ����
	@Override
	public PageBean<User> findUsers(User user, Integer currentPage) {

		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 10;// ÿҳ��¼��
		int allRecord = userMapper.findUsersCount(user);// ��ѯ�û��ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("user", user);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<User> pageList = userMapper.findUsers(map);// ��ҳ��ѯ�û���ǰҳ��¼�б�

		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		pageBean.setPageList(pageList);

		//����
//		System.out.println("allRecord��" + allRecord);
//		System.out.println("currentPage:"+currentPage);
//		System.out.println("pageList.size()"+pageList.size());
		return pageBean;
	}

	@Override
	public User queryUserById(Integer uid) {//�༭�û���Ϣҳ��
		return userMapper.queryUserById(uid);
	}

	@Override
	public void editUserAll(User user) {//�༭ȫ���û���Ϣ
		userMapper.editUserAll(user);
	}

	@Override
	public void deleteUser(int uid) {//ɾ���û�
		userMapper.deleteUser(uid);
	}

	@Override
	public int countUser() {//��ǰ�����û��ܼ�¼��
		return userMapper.countUser();
	}

}
