package cn.zhku.jsj144.zk.financialManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.jsj144.zk.financialManage.mapper.CategoryManageMapper;
import cn.zhku.jsj144.zk.financialManage.pojo.PageBean;
import cn.zhku.jsj144.zk.financialManage.pojo.ShouzhiCategory;
import cn.zhku.jsj144.zk.financialManage.pojo.User;

@Transactional
@Service
public class CategoryManageServiceImpl implements CategoryManageService {

	@Autowired
	private CategoryManageMapper categoryManageMapper;

	//��ҳ��ѯ��֧���
	@Override
	public PageBean<ShouzhiCategory> findCategorys(
			ShouzhiCategory shouzhiCategory, Integer currentPage) {//��װPageBean

		if (currentPage == null) {
			currentPage = 0;
		}

		int pageRecord = 10;// ÿҳ��¼��
		int allRecord = categoryManageMapper.findCategorysCount(shouzhiCategory);// ��ѯ��֧����ܼ�¼��
		
		int allPage = 0;// ��ҳ��
		if (allRecord % pageRecord == 0) {
			allPage = allRecord / pageRecord;
		} else {
			allPage = allRecord / pageRecord + 1;
		}
		int startPosition = currentPage * pageRecord;// ��ʼλ��

		Map<String, Object> map = new HashMap<String, Object>();// ��ҳ��ѯ����
		map.put("shouzhiCategory", shouzhiCategory);
		map.put("startPosition", startPosition);
		map.put("pageRecord", pageRecord);
		List<ShouzhiCategory> pageList = categoryManageMapper.findCategorysCurrentPageList(map);// ��ҳ��ѯ��֧���ǰҳ��¼�б�

		PageBean<ShouzhiCategory> pageBean = new PageBean<ShouzhiCategory>();
		pageBean.setAllPage(allPage);
		pageBean.setAllRecord(allRecord);
		pageBean.setCurrentPage(currentPage);
		pageBean.setPageRecord(pageRecord);
		pageBean.setStartPosition(startPosition);
		pageBean.setPageList(pageList);

//		//����
//		System.out.println("allRecord��" + allRecord);
//		System.out.println("currentPage:"+currentPage);
//		System.out.println("pageList.size()"+pageList.size());
		return pageBean;
	}

	//�жϵ�ǰ�������Ƿ����
	@Override
	public ShouzhiCategory findCategory(ShouzhiCategory shouzhiCategory) {
		return categoryManageMapper. findCategory(shouzhiCategory);
	}

	//�����֧����
	@Override
	public void insertCategory(ShouzhiCategory shouzhiCategory) {
		categoryManageMapper.insertCategory(shouzhiCategory);
	}

	//ͨ��id��ѯ��ǰ��֧������Ϣ
	@Override
	public ShouzhiCategory queryShouzhiCategoryById(Integer szcid) {
		return categoryManageMapper.queryShouzhiCategoryById(szcid);
	}

	//�༭��֧������Ϣ
	@Override
	public void editShouzhiCategory(ShouzhiCategory shouzhiCategory) {
		categoryManageMapper.editShouzhiCategory(shouzhiCategory);
	}

	//���ҵ�ǰ��֧�������µ���֧��¼
	@Override
	public int countShouzhiRecord(int szcid) {
		return categoryManageMapper.countShouzhiRecord(szcid);
	}

	//ɾ����֧������
	@Override
	public void deleteShouzhiCategory(int szcid) {
		categoryManageMapper.deleteShouzhiCategory(szcid);
	}

	//��ǰ������֧�����ͼ�¼��
	@Override
	public int countShouzhiCategorys() {
		return categoryManageMapper.countShouzhiCategorys();
	}
}
