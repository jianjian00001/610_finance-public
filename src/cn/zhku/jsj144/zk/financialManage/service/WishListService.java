package cn.zhku.jsj144.zk.financialManage.service;

import java.util.List;

import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;

public interface WishListService {

	PageBean<WishList> findAllWishList(int uid, Integer currentPage);//��ѯ������Ը��[��ҳ��ѯ]

	int CountWishByTimeAndId(WishList wishList);//��ȡĳ�ˣ�ĳ�����Ը������

	void addWish(WishList wishList);//�����Ը��

	WishList findWishById(int id);//����id��ѯ��Ը����Ϣ

	void editWish(WishList wishList);//�༭��Ը��

	void deleteWish(int id);//ɾ����Ը��
}
