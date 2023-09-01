package cn.zhku.jsj144.zk.financialManage.mapper;

import java.util.List;
import java.util.Map;

import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;

public interface WishListMapper {

	//��ѯ������Ը��
	List<WishList> findAllWishList(int uid);

	//��ȡĳ�ˣ�ĳ�����Ը������
	int CountWishByTimeAndId(WishList wishList);

	//�����Ը��
	void addWish(WishList wishList);

	//����id��ѯ��Ը����Ϣ
	WishList findWishById(int id);

	//�༭��Ը��
	void editWish(WishList wishList);

	//ɾ����Ը��
	void deleteWish(int id);

	// ��ѯ��Ը���ܼ�¼��
	int findWishListCount(int uid);

	// ��ҳ��ѯ,��Ը�� �ĵ�ǰҳ��¼�б�
	List<WishList> findWishList(Map<String, Object> map);

}
