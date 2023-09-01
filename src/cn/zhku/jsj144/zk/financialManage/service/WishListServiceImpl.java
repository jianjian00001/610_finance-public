package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.WishListMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.User;
import cn.zhku.jsj144.zk.financialManage.pojo.WishList;

@Transactional
@Service
public class WishListServiceImpl implements WishListService {
	
	@Autowired
	private WishListMapper wishListMapper;

	
	//��ѯ������Ը��[��ҳ��ѯ]
	@Override
	public PageBean<WishList> findAllWishList(int uid, Integer currentPage) {
		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 6;// ÿҳ��¼��
		int allRecord = wishListMapper.findWishListCount(uid);// ��ѯ��Ը���ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("uid", uid);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<WishList> pageList = wishListMapper.findWishList(map);// ��ҳ��ѯ,��Ը�� �ĵ�ǰҳ��¼�б�

		PageBean<WishList> pageBean = new PageBean<WishList>();
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
	
//	@Override
//	public List<WishList> findAllWishList(int uid) {//��ѯ������Ը��
//		return wishListMapper.findAllWishList(uid);
//	}

	@Override
	public int CountWishByTimeAndId(WishList wishList) {//��ȡĳ�ˣ�ĳ�����Ը������
		return wishListMapper.CountWishByTimeAndId(wishList);
	}

	@Override
	public void addWish(WishList wishList) {//�����Ը��
		 wishListMapper.addWish(wishList);
	}

	@Override
	public WishList findWishById(int id) {//����id��ѯ��Ը����Ϣ
		return wishListMapper.findWishById(id);
	}

	@Override
	public void editWish(WishList wishList) {	//�༭��Ը��
		wishListMapper.editWish(wishList);
	}

	@Override
	public void deleteWish(int id) {//ɾ����Ը��
		wishListMapper.deleteWish(id);
	}


}
